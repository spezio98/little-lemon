package com.example.littlelemon.data

import android.content.Context
import com.example.littlelemon.data.local.LittleLemonDatabase
import com.example.littlelemon.data.remote.MenuApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class AppRepository private constructor(
    private val menuApi: MenuApi,
    private val database: LittleLemonDatabase
) {

    companion object {
        private var INSTANCE: AppRepository? = null

        fun getInstance(
            context: Context,
            menuApi: MenuApi = MenuApi(),
            database: LittleLemonDatabase = LittleLemonDatabase.getDatabase(context)
        ): AppRepository {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = AppRepository(
                        menuApi = menuApi,
                        database = database
                    )
                }
            }
            return INSTANCE!!
        }
    }

    fun getMenu() = flow {
        var localItems = database.getMenuItemDao().getMenuItems().first()

        if(localItems.isEmpty()) {
            val networkItems = menuApi.getMenu()
            val newLocalItems = networkItems.menu.map { it.toLocal() }
            database.getMenuItemDao().insertMenuItems(newLocalItems)
            emit(newLocalItems)
        } else {
            emit(localItems)
        }
    }.flowOn(Dispatchers.IO)

}