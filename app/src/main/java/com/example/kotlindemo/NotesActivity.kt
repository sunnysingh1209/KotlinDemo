package com.example.kotlindemo

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kotlindemo.adapter.NotesActivityAdapter
import com.example.kotlindemo.database.DatabaseHelper
import com.example.kotlindemo.model.Notes
import kotlinx.android.synthetic.main.activity_notes.*


class NotesActivity : AppCompatActivity() {

    var notes_ET: EditText? = null
    var db: DatabaseHelper? = null
    var notesList = arrayListOf<Notes>()
    var adapter: NotesActivityAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_notes)

        notes_ET = findViewById(R.id.notes_ET)
        db = DatabaseHelper(this@NotesActivity)

        notesList.addAll(db!!.getAllNotes())
        notes_recView.layoutManager = LinearLayoutManager(this)
        adapter = NotesActivityAdapter(notesList, this, { action -> show(action) })
        notes_recView.adapter = adapter

        notes_Btn.setOnClickListener {
            createNote(notes_ET?.text.toString())
            notes_ET?.setText("")
        }

    }

    private fun createNote(note: String) {
        // inserting note in db and getting
        // newly inserted note id
        val id = db?.insertNote(note)

        // get the newly inserted note from db
        val n = id?.let { db?.getNote(it) }

        if (n != null) {
            // adding new note to array list at 0 position
            notesList.add(0, n)

            // refreshing the list
            adapter?.notifyDataSetChanged()

        }
    }

    fun show(pos: Int) {
        showActionsDialog(pos)
    }

    private fun showActionsDialog(position: Int) {
        val colors = arrayOf<CharSequence>("Edit", "Delete")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choose option")
        builder.setItems(colors, DialogInterface.OnClickListener { dialog, which ->
            if (which == 0) {
                showNoteDialog(true, notesList[position], position)
            } else {
                deleteNote(position)
            }
        })
        builder.show()
    }

    private fun showNoteDialog(b: Boolean, notes: Notes, position: Int) {
        var layouInter = LayoutInflater.from(this)
        var view = layouInter.inflate(R.layout.note_dialog, null)

        val alertDialogBuilder = AlertDialog.Builder(this@NotesActivity)
        alertDialogBuilder.setView(view)

        var dialog_noteEt = view.findViewById<EditText>(R.id.dialog_noteEt)
        if (notes != null) {
            dialog_noteEt.setText(notes.note)
        }
        alertDialogBuilder.setCancelable(true)

        alertDialogBuilder.setPositiveButton("Ok", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                updateNote(dialog_noteEt.text.toString(), position)
                p0?.dismiss()
            }

        })

        alertDialogBuilder.setNegativeButton("cancel", object : DialogInterface.OnClickListener {
            override fun onClick(p0: DialogInterface?, p1: Int) {
                p0?.dismiss()
            }

        })

        var alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }

    private fun deleteNote(position: Int) {
        // deleting the note from db
        db?.deleteNote(notesList[position])

        // removing the note from the list
        notesList.removeAt(position)
        adapter?.notifyItemRemoved(position)

    }

    private fun updateNote(note: String, position: Int) {
        val n = notesList[position]
        // updating note text
        n.note = note

        // updating note in db
        db?.updateNote(n)

        // refreshing the list
        notesList[position] = n
        adapter?.notifyItemChanged(position)

    }

}
