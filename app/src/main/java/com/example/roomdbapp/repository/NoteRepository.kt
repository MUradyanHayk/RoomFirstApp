package com.example.roomdbapp.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.roomdbapp.AsyncManager
import com.example.roomdbapp.NotesApplication
import com.example.roomdbapp.database.Note
import com.example.roomdbapp.database.NoteDao
import com.example.roomdbapp.database.NoteDatabase

class NoteRepository private constructor(context: Context) {
    private var allNotes: LiveData<List<Note>>? = null

    fun getAllNotes(): LiveData<List<Note>>? {
        allNotes = noteDao?.getAllNotes()
        return allNotes
    }

    fun getNote(identifier: String): Note? {
        val note = noteDao?.getNote(identifier)
        return note
    }

    fun addNote(note: Note) {
        AsyncManager.executor.execute {
            noteDao?.insertNote(note)
        }
    }

    fun deleteNote(note: Note) {
        AsyncManager.executor.execute {
            noteDao?.deleteNote(note)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: NoteRepository? = null
        private var LOCK = Any()
        var noteDao: NoteDao? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                synchronized(LOCK) {
                    if (INSTANCE == null) {
                        INSTANCE = NoteRepository(context)
                        noteDao = NoteDatabase(context)?.noteDao()
                    }
                }
            }
        }

        fun get(): NoteRepository {
            return INSTANCE ?: throw IllegalStateException("must be initialized")
        }
    }
}