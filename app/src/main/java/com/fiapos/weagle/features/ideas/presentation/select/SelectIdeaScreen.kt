package com.fiapos.weagle.features.ideas.presentation.select

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.features.ideas.presentation.list.ListIdeasViewModel
import com.fiapos.weagle.presentation.components.IdeaItem
import com.fiapos.weagle.presentation.components.TopNavigation
import com.fiapos.weagle.ui.theme.AppColorScheme
import java.util.Date

@Composable
fun SelectIdeaScreen(
    viewModel: SelectIdeaViewModel,
    navigationController: NavController
) {

    var ideas = viewModel.loadIdeas()

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
                .clip(RoundedCornerShape(size = 16.dp))
                .background(AppColorScheme.secondary)
                .fillMaxSize()
                .padding(16.dp)
        ) {
            items(ideas) { idea ->
                IdeaItem(
                    title = idea.title,
                    tag = idea.type.label,
                    description = idea.description,
                    createdBy = idea.createdBy,
                    createdAt = Date(),
                    votes = idea.votes,
                    modifier = Modifier,
                    isSelectable = idea.isSelectable,
                    isSelected = idea.isSelected
                )

                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}