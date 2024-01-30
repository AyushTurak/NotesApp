package com.example.notesappsql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesappsql.databinding.ActivityAddNoteBinding
import com.example.notesappsql.databinding.ActivityUpdateBinding

class UpdateActivity : AppCompatActivity() {
    private lateinit var binding:ActivityUpdateBinding
    private lateinit var db:NoteDataBaseHelper
    private  var noteId:Int = -1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = NoteDataBaseHelper(this)

        noteId = intent.getIntExtra("note_id",-1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.updateNoteTitle.setText(note.title)
        binding.noteDiscription.setText(note.content)

        binding.btnDone.setOnClickListener{
            val newTitle = binding.updateNoteTitle.text.toString()
            val newContent = binding.noteDiscription.text.toString()
            val updatesNote = Note(noteId,newTitle,newContent)
            db.updateNote(updatesNote)
            finish()
            Toast.makeText(this,"Data Updated",Toast.LENGTH_SHORT).show()
        }

    }
}