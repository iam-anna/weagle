package com.fiapos.weagle.features.auth.presentation.home

import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fiapos.weagle.features.auth.data.domain.models.User
import com.fiapos.weagle.features.auth.data.UserRepository
import com.fiapos.weagle.features.auth.session.SessionManager
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: UserRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    var user by mutableStateOf<User?>(
        null
    )
        private set

    init {
        loadUser()
    }

    private fun loadUser(){

        viewModelScope.launch {
            val currentUserId = sessionManager.getUserId()

            user = repository.getUserById(currentUserId?.toInt() ?: 0)
        }
    }


}