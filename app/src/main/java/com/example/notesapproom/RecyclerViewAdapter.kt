package com.example.notesapproom

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_row.view.*

class RecyclerViewAdapter(private val mainActivity: MainActivity, private val words: List<Note>) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){
    class ItemViewHolder(itemView : View) :RecyclerView.ViewHolder(itemView)
    var ctx : Context? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        ctx = parent.context
        return ItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                        R.layout.item_row
                        ,parent
                        ,false
                )
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val word=words[position]

        holder.itemView.apply {
            tv.text = word.note
            if(position%2==0){llItem.setBackgroundColor(Color.GRAY)}
            editBtn.setOnClickListener {
                mainActivity.update(word.ID)
            }
            delBtn.setOnClickListener {
                mainActivity.delete(word.ID,word.note)
            }
        }
    }

    override fun getItemCount(): Int =words.size

}


//            editBtn.setOnClickListener{
//                // first we create a variable to hold an AlertDialog builder
//                val dialogBuilder = AlertDialog.Builder(ctx)
//                // then we set up the input
//                val input = EditText(ctx)
//                input.hint="Enter new note"
//                // here we set the message of our alert dialog
//                //dialogBuilder.setMessage("Update Note")
//                    // positive button text and action
//                dialogBuilder.setPositiveButton("ok", DialogInterface.OnClickListener { dialog, id ->
//                        var dbhlr = DBHlr(ctx)
//                        val str = input.text.toString()
//                        dbhlr.update(tv.text.toString(), str)
//                    val intent = Intent(ctx, MainActivity::class.java)
//                    ctx!!.startActivity(intent)
//                    })
//                    // negative button text and action
//                    .setNegativeButton("cancel", DialogInterface.OnClickListener { dialog, id ->
//                    })
//                // create dialog box
//                val alert = dialogBuilder.create()
//                // set title for alert dialog box
//                alert.setTitle("Update Note")
//                // add the Edit Text
//                alert.setView(input)
//                // show alert dialog
//                alert.show()
//            }
//            delBtn.setOnClickListener{
//                var dbhlr = DBHlr(ctx)
//                dbhlr.delete(tv.text.toString())
//                val intent = Intent(ctx, MainActivity::class.java)
//                ctx!!.startActivity(intent)
//            }