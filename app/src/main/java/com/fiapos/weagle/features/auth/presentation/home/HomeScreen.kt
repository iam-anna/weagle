package com.fiapos.weagle.features.auth.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.fiapos.weagle.features.auth.data.domain.models.UserRole
import com.fiapos.weagle.presentation.components.CustomButton
import com.fiapos.weagle.presentation.components.Tag
import com.fiapos.weagle.presentation.navigation.Routes

@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    navigationController: NavController
) {
    val user = viewModel.user

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 32.dp)
    ) {

        Spacer(modifier = Modifier.height(160.dp))

        if(user == null) {
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

            Tag(label = user.role.label)

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = user.name,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(80.dp))

            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                if (user.role == UserRole.OPERATOR || user.role == UserRole.MANAGER) {
                    CustomButton(
                        text = "Lista de ideias",
                        onClick = {
                            navigationController.navigate(Routes.LIST_IDEAS)
                        }
                    )
                }

                if (user.role == UserRole.MANAGER || user.role == UserRole.LEADER) {
                    CustomButton(
                        text = "Lista de projetos",
                        onClick = {
                            // TODO: navigate do list project
                        }
                    )
                }

                CustomButton(
                    text = "Orientações Estratégicas",
                    onClick = {
                        navigationController.navigate(Routes.LIST_STRATEGIC_ORIENTATIONS)
                    }
                )
            }
        }
    }
}