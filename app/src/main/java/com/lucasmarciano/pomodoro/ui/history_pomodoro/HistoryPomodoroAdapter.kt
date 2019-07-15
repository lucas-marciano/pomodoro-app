package com.lucasmarciano.pomodoro.ui.history_pomodoro

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasmarciano.pomodoro.R
import com.lucasmarciano.pomodoro.data.models.Pomodoro
import kotlinx.android.synthetic.main.pomodoro_item.view.*

class HistoryPomodoroAdapter : RecyclerView.Adapter<HistoryPomodoroAdapter.ViewHolder>() {

    var pomodoros: MutableList<Pomodoro> = mutableListOf()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val createdView = LayoutInflater.from(parent.context).inflate(R.layout.pomodoro_item, parent, false)
        return ViewHolder(createdView)
    }

    override fun getItemCount() = pomodoros.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val product = pomodoros[position]
        holder.bind(product)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(pomodoro: Pomodoro) {
            itemView.tvElapsedTime.text = pomodoro.elapsedTime.toString()
            itemView.tvStatus.text = if(pomodoro.isFinished) "Finished" else "Stopped"
            itemView.tvTimeAgo.text = pomodoro.createdAt
        }
    }
}