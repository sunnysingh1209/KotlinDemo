package com.example.kotlindemo.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.kotlindemo.model.Notes

class DatabaseHelper(context: Context) :
    SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL(Notes.CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Drop older table if existed
        db?.execSQL("DROP TABLE IF EXISTS " + Notes.TABLE_NAME);

        // Create tables again
        onCreate(db);
    }

    companion object {
        // Database Version
        val DATABASE_VERSION = 1
        // Database Name
        val DATABASE_NAME = "notes_db"
    }

    fun insertNote(note: String): Long {
        // get writable database as we want to write data
        val db = this.writableDatabase

        val values = ContentValues()
        // `id` and `timestamp` will be inserted automatically.
        // no need to add them
        values.put(Notes.COLUMN_NOTE, note)

        // insert row
        val id = db.insert(Notes.TABLE_NAME, null, values)

        // close db connection
        db.close()

        // return newly inserted row id
        return id
    }

    fun getNote(id: Long): Notes {
        // get readable database as we are not inserting anything
        val db = this.readableDatabase

        val cursor = db.query(
            Notes.TABLE_NAME,
            arrayOf(Notes.COLUMN_ID, Notes.COLUMN_NOTE, Notes.COLUMN_TIMESTAMP),
            Notes.COLUMN_ID + "=?",
            arrayOf(id.toString()), null, null, null, null
        )

        cursor?.moveToFirst()

        // prepare note object
        val note = Notes(
            cursor!!.getInt(cursor.getColumnIndex(Notes.COLUMN_ID)),
            cursor.getString(cursor.getColumnIndex(Notes.COLUMN_NOTE)),
            cursor.getString(cursor.getColumnIndex(Notes.COLUMN_TIMESTAMP))
        )

        // close the db connection
        cursor.close()

        return note
    }

    fun getAllNotes(): ArrayList<Notes> {
        val notes = arrayListOf<Notes>()

        // Select All Query
        val selectQuery = "SELECT  * FROM " + Notes.TABLE_NAME + " ORDER BY " +
                Notes.COLUMN_TIMESTAMP + " DESC"

        val db = this.writableDatabase
        val cursor = db.rawQuery(selectQuery, null)

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                val note = Notes(
                    cursor.getInt(cursor.getColumnIndex(Notes.COLUMN_ID))
                    , cursor.getString(cursor.getColumnIndex(Notes.COLUMN_NOTE))
                    , cursor.getString(cursor.getColumnIndex(Notes.COLUMN_TIMESTAMP))
                )

                notes.add(note)
            } while (cursor.moveToNext())
        }

        // close db connection
        db.close()

        // return notes list
        return notes
    }

    fun updateNote(note: Notes): Int {
        val db = this.writableDatabase

        val values = ContentValues()
        values.put(Notes.COLUMN_NOTE, note.note)

        // updating row
        return db.update(
            Notes.TABLE_NAME, values, Notes.COLUMN_ID + " = ?",
            arrayOf(note.id.toString())
        )
    }

    fun deleteNote(note: Notes) {
        val db = this.writableDatabase
        db.delete(
            Notes.TABLE_NAME, Notes.COLUMN_ID + " = ?",
            arrayOf(note.id.toString())
        )
        db.close()
    }

    fun getNotesCount(): Int {
        val countQuery = "SELECT  * FROM " + Notes.TABLE_NAME
        val db = this.readableDatabase
        val cursor = db.rawQuery(countQuery, null)

        val count = cursor.count
        cursor.close()


        // return count
        return count
    }
}