package com.example.proyecto_2_agenda_contactos.ui.fragments.contacto

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.proyecto_2_agenda_contactos.R
import com.example.proyecto_2_agenda_contactos.datasingleton.ContactoItem
import com.example.proyecto_2_agenda_contactos.models.Contacto

class EditFormContactoFragment : Fragment() {
    lateinit var inputNameEdit: EditText
    lateinit var inputApellidoEdit: EditText
    lateinit var inputCiudadEdit: EditText
    lateinit var inputEdadEdit: EditText
    lateinit var inputEmailEdit: EditText
    lateinit var inputDireccionEdit: EditText
    lateinit var btnGuardarEdit: Button

    val args: EditFormContactoFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_edit_form_contacto, container, false)
        val contactRecibido: Contacto = args.contactoEnviado
        inputNameEdit = view.findViewById(R.id.inputNameEdit)
        inputApellidoEdit = view.findViewById(R.id.inputApellidoEdit)
        inputCiudadEdit = view.findViewById(R.id.inputCiudadEdit)
        inputEdadEdit = view.findViewById(R.id.inputEdadEdit)
        inputEmailEdit = view.findViewById(R.id.inputEmailEdit)
        inputDireccionEdit = view.findViewById(R.id.inputDireccionEdit)

        btnGuardarEdit = view.findViewById(R.id.btnGuardarEdit)

        inputNameEdit.setText(contactRecibido.nombre)
        inputApellidoEdit.setText(contactRecibido.apellido)
        inputCiudadEdit.setText(contactRecibido.ciudad)
        inputEdadEdit.setText(contactRecibido.edad.toString())
        inputEmailEdit.setText(contactRecibido.email)
        inputDireccionEdit.setText(contactRecibido.direccion)

        btnGuardarEdit.setOnClickListener {

            ContactoItem.editContact(
                contactRecibido.id,
                inputNameEdit.text.toString(),
                inputApellidoEdit.text.toString(),
                inputCiudadEdit.text.toString(),
                inputEdadEdit.text.toString().toInt(),
                inputEmailEdit.text.toString(),
                inputDireccionEdit.text.toString(),
            )
            findNavController().navigate(R.id.contactoFragment)
        }

        return view
    }
}