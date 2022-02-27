package com.example.mytestingapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.AdapterView
import android.os.Bundle
import com.example.mytestingapplication.R
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.mytestingapplication.DBHandler

class AddReminder : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)
        val actionBar = supportActionBar
        val dynamicTitle = "Add Reminders"
        actionBar!!.title = dynamicTitle
    }
}