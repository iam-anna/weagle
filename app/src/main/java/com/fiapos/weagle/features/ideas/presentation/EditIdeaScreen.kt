package com.fiapos.weagle.features.ideas.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.domain.models.IdeaType
import com.fiapos.weagle.presentation.components.CustomButton
import com.fiapos.weagle.presentation.components.Dropdown
import com.fiapos.weagle.presentation.components.Input
import com.fiapos.weagle.presentation.components.TitleInput
import com.fiapos.weagle.presentation.components.TopNavigation

@Composable
fun EditIdeaScreen(
    ideaId: String,
    viewModel: EditIdeaViewModel,
    navController: NavController
){
    var title by remember {
        mutableStateOf("Ideia maneira")
    }

    var description by remember {
        mutableStateOf("Descrição legal")
    }

    var selectedType by remember {
        mutableStateOf(IdeaType.IDEA)
    }

    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {
        TopNavigation(
            title = "Editar Ideia",
            onBackPressed = {
                navController.popBackStack();
            }
        )

        Spacer(modifier = Modifier.height(160.dp))

        TitleInput(
            title,
            onValueChange = {
                title = it
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Input(
            label = "Descrição",
            placeholder = "Descrição da ideia aqui.",
            value = description,
            onValueChange = {
                description = it
            },
            minLines = 4
        )

        Spacer(modifier = Modifier.height(16.dp))

        Dropdown(
            options = IdeaType.entries,
            selected = selectedType,
            onOptionSelected = {
                selectedType = it
            },
            label = {
                when (it) {
                    IdeaType.IDEA -> "Ideia"
                    IdeaType.PROBLEM -> "Problema"
                }
            }
        )

        Spacer(modifier = Modifier.height(80.dp))

        CustomButton(
            text = "Atualizar ideia",
            onClick = {
                viewModel.editIdea(
                    id = ideaId,
                    title,
                    description,
                    selectedType
                )
            }
        )

        when(state) {

            is EditIdeaUiState.Loading -> {
                CircularProgressIndicator()
            }

            is EditIdeaUiState.Error -> {
                Spacer(modifier = Modifier.height(12.dp))
                Text(state.message)
            }

            else -> Unit
        }

        LaunchedEffect(state) {
            if (state is EditIdeaUiState.Success) {
                // TODO: change for ideas list
                navController.popBackStack()
            }
        }
    }
}