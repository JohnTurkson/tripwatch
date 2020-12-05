package com.johnturkson.tripwatch.common.requests

import com.johnturkson.tripwatch.common.data.UserData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class Request {
    @Serializable
    @SerialName("CreateUserRequest")
    data class CreateUserRequest(val data: UserData) : Request()
}
