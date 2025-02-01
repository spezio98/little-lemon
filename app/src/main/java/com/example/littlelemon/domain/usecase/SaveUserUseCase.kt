package com.example.littlelemon.domain.usecase

import com.example.littlelemon.domain.model.User
import com.example.littlelemon.domain.repository.UserRepository
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    suspend operator fun invoke(user: User){
        return userRepository.saveUser(user)
    }
}