package com.example.app_info_photo_04_03

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.app_info_photo_04_03.databinding.ActivityModificarPerfilBinding
import com.example.app_info_photo_04_03.model.Perfil
import com.example.app_info_photo_04_03.pref.Prefs
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.storage.FirebaseStorage

class ModificarPerfil : AppCompatActivity() {


    private val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.ivPerfil.setImageURI(uri)
            guardarImagen(uri)
        }
    }

    private fun guardarImagen(uri: Uri) {
        val ref = storage.reference
        val imagen = ref.child("${prefs.getEmail()}/perfil.jpg")
        val upload = imagen.putFile(uri).addOnSuccessListener {
            //Se ha subido la imagen
            Toast.makeText(this, "Se ha subido la imagen", Toast.LENGTH_SHORT).show()
        } .addOnFailureListener {
            //No se ha subido la imagen
            Toast.makeText(this, it.message.toString(), Toast.LENGTH_LONG).show()
        }
    }


    lateinit var storage: FirebaseStorage
    lateinit var binding: ActivityModificarPerfilBinding
    lateinit var prefs: Prefs
    lateinit var db: FirebaseDatabase
    var nombre = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModificarPerfilBinding.inflate(layoutInflater)
        prefs = Prefs(this)
      //  binding.tvCorreo.text = prefs.getEmail()

        //URL DE LA BASE DE DATOS EN STORAGE:

        storage = FirebaseStorage.getInstance("gs://infophoto-2023.appspot.com")
        //URL DE LA BASE DE DATOS EN REAL TIME DATABASE

        db = FirebaseDatabase.getInstance("https://infophoto-2023-default-rtdb.europe-west1.firebasedatabase.app/")

        setContentView(binding.root)
        setListeners()
    }

    private fun setListeners() {
        binding.btnCancelar.setOnClickListener {
            finish()
        }
        binding.btnEditarPerfil.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding.btnGuardar.setOnClickListener {
            guardarAutor()
        }
    }

    private fun guardarAutor() {
        nombre = binding.etNombre2.text.toString().trim()
        if (nombre.isEmpty()) {
            binding.etNombre2.error = "Este campo no puede estar vacio"
            binding.etNombre2.requestFocus()
            return
        }
        val ciudad = binding.spnCiudad.selectedItem.toString()
        val autor = Perfil(prefs.getEmail(), nombre, ciudad)
        db.getReference("perfiles").child(prefs.getEmail().toString().replace(".", "-")).setValue(autor).addOnSuccessListener {
            finish()
        }

    }
}