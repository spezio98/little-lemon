package com.example.littlelemon.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.littlelemon.data.local.entities.MenuItemEntity

@Database(entities = [MenuItemEntity::class], version = 1)
abstract class LittleLemonDatabase : RoomDatabase() {
    abstract fun getMenuItemDao(): MenuItemDao
}