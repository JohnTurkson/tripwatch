package com.johnturkson.tripwatch.server.lambda

import kotlinx.serialization.json.Json

interface HttpLambdaFunction : LambdaFunction {
    val serializer: Json
    
    override fun processInput(input: String): String {
        val request = input.decode()
        val response = processRequest(request)
        return response.encode()
    }
    
    fun String.decode() : HttpRequest {
        return serializer.decodeFromString(HttpRequest.serializer(), this)
    }
    
    fun HttpResponse.encode(): String {
        return serializer.encodeToString(HttpResponse.serializer(), this)
    }
    
    fun processRequest(request: HttpRequest) : HttpResponse
}
