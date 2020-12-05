package com.johnturkson.tripwatch.common.data

import kotlinx.serialization.Serializable

@Serializable
data class User(val id: String, val email: String)
