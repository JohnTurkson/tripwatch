package com.johnturkson.tripwatch.server.data

import kotlinx.serialization.Serializable

@Serializable
data class UserData(val id: String, val email: String, val password: String, val verified: Boolean)
