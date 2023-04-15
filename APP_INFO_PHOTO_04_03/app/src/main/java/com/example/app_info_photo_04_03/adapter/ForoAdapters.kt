package com.example.app_info_photo_04_03.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.app_info_photo_04_03.R
import com.example.app_info_photo_04_03.model.Publicacion

class ForoAdapters(var lista: ArrayList<Publicacion> = ArrayList<Publicacion>(), var onItemView: (Any?) -> Unit): RecyclerView.Adapter<PostViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.activity_foro_publicaciones, parent, false)
        return PostViewHolder(inflater)
    }

    override fun getItemCount(): Int {
        return lista.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.render(lista[position], onItemView)
    }
}