package com.parth8199.instapram

import android.content.Intent
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
        getSupportActionBar()!!.hide();
        if (ParseUser.getCurrentUser() != null) {
          goToMainActivity()
      }

        findViewById<MaterialButton>(R.id.btnLogin).setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.username_edit_text).text.toString()
            val password = findViewById<TextInputEditText>(R.id.password_edit_text).text.toString()
            loginUser(username, password)
        }
        findViewById<MaterialButton>(R.id.btnSignin).setOnClickListener {
            val username = findViewById<TextInputEditText>(R.id.username_edit_text).text.toString()
            val password = findViewById<TextInputEditText>(R.id.password_edit_text).text.toString()
            signUp(username, password)
        }
    }

    private fun signUp(username: String, password: String) {
        val user = ParseUser()

        // Set fields for the user to be created
        user.setUsername(username)
        user.setPassword(password)

        user.signUpInBackground { e ->
            if (e == null) {
                // Hooray! Let them use the app now.

            } else {
                // Sign up didn't succeed. Look at the ParseException
                // to figure out what went wrong
                e.printStackTrace()

            }
        }
    }

    private fun loginUser(username: String, password: String) {
        ParseUser.logInInBackground(
            username, password, ({ user, e ->
                if (user != null) {
                    //Login Successful
                    Log.i(TAG, "Logged in Successfully")
                    goToMainActivity()
                } else {
                    // Signup failed.  Look at the ParseException to see what happened.
                    e.printStackTrace()
                    Toast.makeText(this, "Error Logging in", Toast.LENGTH_SHORT).show()
                }
            })
        )
    }

    private fun goToMainActivity() {
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    companion object {
        const val TAG = "LoginActivity"
    }
}