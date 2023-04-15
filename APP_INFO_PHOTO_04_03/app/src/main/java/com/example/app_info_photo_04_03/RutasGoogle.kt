package com.example.app_info_photo_04_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.app_info_photo_04_03.databinding.ActivityRutasGoogleBinding

class RutasGoogle : AppCompatActivity() {
    lateinit var binding: ActivityRutasGoogleBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutas_google)
        title="RUTAS POR ANDALUCIA"
        binding= ActivityRutasGoogleBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setListener()
    }

    private fun setListener() {
        binding.btnGranada.setOnClickListener{
            startActivity(Intent(this, Granada::class.java))
        }
    }


    //----------------------------------------------------------------------------------------------


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemCerrar->{
                //               prefs.borrarTodo()
//                FirebaseAuth.getInstance().signOut()
                finish()
                true
            }
            R.id.itemSalir->{
                finishAffinity()
                true
            }
            R.id.inicio -> {
                startActivity(Intent(this, HomeActivity::class.java))
                true
            }
            else -> true
        }
    }
}