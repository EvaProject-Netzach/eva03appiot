package com.example.eva01

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.eva01.Adapter.AdapterRacion
import com.example.eva01.Models.Racion
import com.example.eva01.databinding.VerRacionesBinding
import com.google.firebase.database.FirebaseDatabase

class VerRacionesActivity : AppCompatActivity() {

    private lateinit var binding: VerRacionesBinding
    private lateinit var adapter: AdapterRacion
    private val racionesList = ArrayList<Racion>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = VerRacionesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurar RecyclerView
        binding.racionesRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = AdapterRacion(
            racionesList,
            onEditClick = { racion ->
                val intent = Intent(this, EditarRacionesActivity::class.java).apply {
                    putExtra("id", racion.id)
                    putExtra("nombre", racion.nombre)
                    putExtra("tiempo", racion.tiempo)
                    putExtra("descripcion", racion.descripcion)
                }
                startActivity(intent)
            },
            onDeleteClick = { racion ->
                eliminarRacion(racion)
            }
        )
        binding.racionesRecyclerView.adapter = adapter

        // Cargar datos de Firebase
        cargarRaciones()
    }

    private fun cargarRaciones() {
        val database = FirebaseDatabase.getInstance().getReference("Raciones")
        database.get().addOnSuccessListener { snapshot ->
            racionesList.clear()
            snapshot.children.forEach {
                val racion = it.getValue(Racion::class.java)
                racion?.let { racionesList.add(it) }
            }
            adapter.notifyDataSetChanged()
        }.addOnFailureListener {
            Toast.makeText(this, "Error al cargar datos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun eliminarRacion(racion: Racion) {
        val database = FirebaseDatabase.getInstance().getReference("Raciones")
        database.child(racion.id!!).removeValue()
            .addOnSuccessListener {
                Toast.makeText(this, "Ración eliminada", Toast.LENGTH_SHORT).show()
                racionesList.remove(racion)
                adapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Error al eliminar", Toast.LENGTH_SHORT).show()
            }
    }
}
