package com.example.mytestingapplication

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class ServiceForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_service_form)
        val actionBar = supportActionBar
        val dynamicTitle = "Add Vehicle Services"
        actionBar!!.title = dynamicTitle
    }

    fun serviceFormButtonClick(v: View?) {
        val editTextOwnerName =
            findViewById<EditText>(R.id.editTextOwnerNameService) // get owner name
        val ownerName = editTextOwnerName.text.toString()
        val editTextVehicleNo =
            findViewById<EditText>(R.id.editTextVehicleNoService) // get vehicle No
        val vehicleNo = editTextVehicleNo.text.toString()
        val editTextVehicleBrand =
            findViewById<EditText>(R.id.editTextVehicleBrandService) // get vehicle brand
        val vehicleBrand = editTextVehicleBrand.text.toString()
        val checkBoxS1 = findViewById<View>(R.id.checkBox1).isSelected
        val checkBoxS2 = findViewById<View>(R.id.checkBox2).isSelected
        val checkBoxS3 = findViewById<View>(R.id.checkBox3).isSelected
        val checkBoxS4 = findViewById<View>(R.id.checkBox4).isSelected
        val checkBoxS5 = findViewById<View>(R.id.checkBox5).isSelected
        val checkBoxS6 = findViewById<View>(R.id.checkBox6).isSelected
        var electricSystem = "False"
        var airCondition = "False"
        var suspension = "False"
        var oilFiltre = "False"
        var radiator = "False"
        var wheelBearing = "False"
        val currentTime =
            Calendar.getInstance().time // to get the current date and time a repair record is entered.
        val date = currentTime.toString()
        if (ownerName == "" || vehicleBrand == "" || vehicleNo == "") // check whether all fields are entered
        {
            val error = "Please Enter All Fields!!!"
            val textview = findViewById<TextView>(R.id.textViewFieldsErrorService)
            textview.text = error
        } else if (!checkBoxS1 || !checkBoxS2 || !checkBoxS3 || !checkBoxS4 || !checkBoxS5 || !checkBoxS6) // check whether the checklist is checked
        {
            val error = "Please Check the checklist!!!"
            val textview = findViewById<TextView>(R.id.textViewChecklistErrorService)
            textview.text = error
        } else {
            if (checkBoxS1) electricSystem = "True"
            if (checkBoxS2) airCondition = "True"
            if (checkBoxS3) suspension = "True"
            if (checkBoxS4) oilFiltre = "True"
            if (checkBoxS5) radiator = "True"
            if (checkBoxS6) wheelBearing = "True"

            // passing the variables to database table
            val db = DBHandler(this)
            val status = db.addService(
                date,
                ownerName,
                vehicleNo,
                vehicleBrand,
                electricSystem,
                airCondition,
                suspension,
                oilFiltre,
                radiator,
                wheelBearing
            )
            if (status > 0) {
                Toast.makeText(
                    applicationContext,
                    "Service Record Successfully Entered!!!",
                    Toast.LENGTH_LONG
                ).show() // message box to show insertion status
            } else {
                Toast.makeText(
                    applicationContext,
                    "Service Record Insertion Failed!!!",
                    Toast.LENGTH_LONG
                ).show() // message box to show insertion status
            }
        }
    }

    fun homeButtonClickFromServiceForm(v: View?) {
        startActivity(Intent(this@ServiceForm, Home::class.java))
    }
}