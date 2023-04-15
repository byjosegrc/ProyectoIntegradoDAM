package com.example.app_info_photo_04_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AppCompatDelegate
import com.example.app_info_photo_04_03.databinding.ActivitySettingsBinding
import com.example.app_info_photo_04_03.pref.Prefs
import com.google.android.material.switchmaterial.SwitchMaterial

class settingsActivity : AppCompatActivity() {
    lateinit var binding: ActivitySettingsBinding
    lateinit var prefs: Prefs
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding= ActivitySettingsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title="\uD83C\uDFE0 \uD835\uDC08\uD835\uDC0D\uD835\uDC08\uD835\uDC02\uD835\uDC08\uD835\uDC0E"


        //Creo la variable de tema oscuro:
        val swDarkMode = findViewById<SwitchMaterial>(R.id.swDarkMode)

        //SI SE SELECCIONA EL SWITCH:

        swDarkMode.setOnCheckedChangeListener { _, isSelected ->
            //Si se cumple
            if (isSelected){
                //funcion para tema oscuro ON
                enableDarkMode()
            }else{ //si no ...
                //funcion para tema oscuro OFF
                disableDarkMode()
            }
    }


        binding.bottomNavigationView.setOnItemSelectedListener {


            when(it.itemId){

                R.id.home -> startActivity(Intent(this, HomeActivity::class.java))
                R.id.profile -> startActivity(Intent(this, SobreNosotros::class.java))
                R.id.settings -> startActivity(Intent(this, settingsActivity::class.java))

                else ->{



                }

            }

            true

        }


    }

    private fun enableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }

    private fun disableDarkMode(){
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

}