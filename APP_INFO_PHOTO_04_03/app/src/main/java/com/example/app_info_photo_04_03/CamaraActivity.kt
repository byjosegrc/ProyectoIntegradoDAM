package com.example.app_info_photo_04_03

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.app_info_photo_04_03.databinding.ActivityCamaraBinding

class CamaraActivity : AppCompatActivity() {


    val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        when(it.resultCode){
            RESULT_OK->{
                binding.ivFoto.setImageBitmap(it.data?.extras?.get("data") as Bitmap)
            }
        }
    }


    private lateinit var binding: ActivityCamaraBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCamaraBinding.inflate(layoutInflater)
        setContentView(binding.root)
        title = "HACER FOTO"
        setListeners()
    }
    private fun setListeners() {
        binding.btnFoto.setOnClickListener {
            checkPermisos()
        }
    }

    private fun checkPermisos() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!= PackageManager.PERMISSION_GRANTED){
            //permiso no esta aceptado por el momento o bien por que es la primera vez o por que no estan dados
            perdirPermisosCamara()
        }
        else{
            openCamera()
        }
    }

    private fun openCamera() {
        // Toast.makeText(this, "Camara abierta", Toast.LENGTH_SHORT).show()
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        responseLauncher.launch(cameraIntent)

    }

    private fun perdirPermisosCamara() {
        //comprobamos si los ha rechazado
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)){
            //el usuario ya ha rechazodo los permisos podemos abrirle los ajustes o mostrarle
            //un texto para indicarle
            Toast.makeText(this, "Para utulizar esta App vaya a ajustes y dele permisos", Toast.LENGTH_SHORT).show()
        }else{
            //pedimos los permisos
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 100)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == 100){
            if(grantResults.isNotEmpty() && grantResults[0]== PackageManager.PERMISSION_GRANTED){
                openCamera()
            }else{
                Toast.makeText(this, "Permisos rechazados!!!", Toast.LENGTH_SHORT).show()

            }
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu1,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.inicio->{
                startActivity(Intent(this, HomeActivity::class.java))
                true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}