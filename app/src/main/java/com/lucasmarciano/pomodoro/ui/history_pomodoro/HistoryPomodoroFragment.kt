package com.lucasmarciano.pomodoro.ui.history_pomodoro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasmarciano.pomodoro.R
import com.lucasmarciano.pomodoro.data.daos.PomodoroDao
import com.lucasmarciano.pomodoro.data.models.Pomodoro
import com.lucasmarciano.pomodoro.utils.Logger
import kotlinx.android.synthetic.main.history_pomodoro_fragment.*
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HistoryPomodoroFragment : Fragment() {
    private var TAG = Logger.getTag()

    private val adapter: HistoryPomodoroAdapter by inject()
    private val mViewModel: HistoryPomodoroViewModel by viewModel()
    private val pomodoroDao: PomodoroDao by inject()

    companion object {
        fun newInstance() = HistoryPomodoroFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        if(Logger.DEBUG) Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(Logger.DEBUG) Log.d(TAG, "onCreateView: ")
        return inflater.inflate(R.layout.history_pomodoro_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        if(Logger.DEBUG) Log.d(TAG, "onActivityCreated: ")
        super.onActivityCreated(savedInstanceState)
        mViewModel.pomodoroDao = pomodoroDao
        setupRecyclerView()
    }

    override fun onResume() {
        if(Logger.DEBUG) Log.d(TAG, "onResume: ")
        super.onResume()
        mViewModel.pomodoros.observe(this, Observer<MutableList<Pomodoro>> { mlPomodoros ->
            mlPomodoros?.let {
                adapter.pomodoros = it
            }
        })
    }

    private fun setupRecyclerView() {
        if(Logger.DEBUG) Log.d(TAG, "setupRecyclerView: ")
        rvPomodorosList.layoutManager = LinearLayoutManager(context)
        rvPomodorosList.adapter = adapter
    }
}