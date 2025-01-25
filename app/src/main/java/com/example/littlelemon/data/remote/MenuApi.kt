package com.example.littlelemon.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class MenuApi(private val httpClient: HttpClient = ktorHttpClient) {
    private companion object {
        const val MENU_DATA_URL =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    }

    suspend fun getMenu() : MenuNetworkData {
        return httpClient
            .get(MENU_DATA_URL)
            .body()
    }
}