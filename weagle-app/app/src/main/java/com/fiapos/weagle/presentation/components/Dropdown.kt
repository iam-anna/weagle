package com.fiapos.weagle.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.fiapos.weagle.R

@Composable
fun <T> Dropdown(
    options: List<T>,
    selected: T,
    onOptionSelected: (T) -> Unit,
    label: (T) -> String
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Column {
        Row(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.background)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(4.dp)
                )
                .clip(RoundedCornerShape(4.dp))
                .clickable {
                    expanded = true
                }
        ) {
            Box(
                modifier = Modifier
                    .height(48.dp)
                    .width(106.dp)
                    .padding(horizontal = 12.dp, vertical = 10.dp),
                Alignment.CenterStart
            ) {
                Text(
                    text = label(selected),
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.primary
                )
            }

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline
                    ),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.caret_down),
                    contentDescription = "Dropdown"
                )
            }
        }

        DropdownMenu(
            modifier = Modifier
                .width(106.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.outline,
                    shape = RoundedCornerShape(
                        bottomStart = 4.dp,
                        bottomEnd = 4.dp
                    )
                )
                .background(
                    color = MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(
                        bottomStart = 4.dp,
                        bottomEnd = 4.dp
                    )
                ),
            offset = DpOffset(
                x = 0.dp,
                y = (-2).dp,
            ),
            shadowElevation = 0.dp,
            tonalElevation = 0.dp,
            expanded = expanded,
            onDismissRequest = {
                expanded = false
            }
        ) {
            options.forEach { option ->
                DropdownMenuItem(
                    modifier = Modifier.height(40.dp),
                    text = {
                        Text(
                            text = label(option),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.primary
                        )
                    },

                    onClick = {
                        onOptionSelected(option)
                        expanded = false
                    },
                )
            }
        }
    }
}