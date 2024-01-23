package com.example.carshare.database

import java.util.Date
import java.util.UUID
class TransactionModel (
    val id: String = UUID.randomUUID().toString(),
    var carID: String = "",
    var ownerID: String = "",
    var borrowerID: String = "",
    var price: Double = 0.0,
    var date : Date? = null
){}