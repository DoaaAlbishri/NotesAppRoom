package com.example.notesapproom

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {
    lateinit var editText: EditText
    lateinit var button: Button
    lateinit var myRv: RecyclerView
    //var list = ArrayList<String>()
    lateinit var lv: List<Note>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText = findViewById<EditText>(R.id.editText)
        button = findViewById<Button>(R.id.button)
        myRv = findViewById<RecyclerView>(R.id.recyclerView)

        lv = arrayListOf()
        NoteDatabase.getInstance(applicationContext)

        show()
        button.setOnClickListener {
            if (!editText.text.isEmpty()) {
                save()
            } else {
                Toast.makeText(this, "The text is empty", Toast.LENGTH_SHORT).show()
            }
            editText.text.clear()
        }
    }

    fun show() {
        CoroutineScope(Dispatchers.IO).launch {
            lv = NoteDatabase.getInstance(applicationContext).StudentDao()
                    .getNote()
            withContext(Main){
                //recycler view
                myRv()
            }
            }
        }


    fun save() {
        var note = editText.text.toString()
        val s = Note(0,note)
        CoroutineScope(Dispatchers.IO).launch {
            NoteDatabase.getInstance(applicationContext).StudentDao().insertNote(s)
        }
        Toast.makeText(applicationContext, "data saved successfully! ", Toast.LENGTH_SHORT)
                .show()
        show()
    }

    fun update(ID : Int){
        //first we create a variable to hold an AlertDialog builder
        val dialogBuilder = AlertDialog.Builder(this)
        // then we set up the input
        val input = EditText(this)
        input.hint = "Enter new note"
        // here we set the message of our alert dialog
        //dialogBuilder.setMessage("Update Note")
        // positive button text and action
        dialogBuilder.setPositiveButton("ok", DialogInterface.OnClickListener { dialog, id ->
            val str = input.text.toString()
            val s = Note(ID,str)
            CoroutineScope(Dispatchers.IO).launch {
                NoteDatabase.getInstance(applicationContext).StudentDao().updateNote(s)
            }
            Toast.makeText(applicationContext, "data updated successfully! ", Toast.LENGTH_SHORT)
                    .show()
            println("updated item")
            //retrieve data and update recycler view
            show()
        })
                // negative button text and action
                .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, id ->
                })
        // create dialog box
        val alert = dialogBuilder.create()
        // set title for alert dialog box
        alert.setTitle("Update Note")
        // add the Edit Text
        alert.setView(input)
        // show alert dialog
        alert.show()
    }

    fun delete(id: Int ,s1: String) {
        val del = Note(id,s1)
        CoroutineScope(Dispatchers.IO).launch {
            NoteDatabase.getInstance(applicationContext).StudentDao().deleteNote(del)
        }
        Toast.makeText(applicationContext, "data deleted successfully! ", Toast.LENGTH_SHORT)
                .show()
        //retrieve data and update recycler view
        show()
    }

    fun myRv() {
        myRv.adapter = RecyclerViewAdapter(this, lv)
        myRv.layoutManager = LinearLayoutManager(this)
    }
}