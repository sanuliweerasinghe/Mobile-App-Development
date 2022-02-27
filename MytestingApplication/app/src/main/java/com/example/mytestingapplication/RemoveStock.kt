package com.example.mytestingapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class RemoveStock : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_remove_stock)
        val actionBar = supportActionBar
        val dynamicTitle = "Remove Stock"
        actionBar!!.title = dynamicTitle
    }
}