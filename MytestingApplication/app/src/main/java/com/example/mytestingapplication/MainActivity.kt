package com.example.mytestingapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var myDB: DBHandler? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val actionBar = supportActionBar
        val dynamicTitle = "K.P.G Autoways App"
        actionBar!!.title = dynamicTitle

        // when the app is running for the first time, in order to create the database and the tables
        myDB = DBHandler(this)

        // inserting initial values to the Stock Table when running for the first time
        myDB!!.insertStockTablePart1()
        myDB!!.insertStockTablePart2()
        myDB!!.insertStockTablePart3()
        myDB!!.insertStockTablePart4()
        myDB!!.insertStockTablePart5()
        myDB!!.insertStockTablePart6()
        myDB!!.insertStockTablePart7()
    }

    fun getStartedButtonClick(v: View?) {
        startActivity(Intent(this@MainActivity, SignIn::class.java))
    }
}