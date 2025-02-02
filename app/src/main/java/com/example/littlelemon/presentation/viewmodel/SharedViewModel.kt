package com.example.littlelemon.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.littlelemon.domain.model.User
import com.example.littlelemon.domain.usecase.ClearMenuUseCase
import com.example.littlelemon.domain.usecase.ClearUserUseCase
import com.example.littlelemon.domain.usecase.GetUserUseCase
import com.example.littlelemon.domain.usecase.IsUserAuthenticatedUseCase
import com.example.littlelemon.domain.usecase.SaveUserUseCase
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
    private val clearMenuUseCase: ClearMenuUseCase,
    private val saveUserUseCase: SaveUserUseCase,
    private val getUserUseCase: GetUserUseCase,
    private val isUserAuthenticatedUseCase: IsUserAuthenticatedUseCase,
    private val clearUserUseCase: ClearUserUseCase
) : ViewModel() {

    private val _user = MutableStateFlow<User?>(null)
    val user: StateFlow<User?> = _user.asStateFlow()

    init {
        loadUser()
    }

    fun loadUser() {
        viewModelScope.launch {
            _user.value = getUserUseCase()
        }
    }

    fun saveUser(user: User) {
        viewModelScope.launch {
            saveUserUseCase(user)
            _user.value = user
        }
    }

    fun isAuthenticated(): Boolean {
        return isUserAuthenticatedUseCase()
    }

    fun clearDataAndNavigate(onComplete: () -> Unit) {
        viewModelScope.launch {
            val clearUserDeferred = viewModelScope.async { clearMenuUseCase() }
            val clearMenuDeferred = viewModelScope.async {
                clearUserUseCase()
                _user.value = null
            }
            awaitAll(clearUserDeferred, clearMenuDeferred)
            onComplete()
        }
    }
}