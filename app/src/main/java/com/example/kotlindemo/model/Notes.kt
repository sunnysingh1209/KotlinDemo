package com.example.kotlindemo.model

data class Notes(var id: Int = 0, var note: String = "", var timeStamp: String = "") {
    companion object {
        val TABLE_NAME = "notes"

        var COLUMN_ID = "id"
        var COLUMN_NOTE = "note"
        var COLUMN_TIMESTAMP = "timestamp"
        // Create table SQL query
        val CREATE_TABLE = (
                "CREATE TABLE " + TABLE_NAME + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                        + COLUMN_NOTE + " TEXT,"
                        + COLUMN_TIMESTAMP + " DATETIME DEFAULT CURRENT_TIMESTAMP"
                        + ")")
    }
}