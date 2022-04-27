package com.example.proyecto_2_agenda_contactos.ui.fragments.contacto

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.proyecto_2_agenda_contactos.R
import com.example.proyecto_2_agenda_contactos.models.Contacto

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class InfoContactoFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    val  args: InfoContactoFragmentArgs by navArgs()

    lateinit var labelContactoNombre: TextView
    lateinit var labelContactoApellido: TextView
    lateinit var labelContactoCiudad: TextView
    lateinit var labelContactoEdad: TextView
    lateinit var labelContactoEmail: TextView
    lateinit var labelContactoDireccion: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_info_contacto, container, false)
        val contactoRecibido: Contacto = args.contactoEnviado
        labelContactoNombre = view.findViewById(R.id.labelContactoNombre)
        labelContactoApellido = view.findViewById(R.id.labelContactoApellido)
        labelContactoCiudad = view.findViewById(R.id.labelContactoCiudad)
        labelContactoEdad = view.findViewById(R.id.labelContactoEdad)
        labelContactoEmail = view.findViewById(R.id.labelContactoEmail)
        labelContactoDireccion = view.findViewById(R.id.labelContactoDireccion)

        setupFormContacto(contactoRecibido)
        return view
    }

    private fun setupFormContacto(contactoRecibido: Contacto) {
        labelContactoNombre.text = contactoRecibido.nombre
        labelContactoApellido.text = contactoRecibido.apellido
        labelContactoCiudad.text  = contactoRecibido.ciudad
        labelContactoEdad.text = contactoRecibido.edad.toString()
        labelContactoEmail.text = contactoRecibido.email
        labelContactoDireccion.text = contactoRecibido.direccion
    }

    companion object {
        @JvmStatic fun newInstance(param1: String, param2: String) =
                InfoContactoFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }
}