package com.fiapos.weagle.features.projects.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.presentation.components.Tag
import com.fiapos.weagle.presentation.components.TopNavigation

@Composable
fun ViewProjectScreen(
    viewModel: ViewProjectViewModel,
    navController: NavController
) {
    val project = viewModel.project

    // TODO: navigate to 404
    project ?: return

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {
        TopNavigation(
            title = "Visualizar Projeto",
            onBackPressed = {
                navController.popBackStack()
            },
            actionable = true,
            onActionablePressed = {
                // TODO: add actionable (editing) if current user == project owner
                navController.popBackStack()
            }
        )

        Spacer(modifier = Modifier.height(100.dp))

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Tag(label = project.status.label)

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = project.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = project.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary,
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Início em ",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = project.startDate.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Previsto para",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = project.endDate.toString(),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Gestor Responsável",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = project.ownedBy,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Text(
                text = "Investimento",
                style = MaterialTheme.typography.labelMedium,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Investimento",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "R$ 2,00",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Retorno",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "R$ 3,00",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "ROI",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "R$ 1,00",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Ganho de produtvidade",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = "10h/semana",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(4.dp))

            // TODO: add ideas list
        }
    }
}