package com.example.app_info_photo_04_03.pref

import android.content.Context

class Prefs(c: Context) {
    val storage = c.getSharedPreferences("APP",0)


    fun getEmail():String?{
        return  storage.getString("EMAIL",null)
    }

    fun setEmail(email:String){
        storage.edit().putString("EMAIL",email).apply()
    }

    fun deleteAll() {
        storage.edit().clear().apply()
    }
}