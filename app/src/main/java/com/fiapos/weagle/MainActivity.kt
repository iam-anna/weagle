package com.fiapos.weagle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.NavController
import com.fiapos.weagle.auth.login.AuthRepository
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.features.projects.data.ProjectRepository
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import com.fiapos.weagle.presentation.navigation.AppNavGraph
import com.fiapos.weagle.ui.theme.WeagleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val auth = AuthRepository()
        val session  = SessionManager()

        val ideaRepository = IdeaRepository()
        val strategicOrientationRepository = StrategicOrientationRepository()
        val projectRepository = ProjectRepository()

        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeagleTheme {
                AppNavGraph(
                    authRepository = auth,
                    sessionManager = session,
                    ideaRepository = ideaRepository,
                    projectRepository = projectRepository,
                    strategicOrientationRepository = strategicOrientationRepository
                )
            }
        }
    }
}