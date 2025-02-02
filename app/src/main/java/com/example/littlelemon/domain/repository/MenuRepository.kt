package com.example.littlelemon.domain.repository

import com.example.littlelemon.core.utils.UiState
import com.example.littlelemon.domain.model.MenuList

interface MenuRepository {
    suspend fun getMenu(): UiState<MenuList>
    suspend fun clearMenu()
}