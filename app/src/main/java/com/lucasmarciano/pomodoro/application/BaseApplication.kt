package com.lucasmarciano.pomodoro.application

import android.app.Application
import com.lucasmarciano.pomodoro.id.databaseModules
import com.lucasmarciano.pomodoro.id.uiModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class BaseApplication : Application(){

    override fun onCreate() {
        super.onCreate()
        // Start Koin
        startKoin{
            androidLogger()
            androidContext(this@BaseApplication)
            modules(listOf(uiModules, databaseModules))
        }
    }
}