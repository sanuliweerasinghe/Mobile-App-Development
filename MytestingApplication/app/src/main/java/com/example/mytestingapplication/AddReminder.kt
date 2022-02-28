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

class AddReminder : AppCompatActivity(), AdapterView.OnItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_reminder)
        val actionBar = supportActionBar
        val dynamicTitle = "Add Reminders"
        actionBar!!.title = dynamicTitle

        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.part_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this
    }
    fun addReminderButtonClick(v: View?) {
        val editTextTitle = findViewById<EditText>(R.id.editTextReminderTitle)
        val editTextDate = findViewById<EditText>(R.id.editTextReminderDate)
        val editTextParts = findViewById<EditText>(R.id.editTextReminderParts)
        val spinnerPartName = findViewById<View>(R.id.spinner) as Spinner
        val title = editTextTitle.text.toString()
        val date = editTextDate.text.toString()
        val part = spinnerPartName.selectedItem.toString()
        val noOfParts = editTextParts.text.toString()

        // check whether all fields are entered by user
        if (title == "" || date == "" || noOfParts == "") {
            Toast.makeText(applicationContext, "Please Enter All Fields!!!", Toast.LENGTH_LONG)
                .show()
        } else if (spinnerPartName.selectedItemPosition < 0) {
            Toast.makeText(applicationContext, "Please Select a Spare Part!!!", Toast.LENGTH_LONG)
                .show()
        } else {
            // passing the variables to database table
            val db = DBHandler(this)
            val status = db.addReminder(title, date, part, noOfParts)
            if (status > 0) {
                Toast.makeText(
                    applicationContext,
                    "Reminder Successfully Added!!!",
                    Toast.LENGTH_LONG
                ).show() // message box to show insertion status
            } else {
                Toast.makeText(applicationContext, "Reminder Failed!!!", Toast.LENGTH_LONG)
                    .show() // message box to show insertion status
            }
        }
    }

    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        // val text = parent.getItemAtPosition(position).toString()
        // Toast.makeText(parent.context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
}
