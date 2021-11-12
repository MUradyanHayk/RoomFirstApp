package com.example.roomdbapp

import android.app.Application
import com.example.roomdbapp.repository.NoteRepository
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import android.os.Handler
import android.os.Looper

class NotesApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        NoteRepository.initialize(this)
    }
}