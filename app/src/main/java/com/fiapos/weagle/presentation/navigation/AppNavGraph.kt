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
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.domain.models.UserRole
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaViewModelFactory
import com.fiapos.weagle.presentation.LeaderHomeScreen
import com.fiapos.weagle.presentation.ManagerHomeScreen
import com.fiapos.weagle.presentation.OperatorHomeScreen

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
                            )
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

        composable(Routes.OPERATOR_HOME) {
            OperatorHomeScreen()
        }

        composable(Routes.MANAGER_HOME) {
            ManagerHomeScreen()
        }

        composable(Routes.LEADER_HOME) {
            LeaderHomeScreen()
        }

        composable(Routes.CREATE_IDEA) {
            val vm: CreateIdeaViewModel = viewModel(
                factory = CreateIdeaViewModelFactory(
                    ideaRepository,
                    sessionManager
                )
            )

            CreateIdeaScreen(vm, navController)
        }

//        composable("${Routes.OPERATOR_HOME}/{ideaId}") { backStackEntry ->
        composable(Routes.VIEW_IDEA) { backStackEntry ->
            val ideaId = backStackEntry.arguments?.getString("ideaId") ?: ""

            val vm: ViewIdeaViewModel = viewModel(
                factory = ViewIdeaViewModelFactory(
                    ideaRepository,
                    ideaId = ideaId
                )
            )

            ViewIdeaScreen(vm, navController)
        }
    }
}
