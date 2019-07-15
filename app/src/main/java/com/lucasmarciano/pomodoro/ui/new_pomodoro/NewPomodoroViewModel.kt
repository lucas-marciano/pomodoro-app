package com.lucasmarciano.pomodoro.ui.new_pomodoro

import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.lucasmarciano.pomodoro.data.daos.PomodoroDao
import com.lucasmarciano.pomodoro.data.models.Pomodoro
import com.lucasmarciano.pomodoro.extensions.converMillisecondsToMinutes
import com.lucasmarciano.pomodoro.extensions.converMillisecondsToSeconds
import com.lucasmarciano.pomodoro.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class NewPomodoroViewModel : ViewModel() {

    private var TAG = Logger.getTag()
    private var totalTimer: Long = 25 * 60000 // One minute has 60 seconds and one townsend milliseconds.
    private var interval: Long = 1 * 1000
    private var remainMilliseconds: Long = 0
    private lateinit var pomodoroDao: PomodoroDao

    private val _minutes = MutableLiveData<Long>()
    val minutes: LiveData<String> = Transformations.map(_minutes) {
        "$it"
    }

    private val _seconds = MutableLiveData<Long>()
    val seconds: LiveData<String> = Transformations.map(_seconds) {
        "$it"
    }

    private val _isActive = MutableLiveData<Boolean>()
    val isActive: LiveData<Boolean> = Transformations.map(_isActive) {
        it
    }

    fun setDao(dao: PomodoroDao){
        pomodoroDao = dao
    }

    private val countDownTimer = object: CountDownTimer(totalTimer, interval) {
        override fun onFinish() {
            if(Logger.DEBUG) Log.d(TAG, "onFinish: ")
            _isActive.value = false
            this.cancel()
        }

        override fun onTick(millisUntilFinished: Long) {
            if(Logger.DEBUG) Log.d(TAG, "onTick: ")

            remainMilliseconds = millisUntilFinished
            _minutes.value = millisUntilFinished.converMillisecondsToMinutes()
            _seconds.value = millisUntilFinished.converMillisecondsToSeconds()
        }

    }

    fun initTimer(){
        if(Logger.DEBUG) Log.d(TAG, "initTimer: ")
        countDownTimer.start()
        _isActive.value = true
    }

    fun stopTimer(){
        if(Logger.DEBUG) Log.d(TAG, "stopTimer: ")
        countDownTimer.onFinish()
        savePomodoro()
    }

    private fun savePomodoro(){
        val elapsedTime = totalTimer - remainMilliseconds
        val pomodoro =  Pomodoro(
            elapsedTime = elapsedTime,
            remainTime = remainMilliseconds,
            isFinished = elapsedTime == totalTimer
        )

        GlobalScope.launch {
            pomodoroDao.insert(pomodoro)
        }
    }

}