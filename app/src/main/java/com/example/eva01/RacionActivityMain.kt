package com.example.eva01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class RacionActivityMain : AppCompatActivity() {

    private lateinit var buttonVolver: Button
    private lateinit var createRationsButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.racion_creador)

        // inicialización de vistas
        initViews()
        // configurar listeners
        setupListeners()
    }

    private fun initViews() {
        buttonVolver = findViewById(R.id.buttonVolver)
        createRationsButton = findViewById(R.id.createRationsButton)
    }

    private fun setupListeners() {
        // listener para el botón Volver
        buttonVolver.setOnClickListener {
            // finalizar la actividad actual para volver a la anterior
            finish()
        }

        // listener para el botón Crear Raciones
        createRationsButton.setOnClickListener {
            // iniciar la actividad CrearRacionActivity
            val intent = Intent(this, CrearRacionActivity::class.java)
            startActivity(intent)
        }
    }
}
