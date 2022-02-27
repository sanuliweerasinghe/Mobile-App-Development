package com.example.mytestingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingapplication.R
import android.view.View
import com.example.mytestingapplication.DBHandler
import android.widget.TableLayout
import android.widget.TableRow
import android.graphics.Color
import android.widget.TextView
import android.view.Gravity
import android.database.sqlite.SQLiteDatabase
import android.database.Cursor
import android.widget.Toast
import android.database.sqlite.SQLiteException
import android.widget.EditText
import android.content.Intent
import com.example.mytestingapplication.Home

class RepairDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repair_details)
        val actionBar = supportActionBar
        val dynamicTitle = "Repair Details"
        actionBar!!.title = dynamicTitle
    }

    //to retrieve repair details to a Table Layout
    fun viewButtonClickOfRepairDetails(v: View?) {
        val dbHandler = DBHandler(this)
        val tableLayout = findViewById<View>(R.id.tableLayoutRepairTable) as TableLayout
        val rowHeader = TableRow(this)
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"))
        rowHeader.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        val headerText = arrayOf(
            "RepairID",
            "Date of Repair",
            "Owner's Name",
            "Vehicle No",
            "Vehicle Brand",
            "IgnitionSystem",
            "Tyre",
            "Spark Plug",
            "Brakes",
            "Body Painting",
            "Light"
        )
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
            val selectQuery = "select * from RepairTable"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    // Read columns data
                    val repairID = cursor.getString(cursor.getColumnIndexOrThrow("RepairID"))
                    val date = cursor.getString(cursor.getColumnIndexOrThrow("Date"))
                    val ownerName = cursor.getString(cursor.getColumnIndexOrThrow("OwnerName"))
                    val vehicleNo = cursor.getString(cursor.getColumnIndexOrThrow("VehicleNo"))
                    val vehicleBrand =
                        cursor.getString(cursor.getColumnIndexOrThrow("VehicleBrand"))
                    val repair1 = cursor.getString(cursor.getColumnIndexOrThrow("IgnitionSystem"))
                    val repair2 = cursor.getString(cursor.getColumnIndexOrThrow("Tyre"))
                    val repair3 = cursor.getString(cursor.getColumnIndexOrThrow("SparkPlug"))
                    val repair4 = cursor.getString(cursor.getColumnIndexOrThrow("Brake"))
                    val repair5 = cursor.getString(cursor.getColumnIndexOrThrow("BodyPainting"))
                    val repair6 = cursor.getString(cursor.getColumnIndexOrThrow("Light"))

                    // data rows
                    val row = TableRow(this)
                    row.layoutParams = TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                    )
                    val colText = arrayOf(
                        repairID,
                        date,
                        ownerName,
                        vehicleNo,
                        vehicleBrand,
                        repair1,
                        repair2,
                        repair3,
                        repair4,
                        repair5,
                        repair6
                    )
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
                Toast.makeText(applicationContext, "No Repair Records found!!!", Toast.LENGTH_LONG)
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

    //Code for Search by vehicle No functionality
    fun searchButtonClickOfRepairDetails(v: View?) {
        val editText = findViewById<EditText>(R.id.editTextSearchBoxForRepairs)
        val searchVehicleNo = editText.toString()
        val dbHandler = DBHandler(this)
        val tableLayout = findViewById<View>(R.id.tableLayoutRepairTable) as TableLayout
        val rowHeader = TableRow(this)
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"))
        rowHeader.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        val headerText = arrayOf(
            "RepairID",
            "Date of Repair",
            "Owner's Name",
            "Vehicle No",
            "Vehicle Brand",
            "IgnitionSystem",
            "Tyre",
            "Spark Plug",
            "Brakes",
            "Body Painting",
            "Light"
        )
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
            val selectQuery = "select * from RepairTable where VehicleNo =$searchVehicleNo"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    // Read columns data
                    val repairID = cursor.getString(cursor.getColumnIndexOrThrow("RepairID"))
                    val date = cursor.getString(cursor.getColumnIndexOrThrow("Date"))
                    val ownerName = cursor.getString(cursor.getColumnIndexOrThrow("OwnerName"))
                    val vehicleNo = cursor.getString(cursor.getColumnIndexOrThrow("VehicleNo"))
                    val vehicleBrand =
                        cursor.getString(cursor.getColumnIndexOrThrow("VehicleBrand"))
                    val repair1 = cursor.getString(cursor.getColumnIndexOrThrow("IgnitionSystem"))
                    val repair2 = cursor.getString(cursor.getColumnIndexOrThrow("Tyre"))
                    val repair3 = cursor.getString(cursor.getColumnIndexOrThrow("SparkPlug"))
                    val repair4 = cursor.getString(cursor.getColumnIndexOrThrow("Brake"))
                    val repair5 = cursor.getString(cursor.getColumnIndexOrThrow("BodyPainting"))
                    val repair6 = cursor.getString(cursor.getColumnIndexOrThrow("Light"))

                    // data rows
                    val row = TableRow(this)
                    row.layoutParams = TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                    )
                    val colText = arrayOf(
                        repairID,
                        date,
                        ownerName,
                        vehicleNo,
                        vehicleBrand,
                        repair1,
                        repair2,
                        repair3,
                        repair4,
                        repair5,
                        repair6
                    )
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
                Toast.makeText(
                    applicationContext,
                    "No Repair Records found for the searched Vehicle No!!!",
                    Toast.LENGTH_LONG
                ).show()
            }
            db.setTransactionSuccessful()
        } catch (e: SQLiteException) {
            e.printStackTrace()
        } finally {
            db.endTransaction() // End the transaction.
            db.close() // Close database
        }
    }

    fun homeButtonClickFromRepairDetails(v: View?) {
        startActivity(Intent(this@RepairDetails, Home::class.java))
    }
}