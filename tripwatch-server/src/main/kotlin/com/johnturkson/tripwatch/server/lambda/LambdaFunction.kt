package com.johnturkson.tripwatch.server.lambda

import com.amazonaws.services.lambda.runtime.Context
import com.amazonaws.services.lambda.runtime.RequestStreamHandler
import java.io.InputStream
import java.io.OutputStream

interface LambdaFunction<T, R> : RequestStreamHandler {
    override fun handleRequest(input: InputStream, output: OutputStream, context: Context) {
        val request = input.bufferedReader().use { reader -> reader.readText() }
        val response = processInput(request)
        output.bufferedWriter().use { writer -> writer.write(response) }
    }
    
    fun processInput(input: String): String {
        return input.decode().process().encode()
    }
    
    fun String.decode(): T?
    
    fun T?.process(): R
    
    fun R.encode(): String
}
