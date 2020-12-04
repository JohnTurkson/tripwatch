package com.johnturkson.tripwatch.server.lambda

import kotlinx.serialization.Serializable

@Serializable
data class HttpRequest(
    val version: String,
    val routeKey: String,
    val rawPath: String,
    val rawQueryString: String,
    val headers: Map<String, String>,
    val requestContext: HttpRequestContext,
    val body: String? = null,
    val isBase64Encoded: Boolean,
)
