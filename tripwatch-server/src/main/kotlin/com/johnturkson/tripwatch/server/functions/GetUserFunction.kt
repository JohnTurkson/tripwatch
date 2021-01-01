package com.johnturkson.tripwatch.server.functions

import com.johnturkson.awstools.dynamodb.client.DynamoDBClient
import com.johnturkson.awstools.dynamodb.requests.GetItemRequest
import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.requests.Request
import com.johnturkson.tripwatch.common.requests.Request.GetUserRequest
import com.johnturkson.tripwatch.common.responses.Response
import com.johnturkson.tripwatch.common.responses.Response.ClientError.BadRequest.InvalidRequestError
import com.johnturkson.tripwatch.common.responses.Response.ClientError.NotFound.UserNotFoundError
import com.johnturkson.tripwatch.common.responses.Response.Success.OK.GetUserResponse
import com.johnturkson.tripwatch.server.configuration.SerializerConfiguration
import com.johnturkson.tripwatch.server.database.data.UserData
import com.johnturkson.tripwatch.server.database.keys.UserKey
import com.johnturkson.tripwatch.server.lambda.HttpLambdaFunction
import com.johnturkson.tripwatch.server.lambda.HttpRequest
import com.johnturkson.tripwatch.server.lambda.HttpResponse
import kotlinx.coroutines.runBlocking

class GetUserFunction : HttpLambdaFunction<Request, Response> {
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
            is GetUserRequest -> runBlocking { getUser(user) }
            else -> InvalidRequestError
        }
    }
    
    suspend fun getUser(user: String): Response {
        // TODO authorization
        
        val handler = DynamoDBClient()
        
        val getUserRequest = GetItemRequest(
            "TripWatchUsers",
            UserKey(user),
        )
        
        val getUserResponse = runCatching {
            handler.getItem<UserKey, UserData>(getUserRequest)
        }.getOrElse {
            return UserNotFoundError(user)
        }
        
        val (id, email) = getUserResponse.item
        
        return GetUserResponse(User(id.value, email.value))
    }
}
