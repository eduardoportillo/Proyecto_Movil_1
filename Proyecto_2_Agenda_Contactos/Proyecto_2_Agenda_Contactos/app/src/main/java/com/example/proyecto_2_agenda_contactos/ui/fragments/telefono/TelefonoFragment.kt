package com.example.proyecto_2_agenda_contactos.ui.fragments.telefono

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.key
import androidx.fragment.app.FragmentResultListener
import androidx.fragment.app.setFragmentResultListener
import com.example.proyecto_2_agenda_contactos.R
import com.example.proyecto_2_agenda_contactos.models.Contacto
import com.example.proyecto_2_agenda_contactos.ui.fragments.telefono.placeholder.PlaceholderContent

class TelefonoFragment : Fragment() {

    private var columnCount = 1
//    lateinit var contacto: Contacto

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setFragmentResultListener("dataInfoContactoFragment"){key, bundle-> val contacto =
            bundle.getSerializable("contactoEnviado") as Contacto
        } // TODO intentando recibir dato de otro fragment
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_telefono_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MyTelefonoRecyclerViewAdapter(PlaceholderContent.ITEMS)
            }
        }
        return view
    }

    companion object {

        // TODO: Customize parameter argument names
        const val ARG_COLUMN_COUNT = "column-count"

        // TODO: Customize parameter initialization
        @JvmStatic
        fun newInstance(columnCount: Int) =
            TelefonoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
    interface ContactoSendEventListener{
        fun contactoSend(contacto: Contacto)
    }
}