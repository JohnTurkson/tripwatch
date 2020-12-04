package com.johnturkson.tripwatch.server.functions

import com.johnturkson.tripwatch.common.data.User
import com.johnturkson.tripwatch.common.data.UserData
import com.johnturkson.tripwatch.common.requests.Request
import com.johnturkson.tripwatch.common.requests.Request.CreateUserRequest
import com.johnturkson.tripwatch.common.responses.Response
import com.johnturkson.tripwatch.common.responses.Response.Error.BadRequest.InvalidRequestError
import com.johnturkson.tripwatch.common.responses.Response.Success.OK.CreateUserResponse
import com.johnturkson.tripwatch.server.lambda.HttpLambdaFunction
import com.johnturkson.tripwatch.server.lambda.HttpRequest
import com.johnturkson.tripwatch.server.lambda.HttpResponse
import kotlinx.serialization.json.Json
import kotlin.random.Random
import kotlin.random.nextInt

class TripWatchCreateUserFunction : HttpLambdaFunction<Request, Response> {
    override val serializer = Json { ignoreUnknownKeys = true }
    
    override fun HttpRequest.decode(): Request? {
        return when (val body = this.decodeBody()) {
            null -> null
            else -> serializer.decodeFromString(Request.serializer(), body)
        }
    }
    
    override fun Request?.process(): Response {
        return when (this) {
            is CreateUserRequest -> createUser(this.data)
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
    
    fun createUser(data: UserData): Response {
        // TODO check if username is taken
        // TODO check if email is taken
        // TODO check password
        val user = User(generateUserId(), data.email, data.username)
        // TODO add user to database
        // TODO send verification email
        
        return CreateUserResponse(user)
    }
    
    fun generateUserId(length: Int = 16): String {
        var id = ""
        repeat(length) { id += Random.nextInt(0..0xf).toString(0x10) }
        return id
    }
}
