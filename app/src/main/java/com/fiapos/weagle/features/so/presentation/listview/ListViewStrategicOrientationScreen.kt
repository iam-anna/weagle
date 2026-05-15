package com.fiapos.weagle.features.so.presentation.listview

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import com.fiapos.weagle.presentation.components.ListItem
import com.fiapos.weagle.presentation.components.StrategicOrientationCard
import com.fiapos.weagle.presentation.components.TopNavigation

@Composable
fun ListViewStrategicOrientationScreen(
    viewModel: ListViewStrategicOrientationViewModel,
    navController: NavController
) {

    val orientations = viewModel.orientations

    Column(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp),
    ) {
        TopNavigation(
            title = "Orientações Estratégicas",
            onBackPressed = {
                navController.popBackStack()
            }
        )

        Spacer(modifier = Modifier.height(32.dp))

        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(orientations) { strategicOrientation ->
                StrategicOrientationCard(
                    orientation = strategicOrientation,
                    onClick = {
//                        navController.navigate(
//                            "${Routes.VIEW_ORIENTATION}/${orientation.id}"
//                        )
                    }
                )
            }
        }
    }

}