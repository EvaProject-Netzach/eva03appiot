package com.example.eva01.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eva01.Models.Racion
import com.example.eva01.R

class AdapterRacion(
    private var raciones: ArrayList<Racion>,
    private val onEditClick: (Racion) -> Unit,
    private val onDeleteClick: (Racion) -> Unit
) : RecyclerView.Adapter<AdapterRacion.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val nombre: TextView = itemView.findViewById(R.id.tvNombre)
        val tiempo: TextView = itemView.findViewById(R.id.tvTiempo)
        val descripcion: TextView = itemView.findViewById(R.id.tvDescripcion)
        val btnEditar: View = itemView.findViewById(R.id.btnEditaritem)
        val btnEliminar: View = itemView.findViewById(R.id.btnEliminaritem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterRacion.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_raciones, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val racion = raciones[position]

        holder.nombre.text = racion.nombre
        holder.tiempo.text = racion.tiempo
        holder.descripcion.text = racion.descripcion

        // Botones!!
        holder.btnEditar.setOnClickListener {
            onEditClick(racion)
        }

        holder.btnEliminar.setOnClickListener {
            onDeleteClick(racion)
        }
    }

    override fun getItemCount(): Int {
        return raciones.size
    }
}