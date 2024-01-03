package com.example.carshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Spinner
import android.widget.TextView

class AddCarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)

        if (intent.getSerializableExtra("car_name")==null){

        }else{
            var car_name : TextView = findViewById(R.id.editTextCarName)
            car_name.text = intent.getSerializableExtra("car_name").toString()

            var car_class : Spinner = findViewById(R.id.spinnerCarClass)
            car_class.setSelection(resources.getStringArray(R.array.carClass).indexOf(intent.getSerializableExtra("car_class").toString()))

            var car_gearbox : Spinner = findViewById(R.id.spinnerCarGearbox)
            car_gearbox.setSelection(resources.getStringArray(R.array.carGearbox).indexOf(intent.getSerializableExtra("car_gearbox").toString()))

            var car_fuel : Spinner = findViewById(R.id.spinnerCarFuelTank)
            car_fuel.setSelection(resources.getStringArray(R.array.carFuelTank).indexOf(intent.getSerializableExtra("car_fuel").toString()))
            var car_address : TextView = findViewById(R.id.editTextCarAddress)
            car_address.text = intent.getSerializableExtra("car_address").toString()

            var car_cost : TextView = findViewById(R.id.editTextCarPrice)
            car_cost.text = intent.getSerializableExtra("car_cost").toString()
            var car_passengers : TextView = findViewById(R.id.editTextNumberOfPersons)
            car_passengers.text = intent.getSerializableExtra("car_passengers").toString()
            var car_bags : TextView = findViewById(R.id.editTextNumberOfBags)
            car_bags.text =intent.getSerializableExtra("car_bags").toString()

//        var car_rating : TextView = findViewById(R.id.textRating)
//        car_rating.text = intent.getSerializableExtra("car_rating").toString()
        }

    }
}