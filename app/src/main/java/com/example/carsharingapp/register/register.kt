package com.example.carsharingapp.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.carsharingapp.R
import com.example.carsharingapp.database.SQLiteHelper
import com.example.carsharingapp.database.UserModel
import java.util.regex.Pattern

class register : AppCompatActivity() {
    private lateinit var editFirstname: EditText
    private lateinit var editSurname: EditText
    private lateinit var editPassword: EditText
    private lateinit var editEmail: EditText
    private lateinit var editPhone: EditText
    private lateinit var addButton: Button
    private lateinit var testButton: Button

    private lateinit var sqliteHelper: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
        sqliteHelper = SQLiteHelper(this)

        addButton.setOnClickListener { addUser() }
        testButton.setOnClickListener { listUser() }
    }

    private fun listUser() {
        val userList = sqliteHelper.getAllUser()
        Log.e("counter", "${userList.size}")
    }
    private fun addUser() {
        val firstname = editFirstname.text.toString()
        val surname = editSurname.text.toString()
        val password = editPassword.text.toString()
        val email = editEmail.text.toString()
        val phone = editPhone.text.toString()

        if(firstname.isEmpty() || surname.isEmpty() || password.isEmpty() || email.isEmpty() || phone.isEmpty()) {
            Toast.makeText(this, "Please enter all values", Toast.LENGTH_SHORT).show()
        } else {
            if(sqliteHelper.isPhoneAlreadyUsed(phone)) {
                Toast.makeText(this, "Phone already in use", Toast.LENGTH_SHORT).show()
                return
            }
            if(sqliteHelper.isEmailAlreadyUsed(email)) {
                Toast.makeText(this, "Email already in use", Toast.LENGTH_SHORT).show()
                return
            }
            if(!isEmailValid(email)) {
                Toast.makeText(this, "Email is not valid", Toast.LENGTH_SHORT).show()
                return
            }
            val user = UserModel(firstname = firstname, surname = surname, password = password, email = email, phone = phone)
            val status = sqliteHelper.insertUser(user)
            if (status > -1) {
                Toast.makeText(this, "User registered successfully", Toast.LENGTH_SHORT).show()
                clearEditText()
            } else {
                Toast.makeText(this, "Registration failed, please contact our support", Toast.LENGTH_SHORT).show()
            }
            finish()
        }
    }

    fun isEmailValid(email: String): Boolean {
        val emailPattern = Pattern.compile("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")
        return emailPattern.matcher(email).matches()
    }

    private fun clearEditText() {
        editFirstname.setText("")
        editSurname.setText("")
        editPassword.setText("")
        editEmail.setText("")
        editPhone.setText("")
    }

    private fun initView() {
        editFirstname = findViewById(R.id.registerFirstname)
        editSurname = findViewById(R.id.registerSurname)
        editPassword = findViewById(R.id.registerPassword)
        editEmail = findViewById(R.id.registerEmail)
        editPhone = findViewById(R.id.registerPhone)
        addButton = findViewById(R.id.registerButton)
        testButton = findViewById(R.id.button)
    }
}