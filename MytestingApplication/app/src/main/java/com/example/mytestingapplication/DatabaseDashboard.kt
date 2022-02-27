package com.example.mytestingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingapplication.R
import android.view.View
import android.content.Intent
import com.example.mytestingapplication.Home
import com.example.mytestingapplication.ServiceDetails
import com.example.mytestingapplication.RepairDetails

class DatabaseDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_database_dashboard)
        val actionBar = supportActionBar
        val dynamicTitle = "Database Dashboard"
        actionBar!!.title = dynamicTitle
    }

    fun homeButtonClickFromDatabaseDashboard(v: View?) // home button
    {
        startActivity(Intent(this@DatabaseDashboard, Home::class.java))
    }

    fun serviceDetailsButtonClick(v: View?) // service details button
    {
        startActivity(Intent(this@DatabaseDashboard, ServiceDetails::class.java))
    }

    fun repairDetailsButtonClick(v: View?) // repair details button
    {
        startActivity(Intent(this@DatabaseDashboard, RepairDetails::class.java))
    }
}