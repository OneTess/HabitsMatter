package com.example.habitsmatter.uiElements

import androidx.compose.material3.Checkbox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@Composable
fun CheckboxView(progressValue: Int) {
    var checkedState = remember { mutableStateOf(true) }
    if (progressValue == 0) {
        checkedState = mutableStateOf(false)
    } else {
        checkedState = mutableStateOf(true)
    }
    Checkbox(
        checked = checkedState.value,
        onCheckedChange = { checkedState.value = it }
    )
}