package com.example.littlelemon.data.local.entities

import androidx.room.PrimaryKey
import com.example.littlelemon.domain.model.User

data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val firstName: String,
    val lastName: String,
    val email: String
) {
    fun toDomainModel(): User {
        return User(
            firstName = firstName,
            lastName = lastName,
            email = email
        )
    }
}

fun User.toEntity(): UserEntity {
    return UserEntity(
        firstName = firstName,
        lastName = lastName,
        email = email
    )
}
