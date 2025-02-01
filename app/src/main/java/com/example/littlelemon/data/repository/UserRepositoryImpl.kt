package com.example.littlelemon.data.repository

import android.content.SharedPreferences
import com.example.littlelemon.domain.model.User
import com.example.littlelemon.domain.repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val sharedPreferences: SharedPreferences
): UserRepository {

    companion object {
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val EMAIL = "email"
        const val IS_USER_AUTHENTICATED = "is_user_authenticated"
    }

    override suspend fun saveUser(user: User) {
        withContext(Dispatchers.IO) {
            sharedPreferences.edit().apply {
                putString(FIRST_NAME, user.firstName)
                putString(LAST_NAME, user.lastName)
                putString(EMAIL, user.email)
                putBoolean(IS_USER_AUTHENTICATED, true)
                apply()
            }
        }
    }

    override suspend fun getUser(): User? {
        return withContext(Dispatchers.IO) {
            val isAuthenticated = sharedPreferences.getBoolean(IS_USER_AUTHENTICATED, false)
            if (!isAuthenticated) return@withContext null
            val name = sharedPreferences.getString(FIRST_NAME, null)
            val lastName = sharedPreferences.getString(LAST_NAME, null)
            val email = sharedPreferences.getString(EMAIL, null)
            if( name == null || lastName == null || email == null) return@withContext null

            User(name, lastName, email)
        }
    }

    override fun isAuthenticated(): Boolean {
        return sharedPreferences.getBoolean(IS_USER_AUTHENTICATED, false)
    }

    override fun clearAll() {
        sharedPreferences.edit().clear().commit()
    }
}