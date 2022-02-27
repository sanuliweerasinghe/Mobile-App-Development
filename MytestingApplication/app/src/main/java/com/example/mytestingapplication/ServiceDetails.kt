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

class ServiceDetails : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_details)
        val actionBar = supportActionBar
        val dynamicTitle = "Service Details"
        actionBar!!.title = dynamicTitle
    }

    //to retrieve repair details to a Table Layout
    fun viewButtonClickOfServiceDetails(v: View?) {
        val dbHandler = DBHandler(this)
        val tableLayout = findViewById<View>(R.id.tableLayoutServiceTable) as TableLayout
        val rowHeader = TableRow(this)
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"))
        rowHeader.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        val headerText = arrayOf(
            "ServiceID",
            "Date of Service",
            "Owner's Name",
            "Vehicle No",
            "Vehicle Brand",
            "Electrical System",
            "AC",
            "Suspension",
            "Oil Filter",
            "Radiator",
            "Wheel Bearing"
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
            val selectQuery = "select * from ServiceTable"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    // Read columns data
                    val serviceID = cursor.getString(cursor.getColumnIndexOrThrow("ServiceID"))
                    val date = cursor.getString(cursor.getColumnIndexOrThrow("Date"))
                    val ownerName = cursor.getString(cursor.getColumnIndexOrThrow("OwnerName"))
                    val vehicleNo = cursor.getString(cursor.getColumnIndexOrThrow("VehicleNo"))
                    val vehicleBrand =
                        cursor.getString(cursor.getColumnIndexOrThrow("VehicleBrand"))
                    val service1 =
                        cursor.getString(cursor.getColumnIndexOrThrow("ElectricalSystem"))
                    val service2 = cursor.getString(cursor.getColumnIndexOrThrow("AC"))
                    val service3 = cursor.getString(cursor.getColumnIndexOrThrow("Suspension"))
                    val service4 = cursor.getString(cursor.getColumnIndexOrThrow("OilFiltre"))
                    val service5 = cursor.getString(cursor.getColumnIndexOrThrow("Radiator"))
                    val service6 = cursor.getString(cursor.getColumnIndexOrThrow("WheelBearing"))

                    // data rows
                    val row = TableRow(this)
                    row.layoutParams = TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                    )
                    val colText = arrayOf(
                        serviceID,
                        date,
                        ownerName,
                        vehicleNo,
                        vehicleBrand,
                        service1,
                        service2,
                        service3,
                        service4,
                        service5,
                        service6
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
                Toast.makeText(applicationContext, "No Service Records found!!!", Toast.LENGTH_LONG)
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
    fun searchButtonClickOfServiceDetails(v: View?) {
        val editText = findViewById<EditText>(R.id.editTextSearchBoxForServices)
        val searchVehicleNo = editText.toString()
        val dbHandler = DBHandler(this)
        val tableLayout = findViewById<View>(R.id.tableLayoutServiceTable) as TableLayout
        val rowHeader = TableRow(this)
        rowHeader.setBackgroundColor(Color.parseColor("#c0c0c0"))
        rowHeader.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT
        )
        val headerText = arrayOf(
            "ServiceID",
            "Date of Service",
            "Owner's Name",
            "Vehicle No",
            "Vehicle Brand",
            "Electrical System",
            "AC",
            "Suspension",
            "Oil Filter",
            "Radiator",
            "Wheel Bearing"
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
            val selectQuery = "select * from ServiceTable where VehicleNo =$searchVehicleNo"
            val cursor = db.rawQuery(selectQuery, null)
            if (cursor.count > 0) {
                while (cursor.moveToNext()) {
                    // Read columns data
                    val serviceID = cursor.getString(cursor.getColumnIndexOrThrow("ServiceID"))
                    val date = cursor.getString(cursor.getColumnIndexOrThrow("Date"))
                    val ownerName = cursor.getString(cursor.getColumnIndexOrThrow("OwnerName"))
                    val vehicleNo = cursor.getString(cursor.getColumnIndexOrThrow("VehicleNo"))
                    val vehicleBrand =
                        cursor.getString(cursor.getColumnIndexOrThrow("VehicleBrand"))
                    val service1 =
                        cursor.getString(cursor.getColumnIndexOrThrow("ElectricalSystem"))
                    val service2 = cursor.getString(cursor.getColumnIndexOrThrow("AC"))
                    val service3 = cursor.getString(cursor.getColumnIndexOrThrow("Suspension"))
                    val service4 = cursor.getString(cursor.getColumnIndexOrThrow("OilFiltre"))
                    val service5 = cursor.getString(cursor.getColumnIndexOrThrow("Radiator"))
                    val service6 = cursor.getString(cursor.getColumnIndexOrThrow("WheelBearing"))

                    // data rows
                    val row = TableRow(this)
                    row.layoutParams = TableLayout.LayoutParams(
                        TableLayout.LayoutParams.MATCH_PARENT,
                        TableLayout.LayoutParams.WRAP_CONTENT
                    )
                    val colText = arrayOf(
                        serviceID,
                        date,
                        ownerName,
                        vehicleNo,
                        vehicleBrand,
                        service1,
                        service2,
                        service3,
                        service4,
                        service5,
                        service6
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
                    "No Service Records found for the searched Vehicle No!!!",
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
        // db.viewSearchResultsForRepairs(searchVehicleNo);
    }

    fun homeButtonClickFromServiceDetails(v: View?) {
        startActivity(Intent(this@ServiceDetails, Home::class.java))
    }
}