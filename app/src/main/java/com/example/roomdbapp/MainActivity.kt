package com.example.roomdbapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.roomdbapp.fragment.NoteFragment
import com.example.roomdbapp.viewModel.NoteViewModel

class MainActivity : AppCompatActivity() {
    var noteViewModel: NoteViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        noteViewModel = ViewModelProvider(this).get(NoteViewModel::class.java)
        supportFragmentManager.beginTransaction().add(R.id.fragment_container, NoteFragment()).commit()
    }

}