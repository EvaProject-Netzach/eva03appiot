package com.example.eva01

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.eva01.databinding.CrearRacionBinding
import com.example.eva01.Models.Racion
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class CrearRacionActivity : AppCompatActivity() {

    private lateinit var binding: CrearRacionBinding
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = CrearRacionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Raciones")

        binding.saveRacionButton.setOnClickListener {
            if (validarCampos()) {
                guardarRacionesEnFirebase()
            }
        }

        // Configurar acción del botón "Ver Raciones"
        binding.viewRacionButton.setOnClickListener {
            val intent = Intent(this, VerRacionesActivity::class.java)
            startActivity(intent)
        }
    }

    private fun validarCampos(): Boolean {
        var isValid = true
        if (binding.racionNameInput.text.isNullOrEmpty()) {
            binding.racionNameInput.error = "Este campo es obligatorio"
            isValid = false
        }
        if (binding.racionTimeInput.text.isNullOrEmpty()) {
            binding.racionTimeInput.error = "Este campo es obligatorio"
            isValid = false
        }
        if (binding.racionDescriptionInput.text.isNullOrEmpty()) {
            binding.racionDescriptionInput.error = "Este campo es obligatorio"
            isValid = false
        }
        return isValid
    }

    private fun guardarRacionesEnFirebase() {
        val nombre = binding.racionNameInput.text.toString()
        val tiempo = binding.racionTimeInput.text.toString()
        val descripcion = binding.racionDescriptionInput.text.toString()

        val id = database.push().key ?: return
        val racion = Racion(id, nombre, tiempo, descripcion)

        database.child(id).setValue(racion)
            .addOnSuccessListener {
                Snackbar.make(
                    binding.root,
                    "Ración guardada correctamente",
                    Snackbar.LENGTH_SHORT
                ).show()
                limpiarCampos()
            }
            .addOnFailureListener {
                Snackbar.make(binding.root, "Error al guardar ración", Snackbar.LENGTH_SHORT)
                    .show()
            }
    }

    private fun limpiarCampos() {
        binding.racionNameInput.setText("")
        binding.racionTimeInput.setText("")
        binding.racionDescriptionInput.setText("")
    }
}
