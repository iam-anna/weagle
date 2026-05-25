package com.fiapos.weagle.features.auth.login

import com.fiapos.weagle.domain.models.User

sealed class LoginState {
    object Idle : LoginState()
    object Loading : LoginState()
    data class Success(val user: User) : LoginState()
    data class Error(val message: String) : LoginState()
}