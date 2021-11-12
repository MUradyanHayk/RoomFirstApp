package com.example.roomdbapp.database

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.OnConflictStrategy.REPLACE

@Dao
interface NoteDao {
    @Query("SELECT * FROM _notes ORDER BY _name ASC")
    fun getAllNotes(): LiveData<List<Note>>

    @Query("SELECT * FROM _notes WHERE _identifier = :identifier")
    fun getNote(identifier: String): Note

    @Query("SELECT * FROM _notes WHERE _name LIKE :search")
    fun getSearchedNotes(search: String): LiveData<List<Note>>

    @Insert(onConflict = REPLACE)
    fun insertNote(note: Note)

    @Update(onConflict = REPLACE)
    fun updateNote(note: Note)

    @Delete
    fun deleteNote(note: Note)

//
//    @Query("SELECT * FROM _notes WHERE _name LIKE '%' || :filterTitle|| '%' AND _isChecked LIKE '%' ||:filterContent")
//    fun d(filterTitle: String, filterContent: String)
}
