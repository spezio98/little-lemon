package com.example.littlelemon.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MenuItemDao {
    @Query("SELECT * FROM menu_items")
    fun getMenuItems(): Flow<List<MenuItemLocal>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMenuItems(menuItems: List<MenuItemLocal>)

}