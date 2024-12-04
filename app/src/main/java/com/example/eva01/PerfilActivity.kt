package com.example.eva01

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton

class PerfilActivity : AppCompatActivity() {

    private lateinit var textViewUsername: TextView
    private lateinit var textViewPhone: TextView
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.perfil)

        // Uusario y numero de telefono

        textViewUsername = findViewById(R.id.textViewUsername)
        textViewPhone = findViewById(R.id.textViewPhone)

        val buttonEditProfile: MaterialButton = findViewById(R.id.buttonEditProfile)
        val buttonBack: MaterialButton = findViewById(R.id.buttonBack)

        // Inicializacion del SharedPreferences
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        buttonEditProfile.setOnClickListener {
            val editProfileIntent = Intent(this, EditarPerfilActivity::class.java)
            startActivity(editProfileIntent)
        }

        buttonBack.setOnClickListener {
            finish() // FINISH HIM
        }

        // carga datos de perfil e iniciacion de la actividad
        loadProfileData()
    }

    override fun onResume() {
        super.onResume()
        loadProfileData() // carga de los datos actualizados, usando de refernecia soluciones vistas en github
    }

    private fun loadProfileData() {
        val username = sharedPreferences.getString("username", "Usuario no registrado")
        val phone = sharedPreferences.getString("phone", "Numero no registrado")

        textViewUsername.text = username
        textViewPhone.text = phone
    }
}
