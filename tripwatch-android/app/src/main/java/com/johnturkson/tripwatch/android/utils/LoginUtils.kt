package com.johnturkson.tripwatch.android.utils

import com.johnturkson.tripwatch.common.data.UserData
import com.johnturkson.tripwatch.common.requests.Request
import com.johnturkson.tripwatch.common.requests.Request.CreateUserRequest
import com.johnturkson.tripwatch.common.responses.Response
import io.ktor.client.*
import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

suspend fun requestLogIn(userData : UserData) : Response {
    val client = HttpClient()

    val response = client.post<HttpStatement>("https://tripwatch.johnturkson.com/api/CreateUser") {
        body = Json.encodeToString(Request.serializer(), CreateUserRequest(userData))
        println(body)
    }

    val responseText = response.execute().readText()
    println(responseText)
    return Json.decodeFromString(Response.serializer(), responseText)
}