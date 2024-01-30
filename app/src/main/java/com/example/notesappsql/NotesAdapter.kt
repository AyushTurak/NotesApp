package com.example.notesappsql

import android.content.Context
import android.content.Intent
import android.text.Html.ImageGetter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class NotesAdapter(private var notes:List<Note>,context: Context):RecyclerView.Adapter<NotesAdapter.NoteViewHolder>() {

    private var db:NoteDataBaseHelper = NoteDataBaseHelper(context)

    class NoteViewHolder(itemView: View):RecyclerView.ViewHolder(itemView){
        val title : TextView = itemView.findViewById(R.id.title_tv)
        val content:TextView = itemView.findViewById(R.id.content_tv)
        val update:ImageView = itemView.findViewById(R.id.btn_update)
        val delete:ImageView = itemView.findViewById(R.id.btn_delet)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.note_item,parent,false)
        return NoteViewHolder(view)
    }

    override fun getItemCount(): Int {
        return notes.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
     val note = notes[position]
        holder.title.text = note.title
        holder.content.text = note.content
        holder.update.setOnClickListener{
            val intent = Intent(holder.itemView.context,UpdateActivity::class.java).apply {
                putExtra("note_id",note.id)
            }
            holder.itemView.context.startActivity(intent)
        }
        holder.delete.setOnClickListener{
            db.deleteNote(note.id)
            refreshData(db.getAllNotes())
            Toast.makeText(holder.itemView.context,"Note Deleted",Toast.LENGTH_SHORT).show()
        }
    }

    fun refreshData(newNotes:List<Note>){
        notes = newNotes
        notifyDataSetChanged()
    }

}