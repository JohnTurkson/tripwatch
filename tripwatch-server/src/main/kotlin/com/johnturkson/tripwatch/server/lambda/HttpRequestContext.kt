package com.johnturkson.tripwatch.server.lambda

import kotlinx.serialization.Serializable

@Serializable
data class HttpRequestContext(
    val http: HttpRequestContextMetadata,
    val requestId: String,
    val routeKey: String,
    val stage: String,
    val time: String,
)
