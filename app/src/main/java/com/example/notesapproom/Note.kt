package com.example.notesapproom

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Note")
data class Note (
        @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "ID") val ID: Int ,
        @ColumnInfo(name = "Note") val note: String
)