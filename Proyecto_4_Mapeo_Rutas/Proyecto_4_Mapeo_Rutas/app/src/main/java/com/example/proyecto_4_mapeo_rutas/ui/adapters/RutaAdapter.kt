package com.example.proyecto_4_mapeo_rutas.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.proyecto_4_mapeo_rutas.R
import com.example.proyecto_4_mapeo_rutas.models.Ruta
//import android.content.Intent

class RutaAdapter(val data: ArrayList<Ruta>, val listener: RutaListEventListener) : RecyclerView.Adapter<RutaAdapter.RutaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RutaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.ruta_item_layout, parent, false)
        return RutaViewHolder(view)
    }

    override fun onBindViewHolder(holder: RutaViewHolder, position: Int) {
        val ruta = data[position]
        holder.lblTitle.text = "id: "+ruta.id + " Name: " + ruta.nombre + " User: " + ruta.user_id

        holder.btnDelete.setOnClickListener {listener.onDeleteClick(ruta)}

        holder.btnEdit.setOnClickListener {listener.onEditClick(ruta)}

        holder.btnVer.setOnClickListener {listener.onVerPuntosClick(ruta.id as Long)}

    }

    override fun getItemCount(): Int {
        return data.size
    }

    class RutaViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val lblTitle: TextView = view.findViewById(R.id.lblName)
        val btnVer: Button = view.findViewById(R.id.btnVer)
        var btnEdit: Button = itemView.findViewById(R.id.btnEdit)
        var btnDelete: Button = itemView.findViewById(R.id.btnDelete)
    }

    interface RutaListEventListener {
        fun onEditClick(ruta: Ruta)
        fun onDeleteClick(ruta: Ruta)
        fun onVerPuntosClick(idRuta: Long)
    }
}