package com.example.mytestingapplication

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView

internal class ReminderAdapter(mContext: Context, reminderData: List<*>) :
    RecyclerView.Adapter<ReminderViewHolder>() {
    private val reminderData: List<ReminderData>
    private val mContext: Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReminderViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.activity_recyclerview_for_reminders,
            parent, false
        )
        return ReminderViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReminderViewHolder, position: Int) {
        holder.icon.text = reminderData[position].title.substring(0, 1)
        holder.title.text = reminderData[position].title
        holder.date.text = reminderData[position].date
        holder.amount.text = reminderData[position].amount
        holder.sparePart.text = reminderData[position].sparePart
        holder.delete.setOnClickListener {
            val title = holder.title.toString()

            //codes for Deleting the reminder
            val builder = AlertDialog.Builder(mContext)
            builder.setTitle("Alert !")
            builder.setMessage("Are you sure you want to Delete this Reminder?")
            builder.setCancelable(false) //when the user clicks on the outside the Dialog Box then it will remain show

            //When the user click yes button what should happen
            builder.setPositiveButton("Yes") { dialog, which ->
                val dbHandler = DBHandler(mContext)
                dbHandler.deleteReminder(title)
            }
            builder.setNegativeButton("No") { dialog, which ->
                dialog.cancel() // If user click no then dialog box is canceled.
            }
            val alertDialog = builder.create() // Create the Alert dialog
            alertDialog.show() // Show the Alert Dialog box
        }
        holder.mLayout.setOnClickListener {
            val mIntent = Intent(mContext, ReminderAdapter::class.java)
            mIntent.putExtra("title", holder.title.text.toString())
            mIntent.putExtra("date", holder.date.text.toString())
            mIntent.putExtra("amount", holder.amount.text.toString())
            mIntent.putExtra("sparePart", holder.sparePart.text.toString())
            mIntent.putExtra("icon", holder.icon.text.toString())
            mContext.startActivity(mIntent)
        }
    }

    override fun getItemCount(): Int {
        return reminderData.size
    }

    init {
        this.reminderData = reminderData as List<ReminderData>
        this.mContext = mContext
    }
}

internal class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var icon: TextView
    var title: TextView
    var date: TextView
    var amount: TextView
    var sparePart: TextView
    var delete: Button
    var mLayout: RelativeLayout

    init {
        icon = itemView.findViewById(R.id.textView25)
        title = itemView.findViewById(R.id.textView18)
        date = itemView.findViewById(R.id.textView22)
        amount = itemView.findViewById(R.id.textView23)
        sparePart = itemView.findViewById(R.id.textView24)
        delete = itemView.findViewById(R.id.button8)
        mLayout = itemView.findViewById(R.id.layout)
    }
}