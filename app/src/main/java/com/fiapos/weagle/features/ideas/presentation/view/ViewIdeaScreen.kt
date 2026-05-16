package com.fiapos.weagle.features.ideas.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.presentation.components.Tag
import com.fiapos.weagle.presentation.components.TopNavigation
import com.fiapos.weagle.presentation.components.VoteFragment

@Composable
fun ViewIdeaScreen(
    viewModel: ViewIdeaViewModel,
    navController: NavController
) {
    val idea = viewModel.idea

    var votes by remember {
        mutableStateOf(0)
    }

    if(idea == null) {
        // TODO: navigate to 404
        return
    }



    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {
        TopNavigation(
            title = "Visualizar Ideia",
            onBackPressed = {
                navController.popBackStack();
            },
            actionable = true,
            onActionablePressed = {
                // TODO: add actionable (editing) if current user == idea owner
                navController.popBackStack();
            }
        )

        Spacer(modifier = Modifier.height(120.dp))


        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(
                        text = "Criado em",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.secondary
                    )

                    Text(
                        text = idea.createdAt.toString(),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                }

                Tag(label = idea.type.label)
            }

            Spacer(modifier = Modifier.height(16.dp))

            Text(
                text = idea.title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = idea.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.primary
            )

            if (idea.isEdited) {
                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Editado",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.tertiary
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(
                    text = "Criado por",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.secondary
                )

                Text(
                    text = idea.createdBy,
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Spacer(modifier = Modifier.height(32.dp))

            VoteFragment(
                votes = votes,
                onUpvote = {
                    viewModel.upvoteIdea()
                },
                onDownvote = {
                    viewModel.downvoteIdea()
                }
            )
        }
    }
}