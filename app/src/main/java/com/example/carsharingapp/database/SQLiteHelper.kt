package com.example.carsharingapp.database

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.lang.Exception

class SQLiteHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "user.db"
        private const val TBL_USER = "tbl_user"
        private const val ID = "id"
        private const val FIRSTNAME = "firstname"
        private const val SURNAME = "surname"
        private const val PASSWORD = "password"
        private const val EMAIL = "email"
        private const val PHONE = "phone"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTblUser = ("CREATE TABLE " + TBL_USER + "("
                + ID + " INTEGER PRIMARY KEY," + FIRSTNAME + " TEXT," +
                SURNAME + " TEXT," + PASSWORD + " TEXT," + EMAIL + " TEXT,"
                + PHONE + " TEXT" + ")")
        db?.execSQL(createTblUser)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TBL_USER")
        onCreate(db)
    }

    //returns -1 if failed and 0 if worked
    fun insertUser(std: UserModel): Long {
       val db = this.writableDatabase
       val contentValues = ContentValues()
       contentValues.put(ID, std.id)
       contentValues.put(FIRSTNAME, std.firstname)
       contentValues.put(SURNAME, std.surname)
       contentValues.put(PASSWORD, std.password)
       contentValues.put(EMAIL, std.email)
       contentValues.put(PHONE, std.phone)

       val success = db.insert(TBL_USER, null, contentValues)
       db.close()
       return success
    }

    //returns true if phone in database, false if not
    //parameter: phone number as a string
    @SuppressLint("Recycle")
    fun isPhoneAlreadyUsed(newPhone: String): Boolean {
        val selectQuery = "SELECT * FROM $TBL_USER WHERE phone=\"$newPhone\" "
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            db.execSQL(selectQuery)
            e.printStackTrace()
            return false
        }
        val count = cursor.count
        val test = count >= 1
        cursor.close()
        Log.e("PHONE COUNTER", "$count")
        Log.e("RETURNING", "$test")
        return count >= 1
    }

    //return true if email in database, false if not
    //parameter: email as a string
    fun isEmailAlreadyUsed(newEmail: String): Boolean {
        val selectQuery = "SELECT * FROM $TBL_USER WHERE email=\"$newEmail\" "
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            db.execSQL(selectQuery)
            e.printStackTrace()
            return false
        }
        val count = cursor.count
        cursor.close()
        return count >= 1
    }

    //check if user with given password and user exists
    //returns true or false
    fun checkIfUserExists(password: String, email: String): Boolean {
        val selectQuery = "SELECT * FROM $TBL_USER WHERE email=\"$email\" AND password=\"$password\" "
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            db.execSQL(selectQuery)
            e.printStackTrace()
            return false
        }
        val count = cursor.count
        cursor.close()
        return count >= 1
    }

    //parameter: user email
    //output: user firstname
    //if user with given email not found returns "error"
    @SuppressLint("Range")
    fun getUserFirstnameFromEmail(email: String): String {
        val selectQuery = "SELECT firstname FROM $TBL_USER WHERE email=\"$email\" "
        val db = this.readableDatabase
        val cursor: Cursor?
        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            db.execSQL(selectQuery)
            e.printStackTrace()
            return "error"
        }
        cursor!!.moveToFirst()
        val firstname = cursor.getString(cursor.getColumnIndex("firstname"))
        cursor.close()
        return firstname
    }

    // returns all users in database as list
    @SuppressLint("Range")
    fun getAllUser(): ArrayList<UserModel> {
        val userList: ArrayList<UserModel> = ArrayList()
        val selectQuery = "SELECT * FROM $TBL_USER"
        val db = this.readableDatabase

        val cursor: Cursor?

        try {
            cursor = db.rawQuery(selectQuery, null)
        }catch (e: Exception){
            db.execSQL(selectQuery)
            e.printStackTrace()
            return ArrayList()
        }

        var id: Int
        var firstname: String
        var surname: String
        var email: String
        var phone: String

        if (cursor.moveToFirst()) {
            do {
                id = cursor.getInt(cursor.getColumnIndex("id"))
                firstname = cursor.getString(cursor.getColumnIndex("firstname"))
                surname = cursor.getString(cursor.getColumnIndex("surname"))
                email = cursor.getString(cursor.getColumnIndex("email"))
                phone = cursor.getString(cursor.getColumnIndex("phone"))

                val user = UserModel(id = id, firstname = firstname, surname = surname, email = email, phone = phone)
                userList.add(user)
            } while(cursor.moveToNext())
        }

        cursor.close()
        return userList
    }
}