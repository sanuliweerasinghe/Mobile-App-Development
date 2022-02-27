package com.example.mytestingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingapplication.R
import android.view.View
import android.content.Intent
import com.example.mytestingapplication.RepairForm
import com.example.mytestingapplication.ServiceForm
import com.example.mytestingapplication.StockDashboard
import com.example.mytestingapplication.DatabaseDashboard
import com.example.mytestingapplication.ReminderDetails

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        val actionBar = supportActionBar
        val dynamicTitle = "Home Dashboard"
        actionBar!!.title = dynamicTitle
    }

    fun repairButtonClick(v: View?) {
        startActivity(Intent(this@Home, RepairForm::class.java))
    }

    fun serviceButtonClick(v: View?) {
        startActivity(Intent(this@Home, ServiceForm::class.java))
    }

    fun sparePartsButtonClick(v: View?) {
        startActivity(Intent(this@Home, StockDashboard::class.java))
    }

    fun databaseButtonClick(v: View?) {
        startActivity(Intent(this@Home, DatabaseDashboard::class.java))
    }

    fun remindersButtonClick(v: View?) {
        startActivity(Intent(this@Home, ReminderDetails::class.java))
    }
}