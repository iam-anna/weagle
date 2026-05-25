package com.fiapos.weagle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.fiapos.weagle.auth.login.AuthRepository
import com.fiapos.weagle.auth.session.SessionManager
import com.fiapos.weagle.data.local.database.AppDatabase
import com.fiapos.weagle.data.local.entities.UserEntity
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.features.projects.data.ProjectRepository
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import com.fiapos.weagle.presentation.navigation.AppNavGraph
import com.fiapos.weagle.ui.theme.WeagleTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "weagle_db"
        ).build()

        val userDao = db.userDao()

        val auth = AuthRepository(userDao)


        lifecycleScope.launch {
            auth.createdUser(UserEntity(
                name = "Operador ",
                email = "operator@test.com",
                password = "123456",
                role = "OPERATOR",
                isActive = true,
            ))
        }

        val session  = SessionManager(this)

        val ideaRepository = IdeaRepository()

        val strategicOrientationRepository = StrategicOrientationRepository()

        val projectRepository = ProjectRepository()

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