package com.fiapos.weagle.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fiapos.weagle.R

@Composable
fun TitleInput(
    value: String,
    onValueChange: (String) -> Unit
) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            singleLine = true,
            textStyle = MaterialTheme.typography.titleLarge,
            decorationBox = { innerTextField ->
                if (value.isEmpty()) {
                    Text(
                        text = "Título da ideia",
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.tertiary
                    )
                }

                innerTextField()
            }
        )
        Spacer(modifier = Modifier.width(8.dp))

        Image(
            painter = painterResource(id = R.drawable.pencil),
            contentDescription = "Back"
        )
    }
}