package com.example.littlelemon.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.kotlinx.json.json

val ktorHttpClient = HttpClient(Android) {
    install(ContentNegotiation) {
        json(contentType = ContentType("text", "plain"))
    }
}