package com.example.habitsmatter.uiElements

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.habitsmatter.statics.Constants

// TODO: Make those RadioButtons change the Note's type.
@Composable
fun RadioButtons() {
    val radioOptions = listOf<String>("binary", "enumerable") // TODO: Use those only to define the button type and set a condition to display some custom text depending on the type.
    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(radioOptions[1])
    }
    Row {
        radioOptions.forEach {
            text ->
            Row(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .selectable(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        }
                    )
                    .padding(horizontal = Constants.paddingMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (text == selectedOption),
                    onClick = {
                        onOptionSelected(text)
                    }
                )
                Text(
                    text = text,
                    style = MaterialTheme.typography.bodyMedium.merge(), // TODO: Replace all text fields' text sizes with MaterialTheme.typography
                    modifier = Modifier.padding(Constants.paddingMedium)
                )
            }
        }
    }
}