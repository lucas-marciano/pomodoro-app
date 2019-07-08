package com.lucasmarciano.pomodoro.extensions

fun Long.converMillisecondsToMinutes() = this / 1000 / 60

fun Long.converMillisecondsToSeconds() = this / 1000 % 60