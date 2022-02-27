package com.example.mytestingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingapplication.R
import android.widget.ImageButton
import android.view.View
import android.content.Intent
import com.example.mytestingapplication.AddStock
import com.example.mytestingapplication.RemoveStock
import com.example.mytestingapplication.StockDetails
import com.example.mytestingapplication.Home

class StockDashboard : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_dashboard)
        val actionBar = supportActionBar
        val dynamicTitle = "Stock Dashboard"
        actionBar!!.title = dynamicTitle
        val addStockBtn = findViewById<ImageButton>(R.id.imageButtonAddStock)
        val removeStockBtn = findViewById<ImageButton>(R.id.imageButtonRemoveStock)
        val checkStockBtn = findViewById<ImageButton>(R.id.imageButtonCheckStock)
        addStockBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@StockDashboard,
                    AddStock::class.java
                )
            )
        }
        removeStockBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@StockDashboard,
                    RemoveStock::class.java
                )
            )
        }
        checkStockBtn.setOnClickListener {
            startActivity(
                Intent(
                    this@StockDashboard,
                    StockDetails::class.java
                )
            )
        }
    }

    /*
    public void addStockButtonClick(View v){
        startActivity(new Intent(StockDashboard.this, AddStock.class));
    }
    public void removeStockButtonClick(View v){
        startActivity(new Intent(StockDashboard.this, RemoveStock.class));
    }
    public void checkStockButtonClick(View v){ //code for reminder button click event
        startActivity(new Intent(StockDashboard.this, StockDetails.class));
    } */
    fun homeButtonClickFromStockDashboard(v: View?) {
        startActivity(Intent(this@StockDashboard, Home::class.java))
    }
}