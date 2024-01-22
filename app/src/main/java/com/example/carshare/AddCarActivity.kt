package com.example.carshare

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Spinner
import android.widget.TextView
import com.example.carshare.database.CarModel
import com.example.carshare.database.SQLiteHelper
import com.example.carshare.MainActivity

class AddCarActivity : AppCompatActivity() {
    private lateinit var sqliteHelper: SQLiteHelper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_car)
        sqliteHelper = SQLiteHelper(this)

        if (intent.getSerializableExtra("car_name")==null){

        }else{

            var car_name : TextView = findViewById(R.id.editTextCarName)
            car_name.text = intent.getSerializableExtra("car_name").toString()

            var car_class : Spinner = findViewById(R.id.spinnerCarClass)
            car_class.setSelection(resources.getStringArray(R.array.carClass).indexOf(intent.getSerializableExtra("car_class").toString()))

            var car_gearbox : Spinner = findViewById(R.id.spinnerCarGearbox)
            car_gearbox.setSelection(resources.getStringArray(R.array.carGearbox).indexOf(intent.getSerializableExtra("car_gearbox").toString()))

            var car_fuel : TextView = findViewById(R.id.editTextFuelRange)
            car_fuel.text = intent.getSerializableExtra("car_fuel").toString()
            //car_fuel.setSelection(resources.getStringArray(R.array.carFuelTank).indexOf(intent.getSerializableExtra("car_fuel").toString()))

            //var car_fuel : Spinner = findViewById(R.id.spinnerCarFuelTank)
            //car_fuel.setSelection(resources.getStringArray(R.array.carFuelTank).indexOf(intent.getSerializableExtra("car_fuel").toString()))
            var car_address : TextView = findViewById(R.id.editTextCarAddress)
            car_address.text = intent.getSerializableExtra("car_address").toString()

            var car_cost : TextView = findViewById(R.id.editTextCarPrice)
            car_cost.text = intent.getSerializableExtra("car_cost").toString()
            var car_passengers : TextView = findViewById(R.id.editTextNumberOfPeople)
            car_passengers.text = intent.getSerializableExtra("car_passengers").toString()
            var car_bags : TextView = findViewById(R.id.editTextNumberOfBags)
            car_bags.text =intent.getSerializableExtra("car_bags").toString()

//        var car_rating : TextView = findViewById(R.id.textRating)
//        car_rating.text = intent.getSerializableExtra("car_rating").toString()




        }
    }

    fun onContinueClick(view: View) {
        var createdCar : CarModel = CarModel()
        val carName : TextView = findViewById(R.id.editTextCarName)
        createdCar.make = carName.text.toString()
        val carModel : TextView = findViewById(R.id.editTextCarModel)
        createdCar.model = carModel.text.toString()
        val carType : Spinner = findViewById(R.id.spinnerCarClass)
        createdCar.type = carType.selectedItem.toString()
        val carSeats : TextView = findViewById(R.id.editTextNumberOfPeople)
        createdCar.numberOfSeats = carSeats.text.toString().toInt()
        val carBaggage : TextView = findViewById(R.id.editTextNumberOfBags)
        createdCar.spaceForBaggage = carBaggage.text.toString().toInt()
        val carProduction : TextView = findViewById(R.id.editTextProductionYear)
        createdCar.productionYear = carProduction.text.toString().toInt()
        val carGearbox : Spinner = findViewById(R.id.spinnerCarGearbox)
        createdCar.gearboxType = carGearbox.selectedItem.toString()
        val carFuel : TextView = findViewById(R.id.editTextFuelRange)
        createdCar.amountOfFuelInKm = carFuel.text.toString().toInt()
        val carDescription : TextView = findViewById(R.id.editTextCarDescription)
        createdCar.description = carDescription.text.toString()
        val carPrice : TextView = findViewById(R.id.editTextCarPrice)
        createdCar.price = carPrice.text.toString().toDouble()
        val carAddress : TextView = findViewById(R.id.editTextCarAddress)
        createdCar.location = carAddress.text.toString()

        createdCar.owner = intent.getStringExtra("phone").toString()
        sqliteHelper.insertCar(createdCar)

        finish()
    }
}