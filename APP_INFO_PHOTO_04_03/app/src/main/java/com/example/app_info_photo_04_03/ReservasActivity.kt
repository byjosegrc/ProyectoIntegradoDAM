package com.example.app_info_photo_04_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.app_info_photo_04_03.Calendario.Calendario
import com.example.app_info_photo_04_03.Horarios.reservaHoraInicio
import com.example.app_info_photo_04_03.databinding.ActivityReservasBinding
import java.util.Arrays

class ReservasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReservasBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityReservasBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)



        //LLamada a la funcion que va estar ejecutandose siempre para la escucha de eventos:
        setListeners()

        title = "RESERVA SESIONES"
    }



    private fun setListeners() {

        //Escucha para la seleccion de la hora inicio de la reserva de sesion
        binding.etHoraIni.setOnClickListener(){
            showTimeInicio()
        }

        //Escucha para la seleccion de la hora fin de la reserva de sesion
        binding.etHoraFin.setOnClickListener(){
            showTimeFin()
        }

        //Escucha para la seleccion del dia de la sesion de fotos:
        binding.etCalendario.setOnClickListener() {
            showDatePickerDialog()
        }


    }



    //Funcion hora fin de la sesión:
    //esta funcion carga el fragment del timePicker, para ello creo la clase reservaHoraFin
    private fun showTimeFin() {
        val timePicker = reservaHoraInicio { finSelected(it) }
        timePicker.show(supportFragmentManager, "horaFin")

    }

    //Funcion hora inicio de la sesión :

    //esta funcion carga el fragment del timePicker, para ello creo la clase reservaHoraInicio

    private fun showTimeInicio() {

        val timePicker = reservaHoraInicio { inicioSelected(it) }
        timePicker.show(supportFragmentManager, "Hora")

    }

//esta funcion nos dira la hora y minutos seleccionados al recoger la hora de inicio de la sesion
    private fun inicioSelected(timeIni: String) {
        binding.etHoraIni.setText("Reserva para las $timeIni")
    }

    private fun finSelected(timeFin: String) {
        binding.etHoraFin.setText("Reserva para las $timeFin")
    }


    //CALENDARIO:

    private fun showDatePickerDialog() {
        val datePicker = Calendario { day, month, year -> onDateSelected(day, month, year) }
        datePicker.show(supportFragmentManager, "datePicker")
    }

    private fun onDateSelected(day: Int, month: Int, year: Int) {
        binding.etCalendario.setText("Has seleccionado el $day del $month del año $year")
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


