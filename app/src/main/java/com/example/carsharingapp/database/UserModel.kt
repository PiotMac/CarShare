package com.example.carsharingapp.database

import java.util.Random

class UserModel(
    var id: Int = getAutoId(),
    var firstname: String = "",
    var surname: String = "",
    var password: String="",
    var email: String = "",
    var phone: String = ""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }
}