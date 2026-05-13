package com.fiapos.weagle.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fiapos.weagle.R

@Composable
fun TopNavigation(
    title: String,
    onBackPressed: () -> Unit,
    actionable: Boolean = false,
    onActionablePressed: () -> Unit = {}
) {
    Row(modifier = Modifier
        .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(modifier = Modifier
            .size(size = 44.dp)
            .clip(RoundedCornerShape(4.dp))
            .clickable {
                onBackPressed()
            },
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.angle_left),
                contentDescription = "Back"
            )
        }

        Text(
            text = title,
            style = MaterialTheme.typography.labelLarge
        )

        Box(modifier = Modifier
            .size(size = 44.dp)
            .clickable {
                onActionablePressed()
            },
            contentAlignment = Alignment.Center
        ) {
            if (actionable) {
                Image(
                    painter = painterResource(id = R.drawable.angle_left),
                    contentDescription = "Back"
                )
            }
        }
    }
}