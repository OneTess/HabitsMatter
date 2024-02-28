package com.example.habitsmatter.uiElements

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import com.example.habitsmatter.R
import com.example.habitsmatter.data.HabitData
import com.example.habitsmatter.data.HabitViewModel
import com.example.habitsmatter.statics.Constants

@Composable
fun RadioButtons(
    viewModel: HabitViewModel,
    onTypeChange: (String) -> Unit
) {
    val radioOptions = listOf<String>("binary", "enumerable")
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(radioOptions[0])
    }
    Row(
        horizontalArrangement = Arrangement.Center
    ) {
        radioOptions.forEach {
            text ->
            Surface(
                shape = RoundedCornerShape(100),
                color = if (selectedOption == text) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                },
                contentColor = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = Constants.paddingMedium)
                    .clickable {
                        // TODO: Consider implementing saving all Habit's data at once, not every
                        //  individual element on its own. Just think if it will actually be a good idea.
                        Log.d("RadioButtons", "Surface .clickable triggered")
                        onOptionSelected(text)
                        onTypeChange(text)
                    }
            ) {
                Row(
                    modifier = Modifier
                        .padding(horizontal = Constants.paddingMedium),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                    // TODO: Center the text
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center,
                        modifier = Modifier.weight(1f)
                    ) {
                        Text(
                            text = when (text) {
                                "binary" -> { "Yes or No" }
                                "enumerable" -> { "Number" }
                                else -> { "Error!" }
                            },
                            style = MaterialTheme.typography.bodyMedium.merge(), // TODO: Replace all text fields' text sizes with MaterialTheme.typography
                            color = colorResource(id = R.color.purple_200), // TODO: This is a testing color.
                            modifier = Modifier.padding(Constants.paddingMedium)
                        )
                    }
                    Row(
                        // TODO: When clicking the button, there is a slight hint of the
                        //  RadioButtons flashing through the button's surface. Just try
                        //  setting it to completely transparent.
                        //
                        // TODO: Just try removing the RadioButton. If not all, most part of the
                        //  needed logic is already implemented in Surface. The rest should be
                        //  fairly easy.
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        RadioButton(
                            selected = (text == selectedOption),
                            onClick = {
                                      Log.d("RadioButtons", "RadioButton onClick triggered")
                                /*onOptionSelected(text)
                                onTypeChange(text)*/
                            },
                            colors = RadioButtonDefaults.colors(
                                selectedColor = MaterialTheme.colorScheme.primary,
                                unselectedColor = MaterialTheme.colorScheme.onSurface
                            ),
                        )
                    }
                }
            }
        }
    }
    val onTypeChange: (String) -> Unit = { newType ->
        // Update HabitData in Room database
        viewModel.editHabit(HabitData(type = newType))
    }
}