package com.johnturkson.tripwatch.common.data

import kotlinx.serialization.Serializable

@Serializable
data class Trip(val tripName : String, val userIds : List<String>, val imageUrl : String)
