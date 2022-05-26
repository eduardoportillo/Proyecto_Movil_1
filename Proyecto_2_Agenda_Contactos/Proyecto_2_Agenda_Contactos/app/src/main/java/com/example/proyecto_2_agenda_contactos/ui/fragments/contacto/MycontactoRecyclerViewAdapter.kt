package com.example.proyecto_2_agenda_contactos_intento.ui.activities.fragments.contacto

import android.content.Context
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.proyecto_2_agenda_contactos.models.Contacto
import com.example.proyecto_2_agenda_contactos.databinding.FragmentContactoItemBinding
import com.example.proyecto_2_agenda_contactos.ui.fragments.contacto.InfoContactoFragment
import com.example.proyecto_2_agenda_contactos.ui.fragments.contacto.contactoFragment
import com.example.proyecto_2_agenda_contactos.ui.fragments.contacto.contactoFragmentDirections

class MycontactoRecyclerViewAdapter(
    val values: ArrayList<Contacto>,
    val listener: ContactoSendEventListener
) : RecyclerView.Adapter<MycontactoRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            FragmentContactoItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val contacto = values[position]
        //holder.imgContacto = TODO implementar libreria para imagenes
        holder.nombreContacto.text = contacto.nombre
        holder.apellidoContacto.text = contacto.apellido
        holder.numeroContacto.text = contacto.telefonos.get(0).numero.toString()
        holder.btnVer.setOnClickListener{listener.navigationSend(contacto)}
    }


    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: FragmentContactoItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val imgContacto: ImageView = binding.imgContacto
        val nombreContacto: TextView = binding.nombreContacto
        val apellidoContacto: TextView = binding.apellidoContacto
        val numeroContacto: TextView = binding.numeroContacto
        val btnVer: Button = binding.btnVer

        override fun toString(): String {
            return super.toString() + "Nombre: " + nombreContacto.text + "Apellido: " + apellidoContacto + "Numero: " + numeroContacto
        }
    }
    interface ContactoSendEventListener{
        fun navigationSend(contacto: Contacto)
    }

}