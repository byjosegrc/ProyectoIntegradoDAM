package com.example.app_info_photo_04_03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app_info_photo_04_03.databinding.PerfilusuarioBinding
import com.example.app_info_photo_04_03.model.Perfil
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.squareup.picasso.Picasso
class PerfilUsuario : AppCompatActivity() {


    lateinit var binding: PerfilusuarioBinding
    lateinit var db: FirebaseDatabase
    lateinit var storage: FirebaseStorage


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = PerfilusuarioBinding.inflate(layoutInflater)
        //URL DE LA BASE DE DATOS EN STORAGE:

        storage = FirebaseStorage.getInstance("gs://infophoto-2023.appspot.com")
        //URL DE LA BASE DE DATOS EN REAL TIME DATABASE

        db = FirebaseDatabase.getInstance("https://infophoto-2023-default-rtdb.europe-west1.firebasedatabase.app/")

        setContentView(binding.root)
        setListeners()
        obtenerDatos()
    }

    private fun setListeners() {
        binding.btnModificar.setOnClickListener {
            startActivity(Intent(this, ModificarPerfil::class.java))
        }
    }

    private fun obtenerDatos() {
        db.getReference("perfiles").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val autor = snapshot.child(intent.getStringExtra("email").toString().replace(".", "-")).getValue(Perfil::class.java)
                if (autor != null) {
                    binding.tvUserName.text = autor.nombre
                    binding.tvEmail.text = autor.email
                    binding.tvCity.text = autor.ciudad
                    recogerImagen(autor.email)
                }
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }

    private fun recogerImagen(email: String?) {
        val ref = storage.reference
        val imagen = ref.child("$email/perfil.jpg")
        imagen.metadata.addOnSuccessListener {
            //Existe el archivo
            imagen.downloadUrl.addOnSuccessListener {
                Picasso.get().load(it).into(binding.ivAvatar)
            }

        } .addOnFailureListener {
            //No existe el archivo, sacamos el por defecto
            val default = storage.getReference("default/perfil.png")
            default.downloadUrl.addOnSuccessListener {
                Picasso.get().load(it).into(binding.ivAvatar)
            }
        }
    }
}
