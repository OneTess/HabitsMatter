package com.example.habitsmatter.methods

import com.example.habitsmatter.data.HabitData
import com.example.habitsmatter.data.HabitViewModel

fun autoSave(
    id: Int,
    viewModel: HabitViewModel,
) {
    var newId = id
    if (id == 0) {
        newId = viewModel.idMax
        viewModel.editHabit(
            HabitData(
                id = newId,
                title = viewModel.habitTitleState.value.trim(),
                content = viewModel.habitContentState.value.trim()
            )
        )
    } else {
        viewModel.editHabit(
            HabitData(
                id = newId,
                title = viewModel.habitTitleState.value.trim(),
                content = viewModel.habitContentState.value.trim()
            )
        )
    }
}