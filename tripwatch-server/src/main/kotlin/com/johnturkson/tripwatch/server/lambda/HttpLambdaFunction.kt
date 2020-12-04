package com.johnturkson.tripwatch.server.lambda

import kotlinx.serialization.json.Json
import java.util.*

interface HttpLambdaFunction<T, R> : LambdaFunction {
    val serializer: Json
    
    override fun processInput(input: String): String {
        val request = input.decode()
        val response = processRequest(request)
        return response.encode()
    }
    
    fun String.decode() : HttpRequest {
        return serializer.decodeFromString(HttpRequest.serializer(), this)
    }
    
    fun HttpRequest.decodeBody(): String? {
        return when {
            body == null -> null
            isBase64Encoded -> Base64.getDecoder().decode(body).decodeToString()
            else -> body
        }
    }
    
    fun HttpResponse.encode(): String {
        return serializer.encodeToString(HttpResponse.serializer(), this)
    }
    
    fun processRequest(request: HttpRequest) : HttpResponse {
        return request.decode().process().encode()
    }
    
    fun HttpRequest.decode(): T?
    
    fun T?.process(): R
    
    fun R.encode(): HttpResponse
}
