package com.fiapos.weagle.features.so.presentation.create

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationCategory
import com.fiapos.weagle.features.so.data.domain.StrategicOrientationStatus
import com.fiapos.weagle.presentation.components.CustomButton
import com.fiapos.weagle.presentation.components.Dropdown
import com.fiapos.weagle.presentation.components.Input
import com.fiapos.weagle.presentation.components.TitleInput
import com.fiapos.weagle.presentation.components.TopNavigation
import com.fiapos.weagle.presentation.navigation.Routes

@Composable
fun CreateStrategicOrientationScreen(
    viewModel: CreateStrategicOrientationViewModel,
    navigationController: NavController
) {

    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var selectedCategory by remember {
        mutableStateOf(StrategicOrientationCategory.INNOVATION)
    }

    var selectedStatus by remember {
        mutableStateOf(StrategicOrientationStatus.ACTIVE)
    }

    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {

        TopNavigation(
            title = "Criar Orientação Estratégica",
            onBackPressed = {
                navigationController.popBackStack();
            }
        )

        Spacer(modifier = Modifier.height(160.dp))

        TitleInput(
            title,
            placeholder = "Título da orientação",
            onValueChange = {
                title = it
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        Input(
            label = "Descrição",
            placeholder = "Descrição da orientação estratégica aqui.",
            value = description,
            onValueChange = {
                description = it
            },
            minLines = 4
        )

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Dropdown(
                options = StrategicOrientationCategory.entries,
                selected = selectedCategory,
                onOptionSelected = {
                    selectedCategory = it
                },
                label = {
                    it.label
                }
            )

            Spacer(modifier = Modifier.width(36.dp))

            Dropdown(
                options = StrategicOrientationStatus.entries,
                selected = selectedStatus,
                onOptionSelected = {
                    selectedStatus = it
                },
                label = {
                    it.label
                }
            )
        }

        Spacer(modifier = Modifier.height(80.dp))

        CustomButton(
            text = "Criar orientação",
            onClick = {
                viewModel.createStrategicOrientation(
                    title,
                    description,
                    selectedCategory,
                    selectedStatus
                )
            }
        )

        when(state) {

            is CreateStrategicOrientationUiState.Loading -> {
                CircularProgressIndicator()
            }

            is CreateStrategicOrientationUiState.Error -> {
                Spacer(modifier = Modifier.height(12.dp))

                Text(state.message)
            }

            is CreateStrategicOrientationUiState.Success -> {
                navigationController.navigate(Routes.LIST_STRATEGIC_ORIENTATION) {
                    popUpTo(Routes.CREATE_STRATEGIC_ORIENTATION) {
                        inclusive = true
                    }
                }
            }

            else -> Unit
        }
    }
}