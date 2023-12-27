package com.example.carsharingapp

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.carsharingapp.database.CarModel
import com.example.carsharingapp.database.SQLiteHelper
import com.example.carsharingapp.database.Transmission
import com.example.carsharingapp.database.UserModel
import com.example.carsharingapp.login.login
import com.example.carsharingapp.register.register

class MainActivity : AppCompatActivity() {
    private lateinit var goToRegisterButton: Button
    private lateinit var goToLoginButton: Button
    private lateinit var printAllUsers: Button
    val getContent = registerForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        // Handle the returned Uri
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()

        goToRegisterButton.setOnClickListener { goToRegister() }
        goToLoginButton.setOnClickListener { goToLogin() }
        printAllUsers.setOnClickListener { SQLiteHelper(this).printAllUser() }

        /*
        SAMPLE FUNCTION ADDING NEW CAR:
            val car = CarModel(make = "Audi", model = "A3", productionYear = 2013, gearboxType = Transmission.MANUAL)
            SQLiteHelper(this).insertCar(car)

        for more detailed sample check out 'addUser' function inside 'register.kt'
         */


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
            val sampleMSG = data?.getStringExtra("result")
            Log.e("counter", sampleMSG.toString())
            val result = sampleMSG.toString()
            if(result == "LOGGED_IN") {
                val name = data?.getStringExtra("name").toString()
                Toast.makeText(this, "Welcome, $name", Toast.LENGTH_SHORT).show()
            } else if (result == "WRONG_PASSWORD"){
                Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show()
            } else if (result == "WRONG_EMAIL") {
                val email = data?.getStringExtra("email").toString()
                Toast.makeText(this, "User $email doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun initView() {
        goToRegisterButton = findViewById(R.id.goToRegisterButton)
        goToLoginButton = findViewById(R.id.goToLoginButton)
        printAllUsers = findViewById(R.id.printAllUsers)
    }
}