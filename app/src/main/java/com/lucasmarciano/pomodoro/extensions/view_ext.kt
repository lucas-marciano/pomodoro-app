package com.lucasmarciano.pomodoro.extensions

import android.os.Build
import com.google.android.material.floatingactionbutton.FloatingActionButton

fun FloatingActionButton.changeIcon(icon: Int){
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
        this.setImageDrawable(resources.getDrawable(icon, context!!.theme))
    } else {
        this.setImageDrawable(resources.getDrawable(icon))
    }
}