package com.fiapos.weagle.features.projects.presentation.listview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.presentation.components.TopNavigation

@Composable
fun ListViewProjectsScreen(
    viewModel: ListViewProjectsViewModel,
    navigationController: NavController
) {

    val projects = viewModel.projects

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {
        TopNavigation(
            title = "Ideias",
            onBackPressed = {
                navigationController.popBackStack();
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (projects.isEmpty()) {

            Spacer(modifier = Modifier.height(136.dp))

            Text(
                text = "404",
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Opa, parece que não temos nada por aqui",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.secondary
            )
        } else {
            // TODO: project list here
        }
    }
}