package com.example.habitsmatter.uiElements

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun ProgressIndicator(progressType: String, progressValue: Int) {
    if (progressType == "binary") {
        CheckboxView(progressValue)
    } else if (progressType == "enumerable") {
        Text(text = "Progress: $progressValue")
    } else {
        // Something might be there. Or not.
    }
}