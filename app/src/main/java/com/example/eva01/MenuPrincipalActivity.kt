package com.example.eva01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MenuPrincipalActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.menuprincipal)

        val buttonBorrarTiempos = findViewById<Button>(R.id.buttonTemporizer)
        val buttonTablas = findViewById<Button>(R.id.buttonStats)
        val buttonPerfil = findViewById<Button>(R.id.buttonProfile)
        val buttonLogout = findViewById<Button>(R.id.buttonCerrarSesion)

        // Navegar a RacionActivityMain
        buttonBorrarTiempos.setOnClickListener {
            val intent = Intent(this, RacionActivityMain::class.java)
            startActivity(intent)
        }

        // Navegar a VerRacionesActivity
        buttonTablas.setOnClickListener {
            val intent = Intent(this, VerRacionesActivity::class.java)
            startActivity(intent)
        }

        // Navegar a PerfilActivity
        buttonPerfil.setOnClickListener {
            val intent = Intent(this, PerfilActivity::class.java)
            startActivity(intent)
        }

        // Cerrar sesión y volver a InicioSesionActivity
        buttonLogout.setOnClickListener {
            val intent = Intent(this, InicioSesionActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }
}
