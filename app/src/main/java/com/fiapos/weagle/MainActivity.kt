package com.fiapos.weagle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import com.fiapos.weagle.auth.login.AuthRepository
import com.fiapos.weagle.auth.login.LoginScreen
import com.fiapos.weagle.auth.login.LoginViewModel
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.domain.models.User
import com.fiapos.weagle.presentation.navigation.AppNavGraph
import com.fiapos.weagle.ui.theme.WeagleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val auth = AuthRepository()
        val session  = SessionManager()

        var user: User

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeagleTheme {
                AppNavGraph(
                    authRepository = auth,
                    sessionManager = session
                )
            }
        }
    }
}