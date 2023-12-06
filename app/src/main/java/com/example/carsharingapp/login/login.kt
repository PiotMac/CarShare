package com.example.carsharingapp.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.carsharingapp.MainActivity
import com.example.carsharingapp.R

class login : AppCompatActivity() {
    private lateinit var goBackButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()

        goBackButton.setOnClickListener { goBack() }
    }

    private fun goBack() {
        val intent = Intent(this, MainActivity::class.java)
        intent.putExtra("message", "super message")
        setResult(RESULT_OK, intent)
        finish()
    }
    private fun initView() {
        goBackButton = findViewById(R.id.goBackButton)
    }
}