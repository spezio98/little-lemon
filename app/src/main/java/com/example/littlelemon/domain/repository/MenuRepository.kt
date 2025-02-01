package com.example.littlelemon.domain.repository

import com.example.littlelemon.domain.model.MenuList

interface MenuRepository {
    suspend fun getMenu(): MenuList
}