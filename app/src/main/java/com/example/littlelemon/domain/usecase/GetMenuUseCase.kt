package com.example.littlelemon.domain.usecase

import com.example.littlelemon.core.utils.UiState
import com.example.littlelemon.domain.model.MenuList
import com.example.littlelemon.domain.repository.MenuRepository
import javax.inject.Inject

class GetMenuUseCase @Inject constructor(
    private val menuRepository: MenuRepository
) {
    suspend operator fun invoke(): UiState<MenuList> {
        return menuRepository.getMenu()
    }
}