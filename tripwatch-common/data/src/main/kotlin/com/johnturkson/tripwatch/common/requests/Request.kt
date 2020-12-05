package com.johnturkson.tripwatch.common.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Request")
sealed class Request {
    @Serializable
    @SerialName("CreateUserRequest")
    data class CreateUserRequest(val email: String, val password: String) : Request()
}
