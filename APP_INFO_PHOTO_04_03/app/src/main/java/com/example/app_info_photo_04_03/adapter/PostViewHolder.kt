package com.example.app_info_photo_04_03.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.app_info_photo_04_03.databinding.ActivityForoPublicacionesBinding
import com.example.app_info_photo_04_03.model.Publicacion
import java.text.SimpleDateFormat
import java.util.Date

class PostViewHolder(v: View): RecyclerView.ViewHolder(v) {
    private val binding = ActivityForoPublicacionesBinding.bind(v)

    fun render(posts: Publicacion, onItemView: (Any?) -> Unit) {
        binding.tvEmail.text = posts.autor
        binding.tvContent.text = posts.contenido
        binding.tvFecha.text = convertirFecha(posts.fecha!!)
        itemView.setOnClickListener {
            onItemView(posts.autor)
        }
    }

    private fun convertirFecha(fecha: Long): String {
        val date = Date(fecha)
        val format = SimpleDateFormat("dd/MM/yyyy h:m:ss")
        return format.format(date)
    }

}
