package com.example.littlelemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.core.utils.UiState
import com.example.littlelemon.domain.model.MenuList
import com.example.littlelemon.domain.repository.MenuRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val menuRepository: MenuRepository
) : ViewModel() {
    private val _menuState = MutableStateFlow<UiState<MenuList>>(UiState.Loading)
    val menuState: StateFlow<UiState<MenuList>> = _menuState.asStateFlow()

    init {
        getMenu()
    }

    fun getMenu() {
        viewModelScope.launch {
            _menuState.value = UiState.Loading
            _menuState.value = menuRepository.getMenu()
        }
    }

}