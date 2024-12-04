package com.example.eva01

import android.content.SharedPreferences
import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class EditarPerfilActivity : AppCompatActivity() {

    private lateinit var editTextUsername: TextInputEditText
    private lateinit var editTextPhone: TextInputEditText
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.editor_perfil)

        editTextUsername = findViewById(R.id.editTextUsername)
        editTextPhone = findViewById(R.id.editTextPhone)

        val buttonSave: MaterialButton = findViewById(R.id.buttonSave)
        val buttonBack: MaterialButton = findViewById(R.id.buttonBack)

        // inicializacion de sharedPreferences para guardar datos
        sharedPreferences = getSharedPreferences("user_prefs", MODE_PRIVATE)

        // cargar datos existentes si hay
        loadProfileData()

        buttonSave.setOnClickListener {
            saveProfile()
        }

        buttonBack.setOnClickListener {
            finish()
        }
    }

    private fun loadProfileData() {
        val username = sharedPreferences.getString("username", "")
        val phone = sharedPreferences.getString("phone", "")

        editTextUsername.setText(username)
        editTextPhone.setText(phone)
    }

    private fun saveProfile() {
        val username = editTextUsername.text.toString().trim()
        val phone = editTextPhone.text.toString().trim()

        // validación basica
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this, "El nombre de usuario no puede estar vacío", Toast.LENGTH_SHORT).show()
            return
        }

        if (TextUtils.isEmpty(phone) || !numeroDeTelefonoValido(phone)) {
            Toast.makeText(this, "Número de teléfono no válido", Toast.LENGTH_SHORT).show()
            return
        }

        // guardar los datos en SharedPreferences
        with(sharedPreferences.edit()) {
            putString("username", username)
            putString("phone", phone)
            apply()
        }

        Toast.makeText(this, "Perfil guardado", Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun numeroDeTelefonoValido(phone: String): Boolean {
        // logica de validacion de numero telefonico chileno (ose ael +56)
        return phone.matches("^\\+56\\d{9}\$".toRegex()) // validador de numero telefonico chileno, referencia de apps de registro de numero telefonico de GH
    }
}
