package com.johnturkson.tripwatch.android.utils

import com.johnturkson.tripwatch.common.requests.Request
import com.johnturkson.tripwatch.common.responses.Response
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.serialization.json.Json


/*
* File containing anything that communicates
* with the backend endpoint and/or pertains to login/authentication
*
*/

val LOGIN_USERNAME_ERROR = "You have entered an invalid email"
val LOGIN_PASSWORD_ERROR = "You have entered an invalid password"
val LOGIN_AUTH_ERROR = "You have entered an invalid email/password"
val LOGIN_SERVER_ERROR = "There was a problem communicating with the server. Try again later"

suspend fun createAccount(email : String, password : String) : Response {
    val client = HttpClient()

    val response = client.post<HttpStatement>("https://tripwatch.johnturkson.com/api/CreateUser") {
        body = Json.encodeToString(Request.serializer(), Request.CreateUserRequest(email, password))
        println(body)
    }

    val responseText = response.execute().readBytes().map { byte -> byte.toInt().toChar() }.joinToString(separator = "")

    println(responseText)
    return Json.decodeFromString(Response.serializer(), responseText)
}