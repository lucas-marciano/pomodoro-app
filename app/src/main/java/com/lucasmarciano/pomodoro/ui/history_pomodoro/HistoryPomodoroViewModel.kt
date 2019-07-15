package com.lucasmarciano.pomodoro.ui.history_pomodoro


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lucasmarciano.pomodoro.data.daos.PomodoroDao
import com.lucasmarciano.pomodoro.data.models.Pomodoro
import com.lucasmarciano.pomodoro.utils.Logger
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class HistoryPomodoroViewModel : ViewModel() {
    private var TAG = Logger.getTag()

    private var _pomodoros = MutableLiveData<MutableList<Pomodoro>>()
    var pomodoros: LiveData<MutableList<Pomodoro>> = Transformations.map(_pomodoros) {
        it
    }

    lateinit var pomodoroDao: PomodoroDao

    fun getAllPomodoros() {
        if (Logger.DEBUG) Log.d(TAG, "getAllPomodoros: ")

        GlobalScope.launch {
            val pomo = pomodoroDao.all()
            _pomodoros = pomo as MutableLiveData<MutableList<Pomodoro>>
        }
    }
}
