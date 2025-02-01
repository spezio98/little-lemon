package com.example.littlelemon.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.littlelemon.domain.model.MenuItem

@Entity(tableName = "menu_items")
data class MenuItemEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val image: String,
    val price: String,
    val description: String,
    val title: String,
    val category: String
) {
    fun toDomainModel() = MenuItem(
        id = id,
        title = title,
        description = description,
        price = price.toDouble(),
        image = image,
        category = category
    )
}