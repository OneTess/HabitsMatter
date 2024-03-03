package com.example.habitsmatter.data

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.habitsmatter.Graph
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.launch

class HabitViewModel(private val habitRepository: HabitRepository = Graph.habitRepository) : ViewModel() {
    private val _habitTitleState = MutableStateFlow("")
    private val _habitContentState = MutableStateFlow("")
    private val _habitTypeState = MutableStateFlow("binary")
    private val _habitProgressState = MutableStateFlow(0)

    val habitTitleState: StateFlow<String> = _habitTitleState
    val habitContentState: StateFlow<String> = _habitContentState
    val habitTypeState: StateFlow<String> = _habitTypeState
    val habitProgressState: StateFlow<Int> = _habitProgressState

    var idMax by mutableStateOf(0)

    lateinit var getAllHabits: Flow<List<HabitData>>

    init {
        getAllHabits = habitRepository.getAllHabits()
        viewModelScope.launch {
            @OptIn(FlowPreview::class)
            _habitTitleState.debounce(1000).collect()
            _habitContentState.debounce(1000).collect()
        }
    }

    fun onHabitTitleChanged(newString: String) {
        _habitTitleState.value = newString
    }

    fun onHabitContentsChanged(newString: String) {
        _habitContentState.value = newString
    }

    fun onHabitTypeChanged(newString: String) {
        _habitTypeState.value = newString
    }

    fun onHabitProgressChanged(newProgressValue: Int) {
        _habitProgressState.value = newProgressValue
    }

    fun addHabit(habitData: HabitData) {
        viewModelScope.launch(Dispatchers.IO) {
            idMax = habitRepository.insertHabit(habitData)
        }
    }

    fun editHabit(habit: HabitData) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.updateHabit(habit)
        }
    }

    fun deleteHabit(habit: HabitData) {
        viewModelScope.launch(Dispatchers.IO) {
            habitRepository.deleteHabit(habit)
        }
    }

    fun getHabit(id: Int): Flow<HabitData> {
        return habitRepository.getHabit(id)
    }

    fun getAllHabits(): Flow<List<HabitData>> {
        return habitRepository.getAllHabits()
    }
}