package com.example.mytestingapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class RepairForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_repair_form)
        val actionBar = supportActionBar
        val dynamicTitle = "Add Vehicle Repairs"
        actionBar!!.title = dynamicTitle
    }

    fun repairFormButtonClick(v: View?) {
        val editTextOwnerName =
            findViewById<EditText>(R.id.editTextOwnerNameRepair) // get owner name
        val ownerName = editTextOwnerName.text.toString()
        val editTextVehicleNo =
            findViewById<EditText>(R.id.editTextVehicleNoRepair) // get vehicle No
        val vehicleNo = editTextVehicleNo.text.toString()
        val editTextVehicleBrand =
            findViewById<EditText>(R.id.editTextVehicleBrandRepair) // get vehicle brand
        val vehicleBrand = editTextVehicleBrand.text.toString()
        val checkBoxR7 = findViewById<View>(R.id.checkBox7).isSelected
        val checkBoxR8 = findViewById<View>(R.id.checkBox8).isSelected
        val checkBoxR9 = findViewById<View>(R.id.checkBox9).isSelected
        val checkBoxR10 = findViewById<View>(R.id.checkBox10).isSelected
        val checkBoxR11 = findViewById<View>(R.id.checkBox11).isSelected
        val checkBoxR12 = findViewById<View>(R.id.checkBox12).isSelected
        val currentTime =
            Calendar.getInstance().time // to get the current date and time a repair record is entered.
        val date = currentTime.toString()

        //Button repairFormButton = (Button) findViewById(R.id.button);
        var ignitionSystem = "False"
        var tyre = "False"
        var sparkPlug = "False"
        var brakes = "False"
        var bodyPainting = "False"
        var light = "False"
        if (ownerName == "" || vehicleBrand == "" || vehicleNo == "") // check whether all fields are entered
        {
            val error = "Please Enter All Fields!!!"
            val textview = findViewById<TextView>(R.id.textViewFieldsErrorRepair)
            textview.text = error
        } else if (!checkBoxR7 || !checkBoxR8 || !checkBoxR9 || !checkBoxR10 || !checkBoxR11 || !checkBoxR12) // check whether the checklist is checked
        {
            val error = "Please Check the checklist!!!"
            val textview = findViewById<TextView>(R.id.textViewChecklistErrorRepair)
            textview.text = error
        } else {
            if (checkBoxR7) ignitionSystem = "True"
            if (checkBoxR8) tyre = "True"
            if (checkBoxR9) sparkPlug = "True"
            if (checkBoxR10) brakes = "True"
            if (checkBoxR11) bodyPainting = "True"
            if (checkBoxR12) light = "True"

            // passing the variables to database table
            val db = DBHandler(this)
            val status = db.addRepair(
                date,
                ownerName,
                vehicleNo,
                vehicleBrand,
                ignitionSystem,
                tyre,
                sparkPlug,
                brakes,
                bodyPainting,
                light
            )
            if (status > 0) {
                Toast.makeText(
                    applicationContext,
                    "Repair Record Successfully Entered!!!",
                    Toast.LENGTH_LONG
                ).show() // message box to show insertion status
            } else {
                Toast.makeText(
                    applicationContext,
                    "Repair Record Insertion Failed!!!",
                    Toast.LENGTH_LONG
                ).show() // message box to show insertion status
            }
        }
    }

    fun homeButtonClickFromRepairForm(v: View?) {
        startActivity(Intent(this@RepairForm, Home::class.java))
    }
}