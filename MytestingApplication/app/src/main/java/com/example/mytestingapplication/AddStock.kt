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

class AddStock : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_stock)
        val actionBar = supportActionBar
        val dynamicTitle = "Add Stock"
        actionBar!!.title = dynamicTitle
    }
    fun homeButtonClickFromAddStock(v: View?) {
        startActivity(Intent(this@AddStock, Home::class.java))
    }
}