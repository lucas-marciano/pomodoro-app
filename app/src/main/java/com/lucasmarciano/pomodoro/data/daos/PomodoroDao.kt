package com.lucasmarciano.pomodoro.data.daos

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.lucasmarciano.pomodoro.data.models.Pomodoro

@Dao
interface PomodoroDao {

    @Query("SELECT * FROM pomodoros")
    suspend fun all(): MutableList<Pomodoro>

    @Insert
    suspend fun insert(pomodoro: Pomodoro)

    @Delete
    suspend fun delete(pomodoro: Pomodoro)
}