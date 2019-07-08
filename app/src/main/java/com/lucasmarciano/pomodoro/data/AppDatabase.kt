package com.lucasmarciano.pomodoro.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.lucasmarciano.pomodoro.data.daos.PomodoroDao
import com.lucasmarciano.pomodoro.data.models.Pomodoro

@Database(entities = [Pomodoro::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase(){
    abstract fun pomodoroDao(): PomodoroDao
}