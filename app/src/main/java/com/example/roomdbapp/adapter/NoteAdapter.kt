package com.example.roomdbapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.roomdbapp.R
import com.example.roomdbapp.database.Note
import java.lang.ref.WeakReference

interface NoteAdapterDelegate {
    fun onItemClick(identifier: String?)
}
class NoteAdapter(val context: Context, var delegate: WeakReference<NoteAdapterDelegate>?, var list: List<Note>) : RecyclerView.Adapter<ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context).inflate(R.layout.item_note, parent, false)
        return ViewHolder(inflater)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.text = list[position].name
        holder.itemView.setOnClickListener {
//            Toast.makeText(context, "click", Toast.LENGTH_SHORT).show()
            delegate?.get()?.onItemClick(list[position].identifier)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }
}

class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private var textView: TextView? = null
    var text: String? = null
        set(value) {
            field = value
            textView?.text = field
        }

    init {
        textView = itemView.findViewById(R.id.text_view)
    }
}