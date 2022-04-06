package com.example.pettinder.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.pettinder.R
import com.example.pettinder.models.Mascota

class ItemsMascotaAdapter(context: Context, resource: Int, objects: Array<out Mascota>) :
    ArrayAdapter<Mascota>(context, resource, objects) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.mascota_item_layout, parent, false)

        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val lblNombre = view.findViewById<TextView>(R.id.lblNombre)
        val lblEdad = view.findViewById<TextView>(R.id.lblEdad)

        val mascota = getItem(position)

//        imageView.setImageDrawable()
        lblNombre.text = mascota?.nombre
        lblEdad.text = mascota?.edad
        return view
    }
}