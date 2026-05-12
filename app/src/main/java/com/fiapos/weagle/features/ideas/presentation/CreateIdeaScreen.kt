package com.fiapos.weagle.features.ideas.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.fiapos.weagle.domain.models.IdeaType
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text

@Composable
fun CreateIdeaScreen(
    viewModel: CreateIdeaViewModel
) {
    var title by remember {
        mutableStateOf("")
    }

    var description by remember {
        mutableStateOf("")
    }

    var selectedType by remember {
        mutableStateOf(IdeaType.IDEA)
    }

    val state = viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Create Idea",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = title,
            onValueChange = {
                title = it
            },
            label = {
                Text("Title")
            },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = description,
            onValueChange = {
                description = it
            },
            label = {
                Text("Description")
            },
            modifier = Modifier.fillMaxWidth(),
            minLines = 4
        )

        Spacer(modifier = Modifier.height(12.dp))

        Row {

            Button(
                onClick = {
                    selectedType = IdeaType.IDEA
                }
            ) {
                Text("Idea")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(
                onClick = {
                    selectedType = IdeaType.PROBLEM
                }
            ) {
                Text("Problem")
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {

                viewModel.createIdea(
                    title,
                    description,
                    selectedType
                )
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Submit")
        }

        when (state) {

            is CreateIdeaUiState.Success -> {

                Spacer(modifier = Modifier.height(12.dp))

                Text("Idea created successfully")
            }

            is CreateIdeaUiState.Error -> {

                Spacer(modifier = Modifier.height(12.dp))

                Text(state.message)
            }

            else -> Unit
        }
    }
}
