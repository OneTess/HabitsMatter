package com.example.habitsmatter.data

import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {
    suspend fun insertNote(noteData: HabitData): Int {
        return habitDao.insert(noteData).toInt()
    }

    suspend fun deleteNote(noteData: HabitData) {
        return habitDao.delete(noteData)
    }

    suspend fun updateNote(noteData: HabitData) {
        return habitDao.update(noteData)
    }

    fun getNote(id: Int) : Flow<HabitData> {
        return habitDao.getHabit(id)
    }

    fun getAllNotes() : Flow<List<HabitData>> {
        return habitDao.getAllHabits()
    }

    suspend fun getBiggestId() : Int {
        return habitDao.getBiggestId()
    }
}