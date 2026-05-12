package com.fiapos.weagle.presentation.navigation;

import androidx.compose.runtime.Composable;
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.fiapos.weagle.auth.login.AuthRepository;
import com.fiapos.weagle.auth.login.LoginScreen
import com.fiapos.weagle.auth.login.LoginViewModel
import com.fiapos.weagle.auth.login.LoginViewModelFactory
import com.fiapos.weagle.auth.session.SessionManager;
import com.fiapos.weagle.data.IdeaRepository
import com.fiapos.weagle.domain.models.UserRole
import com.fiapos.weagle.features.ideas.presentation.CreateIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.CreateIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.CreateIdeaViewModelFactory
import com.fiapos.weagle.presentation.LeaderHomeScreen
import com.fiapos.weagle.presentation.ManagerHomeScreen

@Composable
fun AppNavGraph(
    authRepository: AuthRepository,
    ideaRepository: IdeaRepository,
    sessionManager: SessionManager
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.LOGIN
    ) {
        composable(Routes.LOGIN) {
            val loginViewModel: LoginViewModel = viewModel(
                factory = LoginViewModelFactory(
                    authRepository,
                    sessionManager
                )
            )

            LoginScreen(
                viewModel = loginViewModel,
                onLoginSuccess = { user ->
                    when (user.role) {
                        UserRole.OPERATOR -> {
                            navController.navigate(
                                Routes.OPERATOR_HOME
                            ) {
                                popUpTo(Routes.LOGIN) {
                                    inclusive = true
                                }
                            }
                        }

                        UserRole.MANAGER -> {
                            navController.navigate(
                                Routes.MANAGER_HOME
                            )
                        }

                        UserRole.LEADER -> {
                            navController.navigate(
                                Routes.LEADER_HOME
                            )
                        }
                    }
                }
            )
        }

//        composable(Routes.OPERATOR_HOME) {
//            CreateIdea()
//        }

        composable(Routes.MANAGER_HOME) {
            ManagerHomeScreen()
        }

        composable(Routes.LEADER_HOME) {
            LeaderHomeScreen()
        }

        composable(Routes.OPERATOR_HOME) {
            val vm: CreateIdeaViewModel = viewModel(
                factory = CreateIdeaViewModelFactory(
                    ideaRepository,
                    sessionManager
                )
            )

            CreateIdeaScreen(vm)
        }
    }
}
