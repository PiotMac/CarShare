package com.example.carsharingapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.carsharingapp.MainActivity
import com.example.carsharingapp.R
import com.example.carsharingapp.database.SQLiteHelper

class login : AppCompatActivity() {
    private lateinit var loginButton: Button
    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var editPassword: EditText
    private lateinit var editEmail: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
        sqliteHelper = SQLiteHelper(this)

        loginButton.setOnClickListener { login() }
    }

    /*
    Tries to login user, returns two values:
    'result':
        'LOGGED_IN' - everything worked fine, second value is 'name'
        'WRONG_PASSWORD'
        'WRONG_EMAIL' - email not in database, second value is 'email'
     */
    private fun login() {
        val intent = Intent(this, MainActivity::class.java)
        if(sqliteHelper.isEmailAlreadyUsed(editEmail.text.toString())) {
            if(sqliteHelper.checkIfUserExists(editPassword.text.toString(), editEmail.text.toString())) {
                intent.putExtra("result", "LOGGED_IN")
                intent.putExtra("name", sqliteHelper.getUserFirstnameFromEmail(editEmail.text.toString()))
            } else {
                intent.putExtra("result", "WRONG_PASSWORD")
            }
        } else {
            intent.putExtra("result", "WRONG_EMAIL")
            intent.putExtra("email", editEmail.text.toString())
        }
        setResult(RESULT_OK, intent)
        finish()
    }
    private fun initView() {
        loginButton = findViewById(R.id.loginButton)
        editPassword = findViewById(R.id.loginPassword)
        editEmail = findViewById(R.id.loginEmail)
    }
}