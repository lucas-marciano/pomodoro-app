package com.lucasmarciano.pomodoro.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Date.formatDateUs(): String {
    val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
    return formatter.format(this)
}