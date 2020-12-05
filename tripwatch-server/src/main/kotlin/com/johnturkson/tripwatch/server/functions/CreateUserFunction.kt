package com.johnturkson.tripwatch.server.functions

import com.johnturkson.awstools.dynamodb.requestbuilder.requests.PutItemRequest
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.requests.Request
import com.johnturkson.tripwatch.common.requests.Request.CreateUserRequest
import com.johnturkson.tripwatch.common.responses.Response
import com.johnturkson.tripwatch.common.responses.Response.ClientError.BadRequest.*
import com.johnturkson.tripwatch.common.responses.Response.ServerError.InternalServerError
import com.johnturkson.tripwatch.common.responses.Response.Success.OK.CreateUserResponse
import com.johnturkson.tripwatch.server.configuration.DatabaseRequestHandler
import com.johnturkson.tripwatch.server.configuration.SerializerConfiguration
import com.johnturkson.tripwatch.server.data.UserData
import com.johnturkson.tripwatch.server.lambda.HttpLambdaFunction
import com.johnturkson.tripwatch.server.lambda.HttpRequest
import com.johnturkson.tripwatch.server.lambda.HttpResponse
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import java.security.MessageDigest
import java.util.Base64
import kotlin.random.Random
import kotlin.random.nextInt

class CreateUserFunction : HttpLambdaFunction<Request, Response> {
    override val serializer = SerializerConfiguration.instance
    
    override fun HttpRequest.decode(): Request? {
        return when (val body = this.decodeBody()) {
            null -> null
            else -> runCatching { serializer.decodeFromString(Request.serializer(), body) }.getOrNull()
        }
    }
    
    override fun Request?.process(): Response {
        return when (this) {
            is CreateUserRequest -> runBlocking { createUser(email, password) }
            else -> InvalidRequestError
        }
    }
    
    override fun Response.encode(): HttpResponse {
        val statusCode = this.statusCode
        val headers = mapOf("Content-Type" to "application/json")
        val body = serializer.encodeToString(Response.serializer(), this)
        val isBase64Encoded = false
        return HttpResponse(statusCode, headers, body, isBase64Encoded)
    }
    
    suspend fun createUser(email: String, password: String): Response {
        if (email.isInvalidEmail()) return InvalidEmailError
        if (password.isTooShort()) return PasswordTooShortError
        if (password.isTooLong()) return PasswordTooLongError
        if (password.isCommonPassword()) return CommonPasswordError
        if (password.isBreached()) return BreachedPasswordError
        
        val handler = DatabaseRequestHandler.instance
        
        val putEmailRequest = PutItemRequest(
            "TripWatchUserEmails",
            mapOf("email" to email),
            conditionExpression = "attribute_not_exists(email)",
        )
        
        runCatching {
            handler.putItem(putEmailRequest, MapSerializer(String.serializer(), String.serializer()))
        }.onFailure {
            return EmailAlreadyTakenError
        }
        
        val user = User(generateId(), email)
        val data = UserData(generateId(), user.email, generatePasswordHash(password), false)
        
        val putUserRequest = PutItemRequest("TripWatchUsers", data)
        
        runCatching {
            handler.putItem(putUserRequest, UserData.serializer())
        }.onFailure {
            return InternalServerError
        }
        
        // TODO send verification email
        
        return CreateUserResponse(user)
    }
    
    fun generateId(length: Int = 16): String {
        var id = ""
        repeat(length) { id += Random.nextInt(0..0xf).toString(0x10) }
        return id
    }
    
    fun generatePasswordHash(password: String): String {
        val prehashAlgorithm = "SHA-512"
        val prehashBytes = MessageDigest.getInstance(prehashAlgorithm).digest(password.toByteArray())
        val prehashHex = Base64.getEncoder().encodeToString(prehashBytes)
        val encoder = BCryptPasswordEncoder()
        return encoder.encode(prehashHex)
    }
    
    private fun String.isInvalidEmail(): Boolean {
        // TODO
        return false
    }
    
    private fun String.isTooShort(): Boolean {
        return this.length < 10
    }
    
    private fun String.isTooLong(): Boolean {
        return this.length > 1000
    }
    
    private fun String.isCommonPassword(): Boolean {
        // TODO
        return false
    }
    
    private fun String.isBreached(): Boolean {
        // TODO
        return false
    }
}
