package com.example.eva01

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class InicioSesionActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.iniciarsesion)

        // Inicializar Firebase Auth
        auth = FirebaseAuth.getInstance()

        val usernameInput = findViewById<EditText>(R.id.usernameInput)
        val passwordInput = findViewById<EditText>(R.id.passwordInput)
        val loginButton = findViewById<Button>(R.id.loginButton)
        val registroTextView = findViewById<TextView>(R.id.registroTextView)

        loginButton.setOnClickListener {
            val username = usernameInput.text.toString().trim()
            val password = passwordInput.text.toString().trim()

            // Validación de campos vacíos
            if (username.isEmpty()) {
                usernameInput.error = "Por favor, ingrese su usuario"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                passwordInput.error = "Por favor, ingrese su contraseña"
                return@setOnClickListener
            }

            // Iniciar sesión con Firebase Authentication
            loginUser(username, password)
        }

        // Redirigir a RegistrarActivity cuando se haga clic en el TextView
        registroTextView.setOnClickListener {
            val intent = Intent(this, RegistrarActivity::class.java)
            startActivity(intent)
        }
    }

    private fun loginUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Inicio de sesión exitoso
                    val user = auth.currentUser
                    Toast.makeText(this, "¡Bienvenido ${user?.email}!", Toast.LENGTH_SHORT).show()
                    val intent = Intent(this, MenuPrincipalActivity::class.java)
                    startActivity(intent)
                    finish() // Cierra la actividad actual
                } else {
                    // Fallo en el inicio de sesión
                    Toast.makeText(this, "Error: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
                }
            }
    }
}
