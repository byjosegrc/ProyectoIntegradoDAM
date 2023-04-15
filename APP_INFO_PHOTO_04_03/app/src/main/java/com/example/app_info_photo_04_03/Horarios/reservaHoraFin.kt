package com.example.app_info_photo_04_03.Horarios

import android.app.Dialog
import android.app.TimePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.TimePicker
import androidx.fragment.app.DialogFragment
import java.util.*


//dentro de la declaracion de la clase creo la variable horaInicio de tipo String que sera la hora seleccionada
//esto actua como un listener de la hora de inicio, tambien hago que extienda de DialogFragment  y añadir
// el listener de que el usuario a seleccionado una hora para ello añado  TimePickerDialog.OnTimeSetListener y ademas
// lo implemento
class reservaHoraFin (val horaFin:(String) -> Unit) : DialogFragment(), TimePickerDialog.OnTimeSetListener {


    //esta funcion nos avisara que la hora ha sido seleccionada
    override fun onTimeSet(view: TimePicker?, hourOfDay: Int, minute: Int) {
        //en mi variable horaFin concateno la hora del dia y los minutos y entre los dos ":" EJEMPLO: 18:20
        horaFin("$hourOfDay:$minute")
    }


    //para sobrescribir el DialogFragment implemento esta funcion la cual voy a sobreescribir
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val calendario = Calendar.getInstance() //hago una instacia de la variable calendario de tipo calendar de java util
        //variable hora:
        val hour = calendario.get(Calendar.HOUR_OF_DAY)
        //variable minutos:
        val minute = calendario.get(Calendar.MINUTE)

        val picker = TimePickerDialog(activity as Context, this, hour, minute, false)
        return picker
    }
}
