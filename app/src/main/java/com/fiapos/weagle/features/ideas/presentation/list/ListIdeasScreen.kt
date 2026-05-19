package com.fiapos.weagle.features.ideas.presentation.list

import SegmentedControl
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.domain.models.Idea
import com.fiapos.weagle.presentation.components.IdeaItem
import com.fiapos.weagle.presentation.components.TopNavigation
import java.util.Date

@Composable
fun ListIdeasScreen(
    viewModel: ListIdeasViewModel,
    navigationController: NavController
) {

    var ideas = viewModel.loadIdeas()

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 32.dp, horizontal = 24.dp)
    ) {
        TopNavigation(
            title = "Ideias",
            onBackPressed = {
//                navController.popBackStack();
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        SegmentedControl(
            options = listOf("Todas", "Criadas por mim"),
            selectedOption = "Todas",
            onOptionSelected = { }
        )

        Spacer(modifier = Modifier.height(24.dp))

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            items(ideas) { idea ->
                IdeaItem(
                    title = idea.title,
                    tag = idea.type.label,
                    description = idea.description,
                    createdBy = idea.createdBy,
                    createdAt = Date(),
                    votes = idea.votes,
                    modifier = Modifier
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }


    }
}