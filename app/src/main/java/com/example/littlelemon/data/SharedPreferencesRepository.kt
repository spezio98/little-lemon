package com.example.littlelemon.data

import android.content.Context
import com.example.littlelemon.data.model.User

class SharedPreferencesRepository private constructor(
    context: Context
) {
    private val sharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)

    companion object {
        const val PREFERENCE_FILE_KEY = "preference_file"
        const val FIRST_NAME = "first_name"
        const val LAST_NAME = "last_name"
        const val EMAIL = "email"
        const val IS_USER_AUTHENTICATED = "is_user_authenticated"

        private var INSTANCE: SharedPreferencesRepository? = null

        fun getSharedPreferenceRepository(context: Context): SharedPreferencesRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = SharedPreferencesRepository(context)
                }
            }
            return INSTANCE!!
        }
    }

    fun isUserAuthenticated(): Boolean{
        return sharedPreferences.getBoolean(IS_USER_AUTHENTICATED, false)
    }

    fun saveData(user: User) {
        sharedPreferences.edit().apply{
            putString(FIRST_NAME, user.firstName)
            putString(LAST_NAME, user.lastName)
            putString(EMAIL, user.email)
            putBoolean(IS_USER_AUTHENTICATED, true)
        }.commit()
    }
}