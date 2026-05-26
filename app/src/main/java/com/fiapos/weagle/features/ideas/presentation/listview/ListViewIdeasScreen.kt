package com.fiapos.weagle.features.ideas.presentation.listview

import SegmentedControl
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.presentation.components.CustomButton
import com.fiapos.weagle.presentation.components.IdeaItem
import com.fiapos.weagle.presentation.components.TopNavigation
import com.fiapos.weagle.presentation.navigation.Routes
import java.util.Date

@Composable
fun ListViewIdeasScreen(
    viewModel: ListViewIdeasViewModel,
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
            title = "Projetos",
            onBackPressed = {
                navigationController.popBackStack();
            }
        )

        Spacer(modifier = Modifier.height(24.dp))

        if (ideas.isEmpty()) {

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

//            SegmentedControl(
//                options = listOf("Todas", "Criadas por mim"),
//                selectedOption = "Todas",
//                onOptionSelected = { }
//            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
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
                        onClick = {
                            navigationController.navigate(
                                "${Routes.VIEW_IDEA}/${idea.id}"
                            )
                        }
                    )

                    Spacer(modifier = Modifier.height(12.dp))
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))


        CustomButton(
            text = "Adicionar ideia",
            onClick = {
                navigationController.navigate(Routes.CREATE_IDEA)
            }
        )
    }
}