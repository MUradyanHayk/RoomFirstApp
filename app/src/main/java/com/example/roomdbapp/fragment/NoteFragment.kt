package com.example.roomdbapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbapp.MainActivity
import com.example.roomdbapp.R
import com.example.roomdbapp.adapter.NoteAdapter
import com.example.roomdbapp.adapter.NoteAdapterDelegate
import com.example.roomdbapp.database.Note
import com.example.roomdbapp.viewModel.NoteViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.lang.ref.WeakReference
import java.util.*

class NoteFragment : Fragment(), NoteAdapterDelegate {
    var viewModel: NoteViewModel? = null
    var recyclerView: RecyclerView? = null
    var fab: FloatingActionButton? = null
    var editText: EditText? = null
    var adapter: NoteAdapter? = null
    var screen: View? = null
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        screen = inflater.inflate(R.layout.fragment_note, container, false)
        createRecyclerView()

        fab = screen?.findViewById(R.id.fab_btn)
        editText = screen?.findViewById(R.id.edit_text)

        fab?.setOnClickListener {
            viewModel?.addNote(createNote(editText?.text?.toString()?.trim() ?: ""))

        }
        return screen
    }

    private fun createNote(name: String): Note {
        val note = Note()
        note.name = name
        note.identifier = UUID.randomUUID().toString() + System.currentTimeMillis().toString() + note.hashCode().toString()
        return note
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (activity as? MainActivity?)?.noteViewModel
        viewModel?.initialize(requireContext())
        viewModel?.getAllNotes()?.observe(viewLifecycleOwner) {
            adapter?.list = it?: listOf()
            adapter?.notifyDataSetChanged()
        }
    }


    private fun createRecyclerView() {
        recyclerView = screen?.findViewById(R.id.recycler_view)
        adapter = NoteAdapter(requireContext(), WeakReference(this), viewModel?.getAllNotes()?.value ?: listOf())
        recyclerView?.adapter = adapter
        recyclerView?.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    override fun onItemClick(identifier: String?) {
        if (identifier == null) {
            return
        }
        viewModel?.deleteNote(identifier)
    }
}