package com.fiapos.weagle.presentation.navigation;

import androidx.compose.runtime.Composable;
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.fiapos.weagle.features.auth.login.AuthRepository;
import com.fiapos.weagle.features.auth.login.LoginScreen
import com.fiapos.weagle.features.auth.login.LoginViewModel
import com.fiapos.weagle.features.auth.login.LoginViewModelFactory
import com.fiapos.weagle.features.auth.session.SessionManager;
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.domain.models.UserRole
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.edit.EditIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.edit.EditIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.edit.EditIdeaViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.listview.ListViewIdeasScreen
import com.fiapos.weagle.features.ideas.presentation.listview.ListViewIdeasViewModel
import com.fiapos.weagle.features.ideas.presentation.listview.ListViewIdeasViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaViewModelFactory
import com.fiapos.weagle.features.projects.data.ProjectRepository
import com.fiapos.weagle.features.projects.presentation.create.CreateProjectScreen
import com.fiapos.weagle.features.projects.presentation.create.CreateProjectViewModel
import com.fiapos.weagle.features.projects.presentation.create.CreateProjectViewModelFactory
import com.fiapos.weagle.features.projects.presentation.view.ViewProjectScreen
import com.fiapos.weagle.features.projects.presentation.view.ViewProjectViewModel
import com.fiapos.weagle.features.projects.presentation.view.ViewProjectViewModelFactory
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import com.fiapos.weagle.features.so.presentation.listview.ListViewStrategicOrientationScreen
import com.fiapos.weagle.features.so.presentation.listview.ListViewStrategicOrientationViewModel
import com.fiapos.weagle.features.so.presentation.listview.ListViewStrategicOrientationViewModelFactory
import com.fiapos.weagle.features.so.presentation.view.ViewStrategicOrientationScreen
import com.fiapos.weagle.features.so.presentation.view.ViewStrategicOrientationViewModel
import com.fiapos.weagle.features.so.presentation.view.ViewStrategicOrientationViewModelFactory
import com.fiapos.weagle.presentation.LeaderHomeScreen
import com.fiapos.weagle.presentation.ManagerHomeScreen

@Composable
fun AppNavGraph(
    authRepository: AuthRepository,
    ideaRepository: IdeaRepository,
    projectRepository: ProjectRepository,
    strategicOrientationRepository: StrategicOrientationRepository,
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

            val vm: ListViewIdeasViewModel = viewModel(
                factory = ListViewIdeasViewModelFactory(
                    ideaRepository,
                    sessionManager
                )
            )

            ListViewIdeasScreen(vm, navController)
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

        composable("${Routes.VIEW_IDEA}/{ideaId}") { backStackEntry ->

            val ideaId = backStackEntry.arguments?.getString("ideaId") ?: ""

            val vm: ViewIdeaViewModel = viewModel(
                factory = ViewIdeaViewModelFactory(
                    ideaRepository,
                    sessionManager,
                    ideaId
                )
            )

            ViewIdeaScreen(vm, navController)
        }

        composable("${Routes.EDIT_IDEA}/{ideaId}") { backStackEntry ->

            val ideaId = backStackEntry.arguments?.getString("ideaId") ?: ""

            val vm: EditIdeaViewModel = viewModel(
                factory = EditIdeaViewModelFactory(
                    ideaRepository,
                    ideaId
                )
            )

            EditIdeaScreen(vm, navController)
        }

        composable(Routes.LIST_IDEAS) {

            val vm: ListViewIdeasViewModel = viewModel(
                factory = ListViewIdeasViewModelFactory(
                    ideaRepository,
                    sessionManager
                )
            )

            ListViewIdeasScreen(vm, navController)
        }

        composable(Routes.LIST_STRATEGIC_ORIENTATION) {
            val vm: ListViewStrategicOrientationViewModel = viewModel(
                factory = ListViewStrategicOrientationViewModelFactory(
                    strategicOrientationRepository
                )
            )

            ListViewStrategicOrientationScreen(vm, navController)
        }

//        composable("${Routes.VIEW_STRATEGIC_ORIENTATION}/{strategicOrientationId}") { backStackEntry ->
        composable(Routes.VIEW_STRATEGIC_ORIENTATION) { backStackEntry ->
            val orientationId = backStackEntry.arguments?.getString("strategicOrientationId") ?: ""

            val vm: ViewStrategicOrientationViewModel = viewModel(
            factory = ViewStrategicOrientationViewModelFactory(
                    strategicOrientationRepository,
                    orientationId
                )
            )

            ViewStrategicOrientationScreen(vm, navController)
        }

        composable(Routes.CREATE_PROJECT) {
            val vm: CreateProjectViewModel = viewModel(
                factory = CreateProjectViewModelFactory(
                    projectRepository,
                    sessionManager
                )
            )

            CreateProjectScreen(vm, navController)
        }

//        composable("${Routes.VIEW_PROJECT}/{ideaId}") { backStackEntry ->
        composable(Routes.MANAGER_HOME) { backStackEntry ->
            val projectId = backStackEntry.arguments?.getString("ideaId") ?: ""

            val vm: ViewProjectViewModel = viewModel(
                factory = ViewProjectViewModelFactory(
                    projectRepository,
                    projectId = projectId
                )
            )

            ViewProjectScreen(vm, navController)
        }
    }
}
