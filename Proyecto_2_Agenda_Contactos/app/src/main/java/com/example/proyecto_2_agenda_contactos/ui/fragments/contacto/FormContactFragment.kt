package com.example.proyecto_2_agenda_contactos.ui.fragments.contacto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import com.example.proyecto_2_agenda_contactos.R
import com.example.proyecto_2_agenda_contactos.datasingleton.ContactoItem

class FormContactFragment : Fragment() {
    lateinit var inputName: EditText
    lateinit var inputApellido: EditText
    lateinit var inputCiudad: EditText
    lateinit var inputEdad: EditText
    lateinit var inputEmail: EditText
    lateinit var inputDireccion: EditText
    lateinit var inputTelefono: EditText
    lateinit var btnGuardar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_form_contact, container, false)
        inputName = view.findViewById(R.id.inputName)
        inputApellido = view.findViewById(R.id.inputApellido)
        inputCiudad = view.findViewById(R.id.inputCiudad)
        inputEdad = view.findViewById(R.id.inputEdad)
        inputEmail = view.findViewById(R.id.inputEmail)
        inputDireccion = view.findViewById(R.id.inputDireccion)
        inputTelefono = view.findViewById(R.id.inputTelefono)
        btnGuardar = view.findViewById(R.id.btnGuardar)

        btnGuardar.setOnClickListener {
            ContactoItem.createContact(
                inputName.text.toString(),
                inputApellido.text.toString(),
                "\\",
                inputCiudad.text.toString(),
                inputEdad.text.toString().toInt(),
                inputEmail.text.toString(),
                inputDireccion.text.toString(),
                inputTelefono.text.toString().toInt()
            ) //TODO canviar url
            findNavController().navigate(R.id.contactoFragment)
        }

        return view
    }

}