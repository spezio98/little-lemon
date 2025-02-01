package com.example.littlelemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.domain.model.User
import com.example.littlelemon.domain.repository.MenuRepository
import com.example.littlelemon.domain.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val menuRepository: MenuRepository
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch {
            _user.value = userRepository.getUser()
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            userRepository.saveUser(user)
            _user.value = user
        }
    }

    fun isAuthenticated(): Boolean {
        return userRepository.isAuthenticated()
    }

    fun clearDataAndNavigate(onComplete: () -> Unit) {
        viewModelScope.launch {
            val clearUserDeferred = viewModelScope.async { menuRepository.clearMenu() }
            val clearMenuDeferred = viewModelScope.async {
                userRepository.clearAll()
                _user.value = null
            }
            awaitAll(clearUserDeferred, clearMenuDeferred)
            onComplete()
        }
    }
}