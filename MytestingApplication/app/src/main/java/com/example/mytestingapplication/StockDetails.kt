package com.example.mytestingapplication

import android.content.Intent
import android.database.sqlite.SQLiteException
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class StockDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stock_details)
    }

    fun homeButtonClickFromStockDetails(v: View?) {
        startActivity(Intent(this@StockDetails, Home::class.java))
    }

    fun viewButtonClickOfStock(v: View?) {
        val dbHandler = DBHandler(this)
        val tableLayout = findViewById<View>(R.id.tableLayoutStockTable) as TableLayout
        val rowHeader = TableRow(this)
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"))
        rowHeader.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        val headerText = arrayOf("PartID", "Part Name", "Available Amount")
        for (c in headerText) {
            val tv = TextView(this)
            tv.layoutParams = TableRow.LayoutParams(
                TableRow.LayoutParams.WRAP_CONTENT,
                TableRow.LayoutParams.WRAP_CONTENT
            )
            tv.gravity = Gravity.CENTER
            tv.textSize = 18f
            tv.setPadding(5, 5, 5, 5)
            tv.text = c
            rowHeader.addView(tv)
        }
        tableLayout.addView(rowHeader)

        // Get data from sqlite database and add them to the table
        // Open the database for reading
        val db = dbHandler.readableDatabase

        // Start the transaction
        db.beginTransaction()
        try {
            val selectQuery = "select * from StockTable"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    // Read columns data
                    val partID = cursor.getString(cursor.getColumnIndexOrThrow("PartID"))
                    val partName = cursor.getString(cursor.getColumnIndexOrThrow("PartName"))
                    val availableAmount =
                        cursor.getString(cursor.getColumnIndexOrThrow("AvailableNo"))

                    // data rows
                    val row = TableRow(this)
                    row.layoutParams = TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                    )
                    val colText = arrayOf(partID, partName, availableAmount)
                    for (text in colText) {
                        val tv = TextView(this)
                        tv.layoutParams = TableRow.LayoutParams(
                            TableRow.LayoutParams.WRAP_CONTENT,
                            TableRow.LayoutParams.WRAP_CONTENT
                        )
                        tv.gravity = Gravity.CENTER
                        tv.textSize = 16f
                        tv.setPadding(5, 5, 5, 5)
                        tv.text = text
                        row.addView(tv)
                    }
                    tableLayout.addView(row)
                }
            } else {
                Toast.makeText(applicationContext, "No Stock Details found!!!", Toast.LENGTH_LONG)
                    .show()
            }
            db.setTransactionSuccessful()
        } catch (e: SQLiteException) {
            e.printStackTrace()
            //Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
        } finally {
            db.endTransaction() // End the transaction.
            db.close() // Close database
        }
    }

    // code for the check shortages functionality
    var dbHandler = DBHandler(this)
    var checkButton = findViewById<Button>(R.id.buttonCheckShortage)
    fun checkStockShortages(v: View?) {
        checkButton.setOnClickListener(View.OnClickListener {
            val res = dbHandler.checkForStockShortages()
            if (res.count == 0) {
                showMessage("No data", "No shortages found")
                return@OnClickListener
            }
            val buffer = StringBuffer()
            while (res.moveToNext()) {
                buffer.append(
                    """
    PartID: ${res.getString(0)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    Spare Part Name: ${res.getString(1)}
    
    """.trimIndent()
                )
                buffer.append(
                    """
    Available Amount: ${res.getString(2)}
    
    
    """.trimIndent()
                )
            }
            showMessage("Shortages found in", buffer.toString())
        })
    }

    fun showMessage(title: String?, message: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(message)
        builder.show()
    }

    //Button AddReminder = findViewById(R.id.buttonStockAddReminder);
    fun addReminderButtonClickFromStockDetails(v: View?) {
        startActivity(Intent(this@StockDetails, AddReminder::class.java))
    }
}