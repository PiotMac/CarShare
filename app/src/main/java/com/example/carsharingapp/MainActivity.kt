package com.example.carsharingapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.contract.ActivityResultContracts
import com.example.carsharingapp.login.login
import com.example.carsharingapp.register.register

class MainActivity : AppCompatActivity() {
    private lateinit var goToRegisterButton: Button
    private lateinit var goToLoginButton: Button
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        goToRegisterButton.setOnClickListener { goToRegister() }
        goToLoginButton.setOnClickListener { goToLogin() }
    }

    private fun goToRegister() {
        val intent = Intent(this, register::class.java)
        startActivity(intent)
    }
    private fun goToLogin() {
        val intent = Intent(this, login::class.java)
        resultLauncher.launch(intent)
    //startActivity(intent)
    }

    var resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            // There are no request codes
            val data: Intent? = result.data
            val sampleMSG = data?.getStringExtra("message")
            Log.e("counter", sampleMSG.toString())
        }
    }

    private fun initView() {
        goToRegisterButton = findViewById(R.id.goToRegisterButton)
        goToLoginButton = findViewById(R.id.goToLoginButton)
    }
}