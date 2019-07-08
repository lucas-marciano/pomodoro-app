package com.lucasmarciano.pomodoro.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.lucasmarciano.pomodoro.extensions.formatDateUs
import java.util.*


@Entity(tableName = "pomodoros")
data class Pomodoro(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val elapsedTime: Long,
    val remainTime: Long,
    val isFinished: Boolean,
    val createdAt: String = Date().formatDateUs()
)