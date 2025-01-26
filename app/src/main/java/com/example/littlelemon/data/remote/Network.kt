package com.example.littlelemon.data.remote

import com.example.littlelemon.data.local.MenuItemLocal
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MenuNetworkData(
    @SerialName("menu")
    val menu: List<MenuItemNetwork>
)

@Serializable
data class MenuItemNetwork(
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
    fun toLocal() = MenuItemLocal(
        id = id,
        title = title,
        description = description,
        price = price.toString(),
        image = image,
        category = category
    )
}