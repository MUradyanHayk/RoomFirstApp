package com.example.roomdbapp.viewModel

import android.content.Context
import android.service.carrier.CarrierIdentifier
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.roomdbapp.database.Note
import com.example.roomdbapp.repository.NoteRepository

class NoteViewModel : ViewModel() {
    private var repository:NoteRepository? = null
    fun initialize(context: Context) {
        repository = NoteRepository.get()
    }


    fun getAllNotes(): LiveData<List<Note>>? {
        return repository?.getAllNotes()
    }

    fun addNote(note: Note) {
        repository?.addNote(note)
    }

    fun getNote(identifier: String): Note {
        return repository?.getNote(identifier)!!
    }

    fun deleteNote(identifier: String) {
        val note = getNote(identifier)
        repository?.deleteNote(note)
    }
}