package com.example.littlelemon.data.remote.dto

import com.example.littlelemon.data.local.entities.MenuItemEntity
import com.example.littlelemon.domain.model.MenuItem
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuListDto(
    @SerialName("menu")
    val menu: List<MenuItemDto>
)

@Serializable
data class MenuItemDto(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("description")
    val description: String,

    @SerialName("price")
    val price: Double,

    @SerialName("image")
    val image: String,

    @SerialName("category")
    val category: String
) {
    fun toDomainModel() = MenuItem(
        id = id,
        title = title,
        description = description,
        price = price,
        image = image,
        category = category
    )

    fun toEntityModel() = MenuItemEntity(
        id = id,
        title = title,
        description = description,
        price = price.toString(),
        image = image,
        category = category
    )
}