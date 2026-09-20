package com.fiapos.weagle.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.fiapos.weagle.ui.theme.CombinatedColor

@Composable
fun Tag(
    label: String,
    background: Color = CombinatedColor.lightBlue,
    foreground: Color = CombinatedColor.lightBlueForeground,
) {
    Box(
        modifier = Modifier
            .background(
                color = background,
                shape = RoundedCornerShape(6.dp)
            )
            .padding(vertical = 4.dp, horizontal = 6.dp),
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyMedium,
            color = foreground
        )
    }
}