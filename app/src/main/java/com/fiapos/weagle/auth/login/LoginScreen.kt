package com.fiapos.weagle.auth.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiapos.weagle.domain.models.User
import com.fiapos.weagle.presentation.components.CustomButton
import com.fiapos.weagle.presentation.components.Input

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: (User) -> Unit
) {
    var email by remember {
        mutableStateOf("")
    }

    var password by remember {
        mutableStateOf("")
    }

    val state  = viewModel.loginState

    LaunchedEffect(state) {
        if (state is LoginState.Success) {
            onLoginSuccess(state.user)
        }
    }

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(40.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Weagle",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.primary
        )
        Text(
            text = "Bem vindo(a)!",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.secondary
        )

        Spacer(modifier = Modifier.height(100.dp))

        Input(
            label = "E-mail",
            placeholder = "joao.silve@example.com",
            value = email,
            onValueChange = {
                email = it
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Input(
            label = "Senha",
            value = password,
            onValueChange = {
                password = it
            },
            isSecure = true
        )

        Spacer(modifier = Modifier.height(80.dp))

        CustomButton(
            text = "Entrar",
            onClick = {
                viewModel.login(email, password)
            }
        )
    }
}