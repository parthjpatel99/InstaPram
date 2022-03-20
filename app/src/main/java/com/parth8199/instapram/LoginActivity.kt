package com.parth8199.instapram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textview.MaterialTextView
import com.parse.ParseUser

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.username_edit_text).text.toString()
            val password = findViewById<TextInputEditText>(R.id.password_edit_text).text.toString()
            loginUser(username, password)
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(
            username, password, ({ user, e ->
                if (user != null) {
                    //Login Successful
                    Log.i(TAG, "Logged in Successfully")
                } else {
                    // Signup failed.  Look at the ParseException to see what happened.
                    e.printStackTrace()
                    Toast.makeText(this, "Error Logging in", Toast.LENGTH_SHORT).show()
                }
            })
        )
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}