package com.fiapos.weagle

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.fiapos.weagle.features.auth.data.AuthRepository
import com.fiapos.weagle.features.auth.session.SessionManager
import com.fiapos.weagle.data.local.database.AppDatabase
import com.fiapos.weagle.features.auth.data.UserRepository
import com.fiapos.weagle.features.auth.data.entities.UserEntity
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
        )
            .fallbackToDestructiveMigration()
            .build()

        val userDao = db.userDao()

        val auth = AuthRepository(userDao)

        lifecycleScope.launch {
            val existingUser = userDao.getByEmail("operator@test.com")

//            if(existingUser == null) {

                auth.createdUser(
                    UserEntity(
                        name = "Josue Pereira Souza",
                        email = "leader@test.com",
                        password = "123456",
                        role = "LEADER",
                        isActive = true,
                    )
                )
//            }
        }

        val session  = SessionManager(this)

        val userRepository = UserRepository(userDao)

        val ideaRepository = IdeaRepository(db.ideaDao())

        val strategicOrientationRepository = StrategicOrientationRepository(db.strategicOrientationDao())

        val projectRepository = ProjectRepository()

        setContent {
            WeagleTheme {
                AppNavGraph(
                    authRepository = auth,
                    sessionManager = session,
                    userRepository = userRepository,
                    ideaRepository = ideaRepository,
                    projectRepository = projectRepository,
                    strategicOrientationRepository = strategicOrientationRepository
                )
            }
        }
    }
}