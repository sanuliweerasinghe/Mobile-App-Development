package com.example.mytestingapplication

import androidx.appcompat.app.AppCompatActivity
import com.example.mytestingapplication.ReminderData
import java.util.ArrayList
import android.os.Bundle
import com.example.mytestingapplication.R
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.mytestingapplication.DBHandler
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.content.Intent
import com.example.mytestingapplication.Home
import com.example.mytestingapplication.AddReminder
import android.view.View as View1
import com.example.mytestingapplication.ReminderAdapter as ReminderAdapter1

class ReminderDetails : AppCompatActivity() {
    var reminderData: MutableList<ReminderData?> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reminder_details)
        val actionBar = supportActionBar
        val dynamicTitle = "Order Reminders"
        actionBar!!.title = dynamicTitle
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        //this layout manager is used to determine whether you want to allow the user to scroll in a vertical orientation.
        val mLinearLayoutManager = LinearLayoutManager(
            this@ReminderDetails,
            LinearLayoutManager.VERTICAL, false
        )

        //this item decoration is used to draw a line under each item in Recyclerview.
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                this@ReminderDetails,
                DividerItemDecoration.VERTICAL
            )
        )
        recyclerView.layoutManager = mLinearLayoutManager

        //Initializing the actual data that will show up in Recyclerview.
        val dbHandler = DBHandler(this)
        val db = dbHandler.readableDatabase
        val selectQuery = "select * from ReminderTable "
        val cursor = db.rawQuery(selectQuery, null)
        if (cursor.count > 0) {
            while (cursor.moveToNext()) {
                // Read columns data
                val title = cursor.getString(cursor.getColumnIndexOrThrow("Title"))
                val date = cursor.getString(cursor.getColumnIndexOrThrow("ReminderDate"))
                val part = cursor.getString(cursor.getColumnIndexOrThrow("PartName"))
                val amount = cursor.getString(cursor.getColumnIndexOrThrow("PartAmount"))
                val data = ReminderData(title, date, part, amount)
                reminderData.add(data)
            }
        }
        val reminderAdapter = ReminderAdapter1(this@ReminderDetails, reminderData)
        recyclerView.adapter = reminderAdapter
    }

    fun homeButtonClickFromReminderDetails(v: View1?) {
        startActivity(Intent(this@ReminderDetails, Home::class.java))
    }

    fun addReminderButtonClickFromReminderDetails(v: View1?) {
        startActivity(Intent(this@ReminderDetails, AddReminder::class.java))
    }
}