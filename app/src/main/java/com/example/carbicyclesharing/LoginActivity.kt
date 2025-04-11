package com.example.carbicyclesharing

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
/*
class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_login)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}*/

//package org.geeksforgeeks.demo

import android.content.Intent
//import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
//import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth


class LoginActivity : AppCompatActivity() {
    private lateinit var emailTextView: EditText
    private lateinit var passwordTextView: EditText
    private lateinit var button: Button
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // creating instance of FirebaseAuth
        auth = FirebaseAuth.getInstance()

        emailTextView = findViewById(R.id.email_edittext)
        passwordTextView = findViewById(R.id.password_edittext)
        button = findViewById(R.id.login_button)

        button.setOnClickListener {
            loginUserAccount()
        }
    }

    private fun loginUserAccount() {
        val email = emailTextView.text.toString()
        val password = passwordTextView.text.toString()

        // Validations for input email and password
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "please enter credentials", Toast.LENGTH_LONG).show()
        } else {
            // login existing user
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    // login successful
                    Toast.makeText(this, "Login successful!!", Toast.LENGTH_LONG).show()
                    startActivity(Intent(this, MainActivity::class.java))
                } else {
                    // login failed
                    Toast.makeText(this, "Login failed!!", Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
