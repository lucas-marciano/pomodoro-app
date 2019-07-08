package com.lucasmarciano.pomodoro.extensions

fun String.putZeroOnLeft() = if(this.toInt() >= 10) this else "0$this"
