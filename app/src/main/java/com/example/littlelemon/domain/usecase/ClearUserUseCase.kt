package com.example.littlelemon.domain.usecase

import com.example.littlelemon.domain.repository.UserRepository
import javax.inject.Inject

class ClearUserUseCase @Inject constructor(
    private val userRepository: UserRepository
) {
    operator fun invoke() {
        return userRepository.clearUser()
    }
}