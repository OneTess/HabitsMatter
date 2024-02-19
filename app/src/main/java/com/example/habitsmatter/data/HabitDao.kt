package com.example.habitsmatter.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
abstract class HabitDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insert(item: HabitData): Long

    @Update
    abstract suspend fun update(item: HabitData)

    @Delete
    abstract suspend fun delete(item: HabitData)

    @Query("SELECT * from habit_table WHERE id = :id")
    abstract fun getHabit(id: Int): Flow<HabitData>

    @Query("SELECT * from habit_table WHERE title != '' OR content != '' ORDER BY title ASC")
    abstract fun getAllHabits(): Flow<List<HabitData>>

    @Query("SELECT MAX(id) from habit_table")
    abstract suspend fun getBiggestId(): Int
}