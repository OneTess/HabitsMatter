package com.example.habitsmatter.uiElements

import androidx.compose.runtime.Composable

@Composable
fun ProgressIndicator(progressType: String, progressValue: Int) {
    if (progressType == "binary") {
        CheckboxView(progressValue)
    } else if (progressType == "enumerable") {
        // Progress bar -- most probably linear, maybe circular.
    } else {
        // Something might be there. Or not.
    }
}