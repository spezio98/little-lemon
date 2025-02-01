package com.example.littlelemon.domain.repository

import com.example.littlelemon.domain.model.User

interface UserRepository {
    suspend fun saveUser(user: User)
    suspend fun getUser(): User?
    fun isAuthenticated(): Boolean
    fun clearAll()
}