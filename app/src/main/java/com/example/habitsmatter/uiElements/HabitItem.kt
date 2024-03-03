package com.example.habitsmatter.uiElements

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.habitsmatter.R
import com.example.habitsmatter.data.HabitData
import com.example.habitsmatter.statics.Constants

@Composable
fun HabitItem(
    habitData: HabitData,
    onItemClick: () -> Unit,
    onDeleteButtonClick: () -> Unit,
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = Constants.paddingMedium)
            .clickable { onItemClick() },
        elevation = CardDefaults.cardElevation(
            defaultElevation = Constants.elevationMedium
        ),
        shape = RoundedCornerShape(Constants.roundedCornerDpSmall),
        colors = CardDefaults.cardColors(
            containerColor = colorResource(id = R.color.background_primary),
            contentColor = colorResource(id = R.color.text_primary)
        ),
    ) {
        Column(
            modifier = Modifier.padding(
                top = Constants.paddingLarge,
                bottom = Constants.paddingLarge,
                start = Constants.paddingExtraLarge,
                end = Constants.paddingExtraLarge
            ),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Center
        ) {
            // Title text field
            Text(
                text = habitData.title,
                fontWeight = FontWeight.ExtraBold,
                fontSize = Constants.fontSizeLarge,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = androidx.compose.ui.Modifier.padding(Constants.paddingExtraSmall))
            // Details text field
            Text(
                text = habitData.content,
                fontSize = Constants.fontSizeMedium,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Row(
                modifier = Modifier
                    .heightIn(max = 28.dp)
                    .fillMaxWidth()
                    .padding(bottom = Constants.paddingSmall),
                horizontalArrangement = Arrangement.End
            ) {
                IconButton(
                    onClick = { onDeleteButtonClick() }
                ) {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Delete HabitData"
                    )
                }
            }

            ProgressIndicator(progressType = "binary", progressValue = 1) // TODO: Pass the Habit data to the HabitItem to display the right type of the progress indicator
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HabitItemPreview() {
    val sampleHabitData = HabitData(title = "Exercise", content = "It's push day today")
    HabitItem(habitData = sampleHabitData, onItemClick = {}, onDeleteButtonClick = {})
}
