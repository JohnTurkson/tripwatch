package com.johnturkson.tripwatch.server.configuration

import kotlinx.serialization.json.Json

object SerializerConfiguration {
    val instance: Json = Json {
        ignoreUnknownKeys = true
        encodeDefaults = true
    }
}
