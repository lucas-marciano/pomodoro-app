package com.lucasmarciano.pomodoro.ui.new_pomodoro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.lucasmarciano.pomodoro.R
import com.lucasmarciano.pomodoro.data.daos.PomodoroDao
import com.lucasmarciano.pomodoro.extensions.changeIcon
import com.lucasmarciano.pomodoro.extensions.putZeroOnLeft
import com.lucasmarciano.pomodoro.utils.Logger
import kotlinx.android.synthetic.main.new_pomodoro_fragment.*
import org.jetbrains.anko.support.v4.toast
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class NewPomodoroFragment : Fragment() {
    private var TAG = Logger.getTag()
    private val pomodoroDao: PomodoroDao by inject()
    private val mViewModel: NewPomodoroViewModel by viewModel()

    companion object {
        fun newInstance() = NewPomodoroFragment()
    }

    private var minute = "25"
    private var second = "00"
    private var mIsActive = false


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(Logger.DEBUG) Log.d(TAG, "onCreateView: ")
        return inflater.inflate(R.layout.new_pomodoro_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(Logger.DEBUG) Log.d(TAG, "onActivityCreated: ")
        setupViewsController()
        updateInterface()
        initObservables()
    }

    /**
     * Method that will init the observables of the ViewModel data.
     */
    private fun initObservables(){
        if(Logger.DEBUG) Log.d(TAG, "initObservables: ")

        mViewModel.setDao(pomodoroDao)

        mViewModel.minutes.observe(this, Observer<String> {
            minute = it.putZeroOnLeft()
            tvTimer.text = String.format(getString(R.string.label_time_format), minute, second)

        })

        mViewModel.seconds.observe(this, Observer<String> {
            second = it.putZeroOnLeft()
            tvTimer.text = String.format(getString(R.string.label_time_format), minute, second)
        })

        mViewModel.isActive.observe(this, Observer<Boolean> { isActive ->
            mIsActive = isActive
            updateInterface()
            if(!mIsActive){
                toast(R.string.message_end_pomodoro)
            }
        })
    }

    /**
     * Method to create all actions that will be triggered when interacting with the interface.
     */
    private fun setupViewsController() {
        if(Logger.DEBUG) Log.d(TAG, "setupViewsController: ")
        fabControlTimer.setOnClickListener {
            if (mIsActive) {
                mViewModel.stopTimer()
            } else {
                mViewModel.initTimer()
            }
        }
    }

    /**
     * Method to update the elements of the interface, depend on the status of the timer.
     */
    private fun updateInterface() {
        if(Logger.DEBUG) Log.d(TAG, "updateInterface: ")
        if (mIsActive) {
            tvTimer.setTextColor(resources.getColor(R.color.colorPrimary))
            fabControlTimer.changeIcon(R.drawable.ic_pause)
        } else {
            tvTimer.setTextColor(resources.getColor(R.color.colorInactiveTimer))
            tvTimer.text = resources.getString(R.string.label_initial_timer)
            fabControlTimer.changeIcon(R.drawable.ic_play)
        }
    }
}