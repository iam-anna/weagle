package com.fiapos.weagle.presentation.navigation;

import androidx.compose.runtime.Composable;
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.fiapos.weagle.features.auth.data.AuthRepository;
import com.fiapos.weagle.features.auth.presentation.login.LoginScreen
import com.fiapos.weagle.features.auth.presentation.login.LoginViewModel
import com.fiapos.weagle.features.auth.presentation.login.LoginViewModelFactory
import com.fiapos.weagle.features.auth.session.SessionManager;
import com.fiapos.weagle.features.ideas.data.IdeaRepository
import com.fiapos.weagle.features.auth.data.domain.models.UserRole
import com.fiapos.weagle.features.auth.data.UserRepository
import com.fiapos.weagle.features.auth.presentation.home.HomeScreen
import com.fiapos.weagle.features.auth.presentation.home.HomeViewModel
import com.fiapos.weagle.features.auth.presentation.home.HomeViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.create.CreateIdeaViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.edit.EditIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.edit.EditIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.edit.EditIdeaViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.list.SelectIdeaViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.listview.ListViewIdeasScreen
import com.fiapos.weagle.features.ideas.presentation.listview.ListViewIdeasViewModel
import com.fiapos.weagle.features.ideas.presentation.listview.ListViewIdeasViewModelFactory
import com.fiapos.weagle.features.ideas.presentation.select.SelectIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.select.SelectIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaScreen
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaViewModel
import com.fiapos.weagle.features.ideas.presentation.view.ViewIdeaViewModelFactory
import com.fiapos.weagle.features.projects.data.ProjectRepository
import com.fiapos.weagle.features.projects.presentation.create.CreateProjectScreen
import com.fiapos.weagle.features.projects.presentation.create.CreateProjectViewModel
import com.fiapos.weagle.features.projects.presentation.create.CreateProjectViewModelFactory
import com.fiapos.weagle.features.projects.presentation.listview.ListViewProjectsScreen
import com.fiapos.weagle.features.projects.presentation.listview.ListViewProjectsViewModel
import com.fiapos.weagle.features.projects.presentation.listview.ListViewProjectsViewModelFactory
import com.fiapos.weagle.features.projects.presentation.view.ViewProjectScreen
import com.fiapos.weagle.features.projects.presentation.view.ViewProjectViewModel
import com.fiapos.weagle.features.projects.presentation.view.ViewProjectViewModelFactory
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import com.fiapos.weagle.features.so.presentation.create.CreateStrategicOrientationScreen
import com.fiapos.weagle.features.so.presentation.create.CreateStrategicOrientationViewModel
import com.fiapos.weagle.features.so.presentation.create.CreateStrategicOrientationViewModelFactory
import com.fiapos.weagle.features.so.presentation.edit.EditStrategicOrientationScreen
import com.fiapos.weagle.features.so.presentation.edit.EditStrategicOrientationViewModel
import com.fiapos.weagle.features.so.presentation.edit.EditStrategicOrientationViewModelFactory
import com.fiapos.weagle.features.so.presentation.listview.ListViewStrategicOrientationScreen
import com.fiapos.weagle.features.so.presentation.listview.ListViewStrategicOrientationViewModel
import com.fiapos.weagle.features.so.presentation.listview.ListViewStrategicOrientationViewModelFactory
import com.fiapos.weagle.features.so.presentation.view.ViewStrategicOrientationScreen
import com.fiapos.weagle.features.so.presentation.view.ViewStrategicOrientationViewModel
import com.fiapos.weagle.features.so.presentation.view.ViewStrategicOrientationViewModelFactory

@Composable
fun AppNavGraph(
    authRepository: AuthRepository,
    userRepository: UserRepository,
    ideaRepository: IdeaRepository,
    projectRepository: ProjectRepository,
    strategicOrientationRepository: StrategicOrientationRepository,
    sessionManager: SessionManager
) {
    val navController = rememberNavController()

    val createProjectVm: CreateProjectViewModel = viewModel(
        factory = CreateProjectViewModelFactory(
            projectRepository,
            sessionManager
        )
    )

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

            val vm: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    userRepository,
                    sessionManager
                )
            )

            HomeScreen(vm, navController)
        }

        composable(Routes.MANAGER_HOME) {

            val vm: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    userRepository,
                    sessionManager
                )
            )

            HomeScreen(vm, navController)
        }

        composable(Routes.LEADER_HOME) {

            val vm: HomeViewModel = viewModel(
                factory = HomeViewModelFactory(
                    userRepository,
                    sessionManager
                )
            )

            HomeScreen(vm, navController)
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

        composable(Routes.SELECT_IDEA) {
            val vm: SelectIdeaViewModel = viewModel(
                factory = SelectIdeaViewModelFactory(
                    ideaRepository,
                    sessionManager
                )
            )

            SelectIdeaScreen(
                viewModel = vm,
                projectViewModel = createProjectVm,
                navController
            )
        }

        composable(Routes.CREATE_STRATEGIC_ORIENTATION) {

            val vm: CreateStrategicOrientationViewModel = viewModel(
                factory = CreateStrategicOrientationViewModelFactory(
                    strategicOrientationRepository,
                    sessionManager,
                )
            )

            CreateStrategicOrientationScreen(vm, navController)
        }

        composable(Routes.LIST_STRATEGIC_ORIENTATIONS) {

            val vm: ListViewStrategicOrientationViewModel = viewModel(
                factory = ListViewStrategicOrientationViewModelFactory(
                    strategicOrientationRepository,
                    sessionManager
                )
            )

            ListViewStrategicOrientationScreen(vm, navController)
        }

        composable("${Routes.VIEW_STRATEGIC_ORIENTATION}/{strategicOrientationId}") { backStackEntry ->

            val orientationId = backStackEntry.arguments?.getString("strategicOrientationId") ?: ""

            val vm: ViewStrategicOrientationViewModel = viewModel(
            factory = ViewStrategicOrientationViewModelFactory(
                strategicOrientationRepository,
                sessionManager,
                orientationId
                )
            )

            ViewStrategicOrientationScreen(vm, navController)
        }

        composable("${Routes.EDIT_STRATEGIC_ORIENTATION}/{strategicOrientationId}") { backStackEntry ->

            val orientationId = backStackEntry.arguments?.getString("strategicOrientationId") ?: ""

            val vm: EditStrategicOrientationViewModel = viewModel(
                factory = EditStrategicOrientationViewModelFactory(
                    strategicOrientationRepository,
                    orientationId
                )
            )

            EditStrategicOrientationScreen(vm, navController)
        }

        composable(Routes.CREATE_PROJECT) {

            CreateProjectScreen(createProjectVm, navController)
        }

        composable(Routes.LIST_PROJECTS) {
            val vm: ListViewProjectsViewModel = viewModel(
                factory = ListViewProjectsViewModelFactory(
                    projectRepository,
                )
            )

            ListViewProjectsScreen(vm, navController)
        }

        composable("${Routes.VIEW_PROJECT}/{ideaId}") { backStackEntry ->
//        composable(Routes.MANAGER_HOME) { backStackEntry ->
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
