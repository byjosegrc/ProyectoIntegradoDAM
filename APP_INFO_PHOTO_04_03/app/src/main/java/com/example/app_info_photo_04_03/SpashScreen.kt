package com.example.app_info_photo_04_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class SpashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startActivity(Intent(this, LoginActivity::class.java))
    }
}