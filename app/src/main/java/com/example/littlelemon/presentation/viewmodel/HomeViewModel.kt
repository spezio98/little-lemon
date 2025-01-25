package com.example.littlelemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.data.AppRepository
import com.example.littlelemon.data.local.MenuItemLocal
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel(
    private val appRepository: AppRepository
) : ViewModel() {
    private val _menuData = MutableStateFlow<List<MenuItemLocal>>(listOf())
    val menuData: StateFlow<List<MenuItemLocal>> = _menuData.asStateFlow()

    init {
        fetchData()
    }

    fun fetchData() {
        viewModelScope.launch {
            appRepository.getMenu()
                .collect { menuList -> _menuData.value = menuList }
        }
    }
}