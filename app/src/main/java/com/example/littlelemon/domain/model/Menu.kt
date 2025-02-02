package com.example.littlelemon.domain.model

data class MenuList(
    val menu: List<MenuItem>
)

data class MenuItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val image: String,
    val category: String
)