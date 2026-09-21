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
import com.fiapos.weagle.data.remote.ApiClient
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

        val session  = SessionManager(this)
        val api = ApiClient.create(session::getToken)
        val auth = AuthRepository(userDao, api)

//        lifecycleScope.launch {
//            val existingUser = userDao.getByEmail("operator@test.com")
//
//            if(existingUser == null) {
//
//                auth.createdUser(
//                    UserEntity(
//                        name = "Josue Pereira Souza",
//                        email = "operator@test.com",
//                        password = "123456",
//                        role = "OPERATOR",
//                        isActive = true,
//                    )
//                )
//                auth.createdUser(
//                    UserEntity(
//                        name = "Josue Pereira Souza",
//                        email = "leader@test.com",
//                        password = "123456",
//                        role = "LEADER",
//                        isActive = true,
//                    )
//                )
//
//                auth.createdUser(
//                    UserEntity(
//                        name = "Josue Pereira Souza",
//                        email = "manager@test.com",
//                        password = "123456",
//                        role = "MANAGER",
//                        isActive = true,
//                    )
//                )
//            }
//        }

        val userRepository = UserRepository(userDao)

        val ideaRepository = IdeaRepository(db.ideaDao(), api)

        val strategicOrientationRepository = StrategicOrientationRepository(db.strategicOrientationDao(), api)

        val projectRepository = ProjectRepository(db.projectDao(), db.ideaDao(), api)

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