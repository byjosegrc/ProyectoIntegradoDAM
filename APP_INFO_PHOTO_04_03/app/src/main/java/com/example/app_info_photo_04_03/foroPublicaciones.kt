package com.example.app_info_photo_04_03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_info_photo_04_03.adapter.ForoAdapters

import com.example.app_info_photo_04_03.databinding.ActivityForoPublicacionesBinding
import com.example.app_info_photo_04_03.model.Publicacion
import com.example.app_info_photo_04_03.pref.Prefs
import com.google.firebase.database.FirebaseDatabase

class foroPublicaciones : AppCompatActivity() {
    //variables:

    lateinit var binding: ActivityForoPublicacionesBinding
    //variable para la base de datos:
    lateinit var db: FirebaseDatabase
    //preferencias de datos:
    lateinit var prefs: Prefs
    //adapter del foro:
    lateinit var adapter: ForoAdapters

    var lista = ArrayList<Publicacion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityForoPublicacionesBinding.inflate(layoutInflater)

        //conexion a la base de datos de real time database de mi proyecto de firebase
        db = FirebaseDatabase.getInstance("https://infophoto-2023-default-rtdb.europe-west1.firebasedatabase.app/")
        prefs = Prefs(this)
        setContentView(binding.root)
        setRecycler()
        traerPosts()
        setListeners()
    }

    private fun setListeners() {
        TODO("Not yet implemented")
    }

    private fun traerPosts() {
        TODO("Not yet implemented")
    }

    private fun setRecycler() {
        TODO("Not yet implemented")
    }
}