package com.example.app_info_photo_04_03

import android.app.ActionBar
import android.app.Notification.Action
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.example.app_info_photo_04_03.databinding.LoginMainBinding
import com.example.app_info_photo_04_03.pref.Prefs
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider



class LoginActivity : AppCompatActivity() {
    lateinit var binding: LoginMainBinding
    lateinit var prefs: Prefs
    private var responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        if (it.resultCode == RESULT_OK){
            //recogemos los datos de la activity de gogle
            val task = GoogleSignIn.getSignedInAccountFromIntent(it.data)
            try {
                val  cuenta = task.getResult(ApiException::class.java)//aqui tengo la cuenta me valido con ella
                if (cuenta!=null){
                    //cogemos las credenciales
                    val credenciales= GoogleAuthProvider.getCredential(cuenta.idToken,null)
                    FirebaseAuth.getInstance().signInWithCredential(credenciales).addOnCompleteListener {
                        if (it.isSuccessful){
                            prefs.setEmail(cuenta.email?:"")
                            irHome()
                        }else{
                            Toast.makeText(this,"fallo",Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }catch (e: ApiException){
                println(e.message)
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= LoginMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        //TITULO
        title="AUTENTICACIÓN"

        //PONER EN EL CENTRO

       // supportActionBar?.setDisplayOptions((androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM))
        //supportActionBar?.setCustomView(R.layout.login_main)

        prefs= Prefs(this)
        comprobarSesion()
        setUp()
    }

    private fun setUp() {

        binding.btnGoogle.setOnClickListener{
            login()
        }
    }
    private fun login() {
        val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            //lo encontramos en el json de la aplicación
            .requestIdToken("425372330535-pbeh3ackctikjq4nm4msgq2pc3pgjtjl.apps.googleusercontent.com")
            .requestEmail()
            .build()
        val googleClient = GoogleSignIn.getClient(this, googleConf)

        //para que si cierro sesion me de a elegir un usuario y no me valide con el ultimo
        googleClient.signOut()

        //responseLauncher
        responseLauncher.launch(googleClient.signInIntent)
    }


    private fun irHome(){
        startActivity(Intent(this, HomeActivity::class.java))
    }

    private fun comprobarSesion() {
        val email= prefs.getEmail()
        if (email!=null){
            irHome()
        }
    }



    /* private fun login() {
         //Configuracion de la autenticacion de google:

         val googleConf = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
             .requestIdToken(getString(R.string.default_web_client_id))
             .requestEmail()
             .build()
         //----------------

         val googleCliente: GoogleSignInClient = GoogleSignIn.getClient(this, googleConf)
         startActivityForResult(googleCliente.signInIntent,GOOGLE_SIGN_IN)

     //DEFAULT_SIGN_IN ya que por defecto voy a logear los usarios por google
     }

     override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
         super.onActivityResult(requestCode, resultCode, data)
         if(requestCode ==GOOGLE_SIGN_IN){
         val task  = GoogleSignIn.getSignedInAccountFromIntent(data)
             try {
                 val account = task.getResult(ApiException::class.java)

                 if (account != null) {
                     val credencial = GoogleAuthProvider.getCredential(account.idToken, null)
                     FirebaseAuth.getInstance().signInWithCredential(credencial)
                 }
             }catch (e: ApiException){
                 showLockTaskEscapeMessage()
             }
         }
     }*/


}