package com.example.app_info_photo_04_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.example.app_info_photo_04_03.databinding.ActivityHomeBinding
import com.example.app_info_photo_04_03.pref.Prefs
import com.google.firebase.auth.FirebaseAuth



class HomeActivity : AppCompatActivity() {


    lateinit var binding: ActivityHomeBinding
    lateinit var prefs: Prefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title =
            "\uD83C\uDFE0 \uD835\uDC08\uD835\uDC0D\uD835\uDC08\uD835\uDC02\uD835\uDC08\uD835\uDC0E"
        setListeners()




        binding.bottomNavigationView.setOnItemSelectedListener {


            when (it.itemId) {

                R.id.home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.profile -> startActivity(Intent(this, PerfilUsuario::class.java))
                R.id.settings -> startActivity(Intent(this, settingsActivity::class.java))

                else -> {


                }

            }

            true

        }


    }


    private fun setListeners() {
        //BOTON RUTAS:
        binding.btnMaps.setOnClickListener {
            startActivity(Intent(this, RutasGoogle::class.java))
        }
        //BOTON CAMARA
        binding.btnCamara.setOnClickListener {
            startActivity(Intent(this, CamaraActivity::class.java))
        }
        //BOTON VIDEO
        binding.btnVideo.setOnClickListener {
            startActivity(Intent(this, SobreNosotros::class.java))
        }
        //BOTON SEDE
        binding.btnSede.setOnClickListener {
            startActivity(Intent(this, GoogleMaps1::class.java))
        }
        //BOTON CARUSEL
        binding.btnCarusel.setOnClickListener {
            startActivity(Intent(this, CaruselActivity::class.java))
        }
        //BOTON RESERVA
        binding.btReserva.setOnClickListener {
            startActivity(Intent(this, ReservasActivity::class.java))
        }
        //BOTON CHAT
        binding.btnChat.setOnClickListener {
            startActivity(Intent(this, foroPublicaciones::class.java))
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater.inflate(R.menu.menu_app, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.itemSalir -> {
                finishAffinity()
                true
            }
            R.id.itemCerrar -> {
                FirebaseAuth.getInstance().signOut()
                prefs.deleteAll()
                finish()
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }
    }
}






