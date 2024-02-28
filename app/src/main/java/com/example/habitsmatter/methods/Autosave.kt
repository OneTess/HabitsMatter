package com.example.habitsmatter.methods

import com.example.habitsmatter.data.HabitData
import com.example.habitsmatter.data.HabitViewModel

// TODO: Disallow saving with empty title
fun autoSave(
    id: Int,
    viewModel: HabitViewModel,
) {
    var newId = id
    if (id == 0) {
        newId = viewModel.idMax
    }
    viewModel.editHabit(
        HabitData(
            id = newId,
            title = viewModel.habitTitleState.value.trim(),
            content = viewModel.habitContentState.value.trim(),
            type = viewModel.habitTypeState.value
        )
    )
}