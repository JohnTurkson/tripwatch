package com.johnturkson.tripwatch.server.configuration

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.websocket.*

object ClientConfiguration {
    val instance: HttpClient = HttpClient(CIO) {
        install(WebSockets)
    }
}
