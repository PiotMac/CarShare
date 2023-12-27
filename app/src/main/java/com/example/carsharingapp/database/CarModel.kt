package com.example.carsharingapp.database

import java.util.UUID

class CarModel(
    val id: String = UUID.randomUUID().toString(),
    var make: String = "",
    var model: String = "",
    var productionYear: Int,
    var gearboxType: Transmission,
){}