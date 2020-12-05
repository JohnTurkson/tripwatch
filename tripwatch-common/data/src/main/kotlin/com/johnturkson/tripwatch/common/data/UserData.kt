package com.johnturkson.tripwatch.common.data

import kotlinx.serialization.Serializable

@Serializable
data class UserData(val email: String, val username: String, val password: String)
