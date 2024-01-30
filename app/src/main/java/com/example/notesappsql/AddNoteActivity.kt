package com.example.notesappsql

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notesappsql.databinding.ActivityAddNoteBinding

class AddNoteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityAddNoteBinding
 private lateinit var db:NoteDataBaseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddNoteBinding.inflate(layoutInflater)
        setContentView(binding.root)
        db = NoteDataBaseHelper(this)

        binding.btnDone.setOnClickListener{
            val note = Note(0,binding.noteTitle.text.toString(),binding.noteDiscription.text.toString())
            db.insertNote(note)
            finish()
            Toast.makeText(this,"Note is added",Toast.LENGTH_SHORT).show()
        }
    }
}