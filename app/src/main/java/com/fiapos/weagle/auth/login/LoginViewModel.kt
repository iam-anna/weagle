package com.fiapos.weagle.auth.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.auth.session.SessionManager

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

//        val user = authRepository.login(
//            email,
//            password
//        )

        val user = authRepository.login(
            "manager@test.com",
            password
        )

        if (user != null) {
            sessionManager.saveUser(user)

            loginState = LoginState.Success(user)
        } else {
            loginState = LoginState.Error(
                "Invalid credentials"
            )
        }
    }
}