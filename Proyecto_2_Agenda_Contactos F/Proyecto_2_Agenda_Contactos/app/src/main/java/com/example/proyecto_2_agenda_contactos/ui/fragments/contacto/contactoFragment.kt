package com.example.proyecto_2_agenda_contactos.ui.fragments.contacto

import android.app.Notification
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.findFragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.proyecto_2_agenda_contactos.R
import com.example.proyecto_2_agenda_contactos.datasingleton.ContactoItem
import com.example.proyecto_2_agenda_contactos.models.Contacto
import com.example.proyecto_2_agenda_contactos_intento.ui.activities.fragments.contacto.MycontactoRecyclerViewAdapter

/**
 * A fragment representing a list of Items.
 */
class contactoFragment : Fragment(), MycontactoRecyclerViewAdapter.ContactoSendEventListener {

    private var columnCount = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_contactos, container, false)

        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = MycontactoRecyclerViewAdapter(ContactoItem.listContacto, this@contactoFragment)
            }
        }

        return view
    }

    override fun navigationSend(contacto: Contacto) {
        val action = contactoFragmentDirections.actionContactoFragmentToInfoContactoFragment(contacto)
        findNavController().navigate(action)
    }

    companion object {

        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            contactoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
    }
}