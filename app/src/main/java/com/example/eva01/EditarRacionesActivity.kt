package com.example.eva01

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.eva01.Models.Racion
import com.example.eva01.databinding.EditarRacionesBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class EditarRacionesActivity : AppCompatActivity() {

    private lateinit var binding: EditarRacionesBinding
    private lateinit var database: DatabaseReference
    private lateinit var RacionId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EditarRacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        database = FirebaseDatabase.getInstance().getReference("Raciones")

        // Obtener datos del intent
        RacionId = intent.getStringExtra("id") ?: return
        binding.racionNameInput.setText(intent.getStringExtra("nombre"))
        binding.racionTimeInput.setText(intent.getStringExtra("tiempo"))
        binding.racionDescriptionInput.setText(intent.getStringExtra("descripcion"))

        binding.saveRacionButton.setOnClickListener {
            if (validarCampos()) {
                actualizarRacion()
            }
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


    private fun actualizarRacion() {
        val nombre = binding.racionNameInput.text.toString()
        val tiempo = binding.racionTimeInput.text.toString()
        val descripcion = binding.racionDescriptionInput.text.toString()

        val racionActualizada = Racion(RacionId, nombre, tiempo, descripcion)
        database.child(RacionId).setValue(racionActualizada)
            .addOnSuccessListener {
                Toast.makeText(this, "Ración actualizada correctamente", Toast.LENGTH_SHORT).show()
                finish()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(this, "Error al actualizar: ${exception.message}", Toast.LENGTH_SHORT).show()
            }
    }

}
