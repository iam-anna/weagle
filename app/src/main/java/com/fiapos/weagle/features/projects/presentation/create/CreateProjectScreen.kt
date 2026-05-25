package com.fiapos.weagle.features.projects.presentation.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.features.ideas.domains.Idea
import com.fiapos.weagle.domain.models.ProjectStatus
import com.fiapos.weagle.presentation.components.*
import com.fiapos.weagle.presentation.navigation.Routes
import java.time.LocalDate

@Composable
fun CreateProjectScreen(
    viewModel: CreateProjectViewModel,
    navController: NavController
) {
    var name by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var selectedType by remember {
        mutableStateOf(ProjectStatus.INACTIVE)
    }

    var startDate by remember {
        mutableStateOf<LocalDate>(LocalDate.now())
    }

    var endDate by remember {
        mutableStateOf<LocalDate>(LocalDate.now())
    }

    var investiment by remember {
        mutableStateOf<String>("")
    }

    var ideaList by remember {
        mutableStateOf<List<Idea>>(
            emptyList()
        )
    }

    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {

        TopNavigation(
            title = "Criar Projeto",
            onBackPressed = {
                navController.popBackStack()
            }
        )

        Spacer(modifier = Modifier.height(60.dp))

        TitleInput(
            name,
            onValueChange = {
                name = it
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Input(
            label = "Descrição",
            placeholder = "Descrição do projeto aqui.",
            value = description,
            onValueChange = {
                description = it
            },
            minLines = 4
        )

        Spacer(modifier = Modifier.height(16.dp))

        Dropdown(
            options = ProjectStatus.entries,
            selected = selectedType,
            onOptionSelected = {
                selectedType = it
            },
            label = {
                when (it) {
                    ProjectStatus.ACTIVE -> "Ativo"
                    ProjectStatus.INACTIVE -> "Inativo"
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {

            DateInput(
                label = "Data inicio",
                value = startDate,
                onDateSelected = {
                    startDate = it
                },
                modifier = Modifier.weight(1f)
            )

            DateInput(
                label = "Data fim",
                value = endDate,
                onDateSelected = {
                    endDate = it
                },
                modifier = Modifier.weight(1f)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        Input(
            label = "Investimento",
            placeholder = "R$ 0,00",
            value = investiment.toString(),
            onValueChange = {
                investiment = it
            },
            keyboardType = KeyboardType.Number
        )

        Spacer(modifier = Modifier.height(40.dp))

        CustomButton(
            text = "Entrar",
            onClick = {
                viewModel.createProject(
                    name,
                    description,
                    selectedType,
                    startDate,
                    endDate,
                    investiment.toFloat(),
                    ideaList
                )
            }
        )

        when(state) {
            is CreateProjectUiState.Loading -> {
                CircularProgressIndicator()
            }

            is CreateProjectUiState.Error -> {
                Spacer(modifier = Modifier.height(12.dp))
                Text(state.message)
            }

            else -> Unit
        }

        LaunchedEffect(state) {
            if (state is CreateProjectUiState.Success){
                // TODO: change for project list
                navController.navigate(
                    Routes.MANAGER_HOME
//                    "${Routes.VIEW_PROJECT}"
                ) {
                    popUpTo(Routes.CREATE_PROJECT) {
                        inclusive = true
                    }
                }
            }
        }
    }
}