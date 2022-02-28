package com.example.mytestingapplication

import androidx.appcompat.app.AppCompatActivity
import android.widget.AdapterView
import android.os.Bundle
import com.example.mytestingapplication.R
import android.widget.Spinner
import android.widget.ArrayAdapter
import android.widget.Button
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.example.mytestingapplication.DBHandler
import android.content.Intent
import com.example.mytestingapplication.Home

class AddStock : AppCompatActivity(),  AdapterView.OnItemSelectedListener{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_stock)
        val actionBar = supportActionBar
        val dynamicTitle = "Add Stock"
        actionBar!!.title = dynamicTitle

        val spinner = findViewById<Spinner>(R.id.spinnerAddPart)
        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.part_list,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter
        spinner.onItemSelectedListener = this

        val editTextSparePart = findViewById<EditText>(R.id.editTextNumber2)

        // button click event for the clear button
        val clearButton = findViewById<Button>(R.id.buttonClearAddParts)
        clearButton.setOnClickListener {
            editTextSparePart.setText("")
            editTextSparePart.isEnabled = true
        }
    }
    fun addStockUpdateButtonClick(v: View?) {
        val editTextSparePart = findViewById<EditText>(R.id.editTextNumber2)
        val partNo = editTextSparePart.text.toString()
        val amount = partNo.toInt()
        val spinnerPartName = findViewById<View>(R.id.spinnerAddPart) as Spinner
        val partName = spinnerPartName.selectedItem.toString()
        if (partNo == "") {
            Toast.makeText(applicationContext, "Please Enter Number of Parts!!!", Toast.LENGTH_LONG)
                .show()
        } else if (spinnerPartName.selectedItemPosition < 0) {
            Toast.makeText(applicationContext, "Please Select a Spare Part!!!", Toast.LENGTH_LONG)
                .show()
        } else {
            val db = DBHandler(this)
            val status = db.addStock(partName, amount)
            if (status > 0) {
                Toast.makeText(
                    applicationContext,
                    "New Stock successfully Added!!!",
                    Toast.LENGTH_LONG
                ).show() // message box to show insertion status
            } else {
                Toast.makeText(applicationContext, "Stock Update Failed!!!", Toast.LENGTH_LONG)
                    .show() // message box to show insertion status
            }
        }
    }
    override fun onItemSelected(parent: AdapterView<*>?, view: View, position: Int, id: Long) {
        //String text = parent.getItemAtPosition(position).toString();
        //Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}
    fun homeButtonClickFromAddStock(v: View?) {
        startActivity(Intent(this@AddStock, Home::class.java))
    }
}