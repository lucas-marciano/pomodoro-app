package com.lucasmarciano.pomodoro.ui.hisoty_pomodoro

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.lucasmarciano.pomodoro.R
import com.lucasmarciano.pomodoro.data.daos.PomodoroDao
import com.lucasmarciano.pomodoro.utils.Logger
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

        GlobalScope.launch(Dispatchers.Main) {
            val pomodoros = pomodoroDao.all()
            if(Logger.DEBUG) Log.d(TAG, "onCreate: ${pomodoros.size}")
//            adapter.add(pomodoros)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if(Logger.DEBUG) Log.d(TAG, "onCreateView: ")

        return inflater.inflate(R.layout.history_pomodoro_fragment, container, false)
    }
}
