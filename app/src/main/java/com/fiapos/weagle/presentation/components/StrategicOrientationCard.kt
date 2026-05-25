package com.fiapos.weagle.presentation.components

import androidx.compose.runtime.Composable
import com.fiapos.weagle.features.so.data.domain.StrategicOrientation
import com.fiapos.weagle.ui.theme.toColor

@Composable
fun StrategicOrientationCard(
    orientation: StrategicOrientation,
    onClick: () -> Unit
) {
    val colors = orientation.category.toColor()

    ListItem(
        label = orientation.title,
        description = orientation.description,
        background = colors.background,
        foreground = colors.foreground,
        border = colors.border,
        onClick = onClick
    )
}