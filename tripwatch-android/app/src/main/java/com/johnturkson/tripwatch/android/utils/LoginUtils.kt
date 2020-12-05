package com.johnturkson.tripwatch.android.utils

import com.johnturkson.tripwatch.common.requests.Request
import com.johnturkson.tripwatch.common.responses.Response
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json

suspend fun requestLogIn(email : String, password : String) : Response {
    val client = HttpClient()

    val response = client.post<HttpStatement>("https://tripwatch.johnturkson.com/api/CreateUser") {
        body = Json.encodeToString(Request.serializer(), Request.CreateUserRequest(email, password))
        println(body)
    }

    val responseText = response.execute().readText()
    println(responseText)
    return Json.decodeFromString(Response.serializer(), responseText)
}