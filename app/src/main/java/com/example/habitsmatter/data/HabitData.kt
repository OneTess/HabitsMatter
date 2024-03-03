package com.example.habitsmatter.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "habit_table")
data class HabitData (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0, // TODO: Change id type to Long. Some errors will appear.
    val type: String = "binary", // TODO: Implement defaulting to the binary properly, or make user always choose the habit type. For now this works in a really weird way.
    val progress: Int = 0, // TODO: Progress Goal db entry
    val title: String = "",
    val content: String = ""
)