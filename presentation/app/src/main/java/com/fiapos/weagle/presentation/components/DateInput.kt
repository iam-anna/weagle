package com.fiapos.weagle.presentation.components

import android.icu.util.Calendar
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fiapos.weagle.R
import kotlinx.coroutines.selects.select
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DateInput(
   label: String,
   value: LocalDate?,
   onDateSelected: (LocalDate) -> Unit,
   modifier: Modifier
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

    Column (modifier = modifier) {
        Text(
            text = label,
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary
        )

        Spacer(Modifier.height(4.dp))

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
                   datePickerDialog.show()
               }
       ) {
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
                   painter = painterResource(id = R.drawable.calendar),
                   contentDescription = "Calendar"
               )
           }

           Box(
               modifier = Modifier
                   .height(48.dp)
                   .width(124.dp)
                   .padding(horizontal = 12.dp, vertical = 10.dp),
               Alignment.CenterStart
           ) {
               Text(
                   text = value?.format(
                       DateTimeFormatter.ofPattern(
                           "d/M/yyyy"
                       )
                   ) ?: "",
                   style = MaterialTheme.typography.bodyLarge,
                   color = MaterialTheme.colorScheme.primary
               )
           }
       }

//        OutlinedTextField(
//            value = ,
//            onValueChange = {},
//            readOnly = true,
//            placeholder = {},
//            leadingIcon = {
//                Image(
//                    painter = painterResource(id = R.drawable.calendar),
//                    contentDescription = "Back"
//                )
//            },
//            label = {
//                Text(label)
//            },
//            modifier = Modifier
//                .fillMaxWidth()
//                .clickable {
//
//                },
//            singleLine = true
//        )
    }
}