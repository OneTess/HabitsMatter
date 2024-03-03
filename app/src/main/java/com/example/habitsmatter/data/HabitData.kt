package com.example.habitsmatter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_table")
data class HabitData (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // TODO: Change id type to Long. Some errors will appear.
    val type: String = "binary",
    val progress: Int = 0, // TODO: Progress Goal db entry
    val title: String = "",
    val content: String = ""
)