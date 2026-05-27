package com.fiapos.weagle.features.ideas.presentation.select

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.features.projects.presentation.create.CreateProjectViewModel
import com.fiapos.weagle.presentation.components.CustomButton
import com.fiapos.weagle.presentation.components.IdeaItem
import com.fiapos.weagle.presentation.components.TopNavigation
import com.fiapos.weagle.ui.theme.AppColorScheme
import java.util.Date

@Composable
fun SelectIdeaScreen(
    viewModel: SelectIdeaViewModel,
    projectViewModel: CreateProjectViewModel,
    navigationController: NavController
) {

    val ideas = viewModel.ideas

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {
        TopNavigation(
            title = "Selecionar Ideias",
            onBackPressed = {}
        )

        Spacer(
            modifier = Modifier
                .size(32.dp)
        )

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .clip(RoundedCornerShape(size = 8.dp))
                .background(MaterialTheme.colorScheme.outline)
                .fillMaxSize()
                .padding(horizontal = 6.dp, vertical = 12.dp)
        ) {

            items(ideas) { idea ->
                val isSelected = projectViewModel.isIdeaSelected(
                    idea.id
                )

                IdeaItem(
                    title = idea.title,
                    tag = idea.type.label,
                    description = idea.description,
                    createdBy = idea.createdBy,
                    createdAt = idea.createdAt,
                    votes = idea.votes,
                    modifier = Modifier,
                    isSelectable = true,
                    isSelected = isSelected,
                    onClick = {

                        projectViewModel.toggleIdeaSelection(idea)
                    }
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                CustomButton(
                    text = "Confirmar Seleção",
                    onClick = {
                        navigationController.popBackStack()
                    }
                )
            }
        }
    }
}