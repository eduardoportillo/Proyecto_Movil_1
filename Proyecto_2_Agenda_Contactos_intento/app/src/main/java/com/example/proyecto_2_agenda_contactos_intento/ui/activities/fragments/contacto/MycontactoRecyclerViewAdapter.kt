package com.example.proyecto_2_agenda_contactos_intento.ui.activities.fragments.contacto

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.practicanavegacion.ui.models.Contacto
import com.example.proyecto_2_agenda_contactos_intento.databinding.FragmentItemBinding


/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class MycontactoRecyclerViewAdapter(
    private val values: List<Contacto>
) : RecyclerView.Adapter<MycontactoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = values[position]
//        holder.idView.text = contacto.id
//        holder.contentView.text = item.content
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val idView: TextView = binding.nombreContacto
        val contentView: TextView = binding.numeroContacto
        val btnVer: Button = binding.btnVer

        override fun toString(): String {
            return super.toString() + " '" + contentView.text + "'"
        }
    }

}