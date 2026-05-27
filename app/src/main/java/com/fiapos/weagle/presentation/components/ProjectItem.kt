package com.fiapos.weagle.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.fiapos.weagle.features.projects.data.domain.Project
import java.time.format.DateTimeFormatter

@Composable
fun ProjectItem(
    project: Project,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val dateFormatter = DateTimeFormatter.ofPattern("d/M/yyyy")

    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(4.dp))
            .clickable { onClick() }
            .background(MaterialTheme.colorScheme.surface)
            .border(1.dp, MaterialTheme.colorScheme.outline, RoundedCornerShape(4.dp))
            .padding(16.dp)
    ) {
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = project.name,
                    color = MaterialTheme.colorScheme.primary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.weight(1f)
                )
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )
            }

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 12.dp),
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f)
            )

            Row(modifier = Modifier.fillMaxWidth()) {
                InfoItem(label = "Cliente:", value = project.ownedBy, modifier = Modifier.weight(1.3f))
                InfoItem(label = "Due:", value = project.endDate.format(dateFormatter), modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(8.dp))

            Row(modifier = Modifier.fillMaxWidth()) {
                InfoItem(label = "Start:", value = project.startDate.format(dateFormatter), modifier = Modifier.weight(1f))
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = "Status: ",
                    color = MaterialTheme.colorScheme.tertiary,
                    fontSize = 13.sp
                )
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(4.dp))
                        .background(Color(0xFFD9E95F)) // Cor lima para o destaque do status
                        .padding(horizontal = 8.dp, vertical = 2.dp)
                ) {
                    Text(
                        text = project.status.label,
                        color = MaterialTheme.colorScheme.primary,
                        fontSize = 11.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun InfoItem(label: String, value: String, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text(
            text = label,
            color = MaterialTheme.colorScheme.tertiary,
            fontSize = 13.sp,
            modifier = Modifier.padding(end = 4.dp)
        )
        Text(
            text = value,
            color = MaterialTheme.colorScheme.secondary,
            fontSize = 13.sp,
            fontWeight = FontWeight.Medium
        )
    }
}
