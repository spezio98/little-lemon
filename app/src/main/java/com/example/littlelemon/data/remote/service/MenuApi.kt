package com.example.littlelemon.data.remote.service

import com.example.littlelemon.data.remote.dto.MenuListDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import javax.inject.Inject

class MenuApi @Inject constructor(
    private val httpClient: HttpClient
) {
    private companion object {
        const val MENU_DATA_URL =
            "https://raw.githubusercontent.com/Meta-Mobile-Developer-PC/Working-With-Data-API/main/menu.json"
    }

    suspend fun getMenu() : MenuListDto {
        return httpClient.get(MENU_DATA_URL).body()
    }
}