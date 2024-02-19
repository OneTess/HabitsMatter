package com.example.habitsmatter.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [HabitData::class],
    version = 1,
    exportSchema = false
)
abstract class HabitDatabase : RoomDatabase() {
    abstract fun habitDao(): HabitDao
}