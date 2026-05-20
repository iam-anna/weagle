package com.fiapos.weagle.features.projects.presentation.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.domain.models.ProjectStatus
import com.fiapos.weagle.presentation.components.DateInput
import com.fiapos.weagle.presentation.components.Dropdown
import com.fiapos.weagle.presentation.components.Input
import com.fiapos.weagle.presentation.components.TitleInput
import com.fiapos.weagle.presentation.components.TopNavigation
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

    var selectedDate by remember {
        mutableStateOf<LocalDate?>(null)
    }

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

        Row {

            DateInput(
                label = "Prazo",
                value = selectedDate,
                onDateSelected = {
                    selectedDate = it
                }
            )

            DateInput(
                label = "Prazo",
                value = selectedDate,
                onDateSelected = {
                    selectedDate = it
                }
            )
        }
    }
}