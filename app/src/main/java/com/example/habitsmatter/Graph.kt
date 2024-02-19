package com.example.habitsmatter

import android.content.Context
import androidx.room.Room
import com.example.habitsmatter.data.HabitDatabase
import com.example.habitsmatter.data.HabitRepository

object Graph {
    private lateinit var database: HabitDatabase

    val habitRepository by lazy {
        HabitRepository(habitDao = database.habitDao())
    }

    fun provide(context: Context) {
        database = Room.databaseBuilder(context, HabitDatabase::class.java, "habits.db").build()
    }
}