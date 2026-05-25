package com.fiapos.weagle.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.auth.session.SessionManager
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class LoginViewModel (
    private val authRepository: AuthRepository,
    private val sessionManager: SessionManager
) : ViewModel() {

    var loginState by mutableStateOf<LoginState>(
        LoginState.Idle
    )
        private set

    fun login(
        email: String,
        password: String
    ) {

        loginState = LoginState.Loading

        viewModelScope.launch {

            val user = authRepository.login(
                email,
                password
            )

            if (user != null) {

                sessionManager.saveUserId(user.id)

                loginState = LoginState.Success(user)
            } else {

                loginState = LoginState.Error(
                    "Invalid credentials"
                )
            }
        }
    }
}