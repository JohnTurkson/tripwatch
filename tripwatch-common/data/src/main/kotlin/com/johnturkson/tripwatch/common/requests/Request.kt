package com.johnturkson.tripwatch.common.requests

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("Request")
sealed class Request {
    @Serializable
    @SerialName("CreateUserRequest")
    data class CreateUserRequest(val email: String, val password: String) : Request()
    
    @Serializable
    @SerialName("GetUserRequest")
    data class GetUserRequest(val user: String) : Request()
    
    @Serializable
    @SerialName("GetUserVerificationStatusRequest")
    data class GetUserVerificationStatusRequest(val user: String) : Request()
    
    @Serializable
    @SerialName("SendVerificationEmailRequest")
    data class SendVerificationEmailRequest(val user: String) : Request()
}
