package com.example.habitsmatter.uiElements

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.habitsmatter.data.HabitViewModel
import com.example.habitsmatter.methods.autoSave
import kotlin.math.roundToInt

@Composable
fun ProgressWriterView(id: Int, viewModel: HabitViewModel) {
    // TODO: Database values seem to stay correct, but visually the progress bar stays in the last
    //  state it was left in, regardless of which Habit is currently open.
    var sliderPosition by remember { mutableFloatStateOf(0f) }
    Column {
        Slider(
            value = viewModel.habitProgressState.value.toFloat(),
            onValueChange = {
                sliderPosition = it.roundToInt().toFloat()
                viewModel.onHabitProgressChanged(it.roundToInt())
                autoSave(id, viewModel)
            },
            colors = SliderDefaults.colors(
                thumbColor = MaterialTheme.colorScheme.secondary,
                activeTrackColor = MaterialTheme.colorScheme.secondary,
                inactiveTrackColor = MaterialTheme.colorScheme.secondaryContainer,
            ),
            steps = 99,
            valueRange = 0f..100f
        )
        Text(text = viewModel.habitProgressState.value.toString())
    }
}