package com.fiapos.weagle.presentation.components

import android.icu.util.Calendar
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
   label: String,
   value: LocalDate?,
   onDateSelected: (LocalDate) -> Unit
) {
    val context = LocalContext.current

    val calendar = Calendar.getInstance()

    val datePickerDialog = android.app.DatePickerDialog(
        context,
        { _, year, month, dayOfMonth ->

            onDateSelected(
                LocalDate.of(
                    year,
                    month + 1,
                    dayOfMonth
                )
            )
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    OutlinedTextField(
        value = value?.format(
            DateTimeFormatter.ofPattern(
                "dd/MM/yyyy"
            )
        ) ?: "",
        onValueChange = {},
        readOnly = true,
        label = {
            Text(label)
        },
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                datePickerDialog.show()
            }
    )
}