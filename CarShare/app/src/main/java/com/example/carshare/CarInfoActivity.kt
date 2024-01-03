package com.example.carshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.carshare.ui.myCars.HomeAdapter

class CarInfoActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_car_info)


        var car_name : TextView = findViewById(R.id.textNameCar)
        car_name.text = intent.getSerializableExtra("car_name").toString()
        var car_class : TextView = findViewById(R.id.textClassCar)
        car_class.text = intent.getSerializableExtra("car_class").toString()
        var car_gearbox : TextView = findViewById(R.id.textGearbox)
        car_gearbox.text = intent.getSerializableExtra("car_gearbox").toString()
        var car_fuel : TextView = findViewById(R.id.textFuelTank)
        car_fuel.text= intent.getSerializableExtra("car_fuel").toString()
        var car_address : TextView = findViewById(R.id.textCarAddress)
        car_address.text = intent.getSerializableExtra("car_address").toString()
        var car_rating : TextView = findViewById(R.id.textRating)
        car_rating.text = intent.getSerializableExtra("car_rating").toString()
        var car_cost : TextView = findViewById(R.id.textCostPerDay)
        car_cost.text = intent.getSerializableExtra("car_cost").toString() + " PLN/day"
        var car_passengers : TextView = findViewById(R.id.textNumberOfPersons)
        car_passengers.text = "x" + intent.getSerializableExtra("car_passengers").toString()
        var car_bags : TextView = findViewById(R.id.textNumberOfBags)
        car_bags.text = "x" + intent.getSerializableExtra("car_bags").toString()

    }
}