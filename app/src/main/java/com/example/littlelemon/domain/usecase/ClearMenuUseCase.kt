package com.example.littlelemon.domain.usecase

import com.example.littlelemon.domain.repository.MenuRepository
import javax.inject.Inject

class ClearMenuUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke() {
        return menuRepository.clearMenu()
    }
}