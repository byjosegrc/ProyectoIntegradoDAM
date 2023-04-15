package com.example.app_info_photo_04_03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.gms.maps.GoogleMap
import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.maps.CameraUpdate
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap.OnMyLocationButtonClickListener
import com.google.android.gms.maps.GoogleMap.OnMyLocationClickListener
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.Dash
import com.google.android.gms.maps.model.Dot
import com.google.android.gms.maps.model.Gap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.gms.maps.model.PolylineOptions
class Granada  : AppCompatActivity(), OnMapReadyCallback, OnMyLocationButtonClickListener, OnMyLocationClickListener{
    lateinit var maps: GoogleMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rutas_google)
        crearFragment()
    }

    private fun crearFragment() {
        var mapFragment = supportFragmentManager.findFragmentById(R.id.fmaps) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(p0: GoogleMap) {
        maps = p0
        maps.setOnMyLocationButtonClickListener(this)
        maps.setOnMyLocationClickListener(this)
        maps.mapType = GoogleMap.MAP_TYPE_SATELLITE
        val settings = maps.uiSettings
        settings.isZoomControlsEnabled = true
        createMarker()
        crearAnimacion()
        crearPolyLines()
    }
    //---------------------------------------------------------------------------------------------
    private fun crearPolyLines() {
        val polyLineOptions = PolylineOptions()
            .add(LatLng(36.85038934965935, -2.4649787308318207))
            .add(LatLng(36.85079869957478, -2.4609574938416086))
            .add(LatLng(36.853271201278304, -2.4459156655664906))
            .add(LatLng(36.843466575207344, -2.46394011051201))
            .add(LatLng(36.849047295915035, -2.461772885480761))
            .add(LatLng(36.852003608355716, -2.4692466955260852))
            .add(LatLng(36.85038934965935, -2.4649787308318207))
            .width(15f)
            .color(ContextCompat.getColor(this, R.color.black))

        val polyline = maps.addPolyline(polyLineOptions)
        val patron = listOf(
            //Dibujo del patron
            //.  _________   .   ________  .  __________
            Dot(), Gap(10f), Dash(50f), Gap(10f)
        )
        polyline.pattern = patron
    }


    //---------------------------------------------------------------------------------------------
    private fun crearAnimacion() {
        //Añadimos una pequeña animacion
        val coordenadas = LatLng(36.850525016931655, -2.465031842530974)
        maps.animateCamera(
            CameraUpdateFactory.newLatLngZoom(coordenadas, 18f), 4500, null
        )
    }

    private fun createMarker() {
        val coordenadas = LatLng(36.850525016931655, -2.465031842530974)
        val mIsnti = MarkerOptions().position(coordenadas).title("IES Al-Ándalus")
        maps.addMarker(mIsnti)

    }

    /*
     * Metodo para comprobar que aun tengo los permisos mientras tengo la app corriendo
     * para evitar crasheos
     */
    override fun onResumeFragments() {
        super.onResumeFragments()
        if (!::maps.isInitialized) return
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
            || ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            maps.isMyLocationEnabled = false
        }
    }

    override fun onMyLocationButtonClick(): Boolean {
        Toast.makeText(this, "Has pulsado la localizacion, serás desintegrado", Toast.LENGTH_LONG).show()
        //Si devuelves un true, el boton localizar ya no funciona, para que siga con la operativa devolvemos false
        return false
    }

    override fun onMyLocationClick(p0: Location) {
        Toast.makeText(this, "Coordenadas: Latitud: ${p0.latitude}, Longitud: ${p0.longitude}", Toast.LENGTH_LONG).show()
    }
}