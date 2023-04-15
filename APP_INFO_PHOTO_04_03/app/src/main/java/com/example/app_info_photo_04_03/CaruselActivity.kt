package com.example.app_info_photo_04_03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class CaruselActivity : AppCompatActivity() {

    //creo el arraylistof para almacenar las imagenes del carusel
    private val list = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_carusel)
        title="CARUSEL FOTOS"
        //busco el compone del carusel del layout
        val carousel: ImageCarousel = findViewById(R.id.carousel)

        //añado las imagenes al arraylistOf
        list.add(CarouselItem(R.drawable.uno))
        list.add(CarouselItem(R.drawable.dos))
        list.add(CarouselItem(R.drawable.tres))
        list.add(CarouselItem(R.drawable.cuatro))
        //le pasamos al array la lista de valores en este caso
        // imagenes al carusel de imagenes
        carousel.addData(list)
    }
}
