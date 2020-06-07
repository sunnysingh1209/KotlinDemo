package com.example.kotlindemo.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlindemo.R
import com.example.kotlindemo.model.Notes
import kotlinx.android.synthetic.main.note_activity_adapter.view.*

class NotesActivityAdapter(
    val notesList: ArrayList<Notes>,
    var context: Context,
    var action: (Int) -> Unit
) :
    RecyclerView.Adapter<NotesActivityAdapter.MyView>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyView {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.note_activity_adapter, parent, false)
        return MyView(view)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    override fun onBindViewHolder(holder: MyView, position: Int) {
        holder.notesAdapter_id?.text = notesList[holder.adapterPosition].id.toString()
        holder.notesAdapter_note?.text = notesList[holder.adapterPosition].note
        holder.notesAdapter_timeStamp?.text = notesList.get(holder.adapterPosition).timeStamp

        holder.itemView.setOnClickListener {
            action(holder.adapterPosition)
        }
//        (holder as MyView).bind(mainModelList[position], context, clickListner)

    }

    class MyView(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var notesAdapter_id: TextView = itemView.notesAdapter_id
        var notesAdapter_note: TextView = itemView.notesAdapter_note
        var notesAdapter_timeStamp: TextView = itemView.notesAdapter_timeStamp

    }
}