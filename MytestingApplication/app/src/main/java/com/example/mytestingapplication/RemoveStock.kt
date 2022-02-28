package com.example.mytestingapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RemoveStock : AppCompatActivity() ,AdapterView.OnItemSelectedListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_stock)
        val actionBar = supportActionBar
        val dynamicTitle = "Remove Stock"
        actionBar!!.title = dynamicTitle

        val spinner = findViewById<Spinner>(R.id.spinnerRemovePart)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.part_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        val editTextSparePart = findViewById<EditText>(R.id.editTextNumber)

        // button click event for the clear button
        val clearButton = findViewById<Button>(R.id.buttonClearRemoveParts)
        clearButton.setOnClickListener {
            editTextSparePart.setText("")
            editTextSparePart.isEnabled = true
        }
    }
    fun removeStockUpdateButtonClick(v: View?) {
        val editTextSparePart = findViewById<EditText>(R.id.editTextNumber)
        val partNo = editTextSparePart.text.toString()
        val amount = partNo.toInt()
        val spinnerPartName = findViewById<View>(R.id.spinnerRemovePart) as Spinner
        val partName = spinnerPartName.selectedItem.toString()
        if (partNo == "") {
            Toast.makeText(applicationContext, "Please Enter Number of Parts!!!", Toast.LENGTH_LONG)
                .show()
        } else if (spinnerPartName.selectedItemPosition < 0) {
            Toast.makeText(applicationContext, "Please Select a Spare Part!!!", Toast.LENGTH_LONG)
                .show()
        } else {
            val db = DBHandler(this)
            val status = db.removeStock(partName, amount)
            if (status > 0) {
                Toast.makeText(
                    applicationContext,
                    "Stock successfully Removed!!!",
                    Toast.LENGTH_LONG
                ).show() // message box to show insertion status
            } else {
                Toast.makeText(applicationContext, "Stock Update Failed!!!", Toast.LENGTH_LONG)
                    .show() // message box to show insertion status
            }
        }
    }
    override fun onItemSelected(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val text = parent.getItemAtPosition(position).toString()
        Toast.makeText(parent.context, text, Toast.LENGTH_SHORT).show()
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    fun homeButtonClickFromRemoveStock(v: View?) {
        startActivity(Intent(this@RemoveStock, Home::class.java))
    }
}