package com.lucasmarciano.pomodoro.ui

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.lucasmarciano.pomodoro.R
import com.lucasmarciano.pomodoro.ui.hisoty_pomodoro.HistoryPomodoroFragment
import com.lucasmarciano.pomodoro.ui.new_pomodoro.NewPomodoroFragment

private val TAB_TITLES = arrayOf(
    R.string.tab_label_new,
    R.string.tab_label_history
)

enum class IndexTab(val index: Int) {
    TAB_NEW(0),
    TAB_HISTORY(1)
}

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
class SectionsPagerAdapter(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            IndexTab.TAB_NEW.index -> NewPomodoroFragment.newInstance()
            IndexTab.TAB_HISTORY.index -> HistoryPomodoroFragment.newInstance()
            else -> NewPomodoroFragment.newInstance()
        }

    }

    override fun getPageTitle(position: Int): CharSequence? {
        return context.resources.getString(TAB_TITLES[position])
    }

    override fun getCount(): Int {
        return TAB_TITLES.size
    }
}