package com.example.habitsmatter.uiElements

import androidx.compose.runtime.Composable

@Composable
fun ProgressIndicator(type: String) {
    if (type == "binary") {
        CheckboxView()
    } else if (type == "enumerable") {
        // Progress bar -- most probably linear, maybe circular.
    } else {
        // Something might be there. Or not.
    }
}