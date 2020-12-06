package com.johnturkson.tripwatch.server.functions

import com.johnturkson.awstools.dynamodb.objectbuilder.buildDynamoDBObject
import com.johnturkson.awstools.dynamodb.requestbuilder.requests.UpdateItemRequest
import com.johnturkson.awstools.ses.requestbuilder.builder.Content
import com.johnturkson.awstools.ses.requestbuilder.builder.Destination
import com.johnturkson.awstools.ses.requestbuilder.components.EmailContent
import com.johnturkson.awstools.ses.requestbuilder.requests.SendEmailRequest
import com.johnturkson.tripwatch.common.requests.Request
import com.johnturkson.tripwatch.common.requests.Request.SendVerificationEmailRequest
import com.johnturkson.tripwatch.common.responses.Response
import com.johnturkson.tripwatch.common.responses.Response.ClientError.BadRequest.InvalidRequestError
import com.johnturkson.tripwatch.common.responses.Response.ClientError.BadRequest.UserAlreadyVerifiedError
import com.johnturkson.tripwatch.common.responses.Response.ClientError.Forbidden.InvalidCredentialsError
import com.johnturkson.tripwatch.common.responses.Response.ServerError.InternalServerError
import com.johnturkson.tripwatch.common.responses.Response.Success.OK.*
import com.johnturkson.tripwatch.server.configuration.DatabaseRequestHandler
import com.johnturkson.tripwatch.server.configuration.EmailRequestHandler
import com.johnturkson.tripwatch.server.configuration.SerializerConfiguration
import com.johnturkson.tripwatch.server.lambda.HttpLambdaFunction
import com.johnturkson.tripwatch.server.lambda.HttpRequest
import com.johnturkson.tripwatch.server.lambda.HttpResponse
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlin.random.Random
import kotlin.random.nextInt

class SendVerificationEmailFunction : HttpLambdaFunction<Request, Response> {
    private val serializer = SerializerConfiguration.instance
    
    override fun String.decodeHttpRequest(): HttpRequest? {
        return runCatching { serializer.decodeFromString(HttpRequest.serializer(), this) }.getOrNull()
    }
    
    override fun HttpRequest.decodeTypedRequest(): Request? {
        return when (val body = this.decodeBody()) {
            null -> null
            else -> runCatching { serializer.decodeFromString(Request.serializer(), body) }.getOrNull()
        }
    }
    
    override fun Response.encodeTypedResponse(): HttpResponse {
        val statusCode = this.statusCode
        val headers = mapOf("Content-Type" to "application/json")
        val body = serializer.encodeToString(Response.serializer(), this)
        val isBase64Encoded = false
        return HttpResponse(statusCode, headers, body, isBase64Encoded)
    }
    
    override fun HttpResponse.encodeHttpResponse(): String {
        return serializer.encodeToString(HttpResponse.serializer(), this)
    }
    
    override fun Request?.process(): Response {
        return when (this) {
            is SendVerificationEmailRequest -> runBlocking { sendVerificationEmail(user) }
            else -> InvalidRequestError
        }
    }
    
    suspend fun sendVerificationEmail(user: String): Response {
        // TODO authentication
        
        val verified = when (val response = GetUserVerificationStatusFunction().getUserVerificationStatus(user)) {
            is GetUserVerificationStatusResponse -> response.verified
            else -> return InvalidCredentialsError
        }
        
        if (verified) return UserAlreadyVerifiedError
        
        val address = when (val response = GetUserFunction().getUser(user)) {
            is GetUserResponse -> response.user.email
            else -> return InvalidCredentialsError
        }
        
        val code = createVerificationCode()
        val link = createVerificationLink(user, code)
        
        // TODO remove restriction in the future
        if (!address.matches(Regex("\\w+@johnturkson\\.com"))) {
            return SendVerificationEmailResponse
        }
        
        val sender = "tripwatch@johnturkson.com"
        val subject = "Verify Your TripWatch Account"
        val body = link
        val sendEmailRequest = SendEmailRequest(
            sender,
            Destination {
                ToAddresses(address)
            },
            generateVerificationEmail(subject, body)
        )
        
        val setVerificationCodeRequest = UpdateItemRequest(
            "TripWatchUserVerificationCodes",
            buildDynamoDBObject {
                put("id", user)
            },
            updateExpression = "set code = :code",
            expressionAttributeValues = buildDynamoDBObject {
                put(":code", code)
            }
        )
        
        runCatching {
            DatabaseRequestHandler.instance.updateItem(
                setVerificationCodeRequest,
                MapSerializer(String.serializer(), String.serializer())
            )
        }.onFailure {
            return InternalServerError
        }
        
        runCatching {
            EmailRequestHandler.instance.sendEmail(sendEmailRequest)
        }.onFailure {
            return InternalServerError
        }
        
        // TODO put link into database
        
        return SendVerificationEmailResponse
    }
    
    fun generateVerificationEmail(subject: String, body: String): EmailContent {
        return Content {
            Simple {
                Subject {
                    Data(subject)
                }
                Body {
                    Text {
                        Data(body)
                    }
                }
            }
        }
    }
    
    fun createVerificationCode(): String {
        val length = 16
        var id = ""
        repeat(length) { id += Random.nextInt(0..0xf).toString(0x10) }
        return id
    }
    
    fun createVerificationLink(user: String, code: String): String {
        return "https://tripwatch.johnturkson.com/api/VerifyUser?user=$user&code=$code"
    }
}
