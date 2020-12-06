package com.johnturkson.tripwatch.server.lambda

import java.util.Base64

interface HttpLambdaFunction<T, R> : LambdaFunction<T, R> {
    override fun String.decode(): T? {
        return this.decodeHttpRequest()?.decodeTypedRequest()
    }
    
    override fun R.encode(): String {
        return this.encodeTypedResponse().encodeHttpResponse()
    }
    
    fun String.decodeHttpRequest(): HttpRequest?
    
    fun HttpRequest.decodeTypedRequest(): T?
    
    fun R.encodeTypedResponse(): HttpResponse
    
    fun HttpResponse.encodeHttpResponse(): String
    
    fun HttpRequest.decodeBody(): String? {
        return when {
            body == null -> null
            isBase64Encoded -> Base64.getDecoder().decode(body).decodeToString()
            else -> body
        }
    }
}
