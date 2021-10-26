package com.example.notesapproom

import androidx.room.*

@Dao
interface NoteDao {

    @Query("SELECT * FROM Note")
    fun getNote(): List<Note>

    @Insert
    fun insertNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

    @Update
    fun updateNote(note: Note)

}