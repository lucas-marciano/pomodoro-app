package com.lucasmarciano.pomodoro.ui.hisoty_pomodoro

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasmarciano.pomodoro.R
import com.lucasmarciano.pomodoro.data.models.Pomodoro

class HistoryPomodoroAdapter(
    private val pomodoros: MutableList<Pomodoro> = mutableListOf(),
    private val context: Context) : RecyclerView.Adapter<HistoryPomodoroAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createdView = LayoutInflater.from(context).inflate(R.layout.pomodoro_item, parent, false)
        return ViewHolder(createdView)
    }

    override fun getItemCount() = pomodoros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = pomodoros[position]
        holder.bind(product)
    }

    fun add(pomodoros: MutableList<Pomodoro>) {
        this.pomodoros.addAll(pomodoros)
        notifyItemRangeInserted(0, pomodoros.size)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(product: Pomodoro) {

        }
    }
}