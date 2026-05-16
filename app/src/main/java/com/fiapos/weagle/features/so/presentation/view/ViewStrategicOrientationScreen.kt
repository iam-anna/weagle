package com.fiapos.weagle.features.so.presentation.view

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.fiapos.weagle.features.so.data.StrategicOrientationRepository
import com.fiapos.weagle.presentation.components.TopNavigation
import com.fiapos.weagle.ui.theme.toColor

@Composable
fun ViewStrategicOrientationScreen(
    viewModel: ViewStrategicOrientationViewModel,
    navController: NavController
) {
    val orientation = viewModel.orientation

    if(orientation == null) {
        // TODO: navigate to 404
        return
    }

    Column(
        modifier = Modifier
            .background(orientation.category.toColor().background)
            .fillMaxSize()
            .padding(vertical = 64.dp, horizontal = 24.dp)
    ) {

        TopNavigation(
            title = "Orientações Estratégicas",
            onBackPressed = {
                navController.popBackStack()
            }
        )

    }
}