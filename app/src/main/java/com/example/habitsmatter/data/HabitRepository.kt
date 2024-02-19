package com.example.habitsmatter.data

import kotlinx.coroutines.flow.Flow

class HabitRepository(private val habitDao: HabitDao) {
    suspend fun insertHabit(noteData: HabitData): Int {
        return habitDao.insert(noteData).toInt()
    }

    suspend fun deleteHabit(noteData: HabitData) {
        return habitDao.delete(noteData)
    }

    suspend fun updateHabit(noteData: HabitData) {
        return habitDao.update(noteData)
    }

    fun getHabit(id: Int) : Flow<HabitData> {
        return habitDao.getHabit(id)
    }

    fun getAllHabits() : Flow<List<HabitData>> {
        return habitDao.getAllHabits()
    }

    suspend fun getBiggestId() : Int {
        return habitDao.getBiggestId()
    }
}