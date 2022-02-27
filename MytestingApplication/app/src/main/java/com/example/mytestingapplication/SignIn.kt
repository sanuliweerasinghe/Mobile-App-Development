package com.example.mytestingapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mytestingapplication.R
import android.view.View
import android.widget.EditText
import android.content.Intent
import com.example.mytestingapplication.Home
import android.widget.TextView

class SignIn : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        val actionBar = supportActionBar
        val dynamicTitle = "Sign In"
        actionBar!!.title = dynamicTitle
    }

    fun signInButtonClick(v: View?) //sign in button click event
    {
        val editTextUsername =
            findViewById<EditText>(R.id.editTextUsername) // finding the edittext for username
        val username = editTextUsername.text.toString() // getting the username input by user
        val editTextPassword =
            findViewById<EditText>(R.id.editTextPassword) // finding edittext for password
        val password = editTextPassword.text.toString() // getting the password input by user
        if (username == "kpalihakkara" && password == "kpg123") //only if both username and password match properly
        {
            startActivity(Intent(this@SignIn, Home::class.java))
        } else if (username == "kpalihakkara") //only if username matches properly
        {
            val error = "Check the Password!!!" // assigning error message to the textview
            val textview = findViewById<TextView>(R.id.textViewPasswordError)
            textview.text = error
            editTextPassword.isEnabled = true
        } else if (password == "kpg123") //only if password matches properly
        {
            val error = "Check the Username!!!" // assigning error message to the textview
            val textview = findViewById<TextView>(R.id.textViewUsernameError)
            textview.text = error
            editTextUsername.isEnabled = true
        } else  //both username and password do not match properly
        {
            val uError = "Check the Username!!!"
            val pError = "Check the Password!!!"
            val textview1 = findViewById<TextView>(R.id.textViewUsernameError)
            textview1.text = uError
            editTextUsername.isEnabled = true
            val textview2 = findViewById<TextView>(R.id.textViewPasswordError)
            textview2.text = pError
            editTextPassword.isEnabled = true
        }
    }
}