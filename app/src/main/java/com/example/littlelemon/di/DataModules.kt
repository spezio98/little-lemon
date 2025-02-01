package com.example.littlelemon.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.littlelemon.data.local.LittleLemonDatabase
import com.example.littlelemon.data.local.MenuItemDao
import com.example.littlelemon.data.remote.service.MenuApi
import com.example.littlelemon.data.remote.service.ktorHttpClient
import com.example.littlelemon.data.repository.MenuRepositoryImpl
import com.example.littlelemon.data.repository.UserRepositoryImpl
import com.example.littlelemon.domain.repository.MenuRepository
import com.example.littlelemon.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import javax.inject.Singleton

private const val PREFERENCE_FILE_KEY = "preference_file"

@Module
@InstallIn(SingletonComponent::class)
object DataModules {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(PREFERENCE_FILE_KEY, Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideUserRepository(sharedPreferences: SharedPreferences): UserRepository {
        return UserRepositoryImpl(sharedPreferences)
    }

    @Provides
    @Singleton
    fun provideKtorHttpClient(): HttpClient {
        return ktorHttpClient
    }

    @Provides
    @Singleton
    fun provideMenuApi(httpClient: HttpClient): MenuApi {
        return MenuApi(httpClient)
    }

    @Provides
    @Singleton
    fun provideLittleLemonDatabase(@ApplicationContext context: Context): LittleLemonDatabase {
        return Room.databaseBuilder(context, LittleLemonDatabase::class.java, "little_lemon_db")
            .build()
    }

    @Provides
    @Singleton
    fun provideMenuItemDao(littleLemonDatabase: LittleLemonDatabase): MenuItemDao {
        return littleLemonDatabase.getMenuItemDao()
    }

    @Provides
    @Singleton
    fun provideMenuRepository(menuApi: MenuApi, menuItemDao: MenuItemDao): MenuRepository {
        return MenuRepositoryImpl(menuApi, menuItemDao)
    }
}