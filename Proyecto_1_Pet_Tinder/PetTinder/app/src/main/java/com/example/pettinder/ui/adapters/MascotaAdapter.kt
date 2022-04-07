package com.example.pettinder.ui.adapters

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pettinder.R
import com.example.pettinder.models.Mascota


class MascotaAdapter
    (
    val data: ArrayList<Mascota>,
    val listener: MascotaListEventListener,
    val contex: Context
    ) : RecyclerView.Adapter<MascotaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MascotaViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.mascota_item_layout, parent, false)
        return MascotaViewHolder(view)
    }

    override fun onBindViewHolder(holder: MascotaViewHolder, position: Int) {
        val mascota = data[position]

        val key_drawable = mascota.key_drawable
        val imageResource: Int = contex.getResources().getIdentifier(key_drawable, "drawable", contex.packageName)
        val dra: Drawable? = contex.getDrawable(imageResource)
        holder.imgMascota.setImageDrawable(dra)

        holder.lblNombre.text = mascota.nombre
        holder.lblEdad.text = mascota.edad
        holder.btnMeGusta.setOnClickListener{(listener.onMeGustaClick(mascota))}
        holder.btnNoMeGusta.setOnClickListener{(listener.onNoMeGustaClick(mascota))}

    }

    override fun getItemCount(): Int {
        return data.size
    }

    private fun searchMascota(mascota: Mascota): Int {
        var pos = 0
        for (item in data) {
            if (item.id == mascota.id) {
                return pos
            }
            pos++
        }
        return -1
    }

    fun deletePersona(mascota: Mascota) {
        val position = searchMascota(mascota)
        data.removeAt(position)
        notifyItemRemoved(position)
    }


}

class MascotaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    val imgMascota: ImageView
    val lblNombre: TextView
    val lblEdad: TextView
    val btnMeGusta: Button
    val btnNoMeGusta: Button

    init {
        imgMascota = itemView.findViewById(R.id.imgMascota)
        lblNombre = itemView.findViewById(R.id.lblNombre)
        lblEdad = itemView.findViewById(R.id.lblEdad)
        btnMeGusta = itemView.findViewById(R.id.btnMeGusta)
        btnNoMeGusta = itemView.findViewById(R.id.btnNoMeGusta)
    }
}

interface MascotaListEventListener {
    fun onMeGustaClick(mascota: Mascota)
    fun onNoMeGustaClick(mascota: Mascota)
}