package com.lucasmarciano.pomodoro.id

import androidx.room.Room
import com.lucasmarciano.pomodoro.data.AppDatabase
import com.lucasmarciano.pomodoro.ui.hisoty_pomodoro.HistoryPomodoroAdapter
import com.lucasmarciano.pomodoro.ui.hisoty_pomodoro.HistoryPomodoroViewModel
import com.lucasmarciano.pomodoro.ui.new_pomodoro.NewPomodoroViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val uiModules = module {
    // ViewModels
    viewModel { NewPomodoroViewModel() }
    viewModel { HistoryPomodoroViewModel() }

    // Adapters
    factory { HistoryPomodoroAdapter(context = get()) }

}

val databaseModules = module {

    // Room
    single {
        Room.databaseBuilder(
            get(),
            AppDatabase::class.java,
            "pomodoro-database"
        ).build()
    }
    single { get<AppDatabase>().pomodoroDao() }
}