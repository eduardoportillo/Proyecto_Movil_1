package com.moviles.marketplace.ui.activities.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.R
import com.moviles.marketplace.models.Menssage

class MsgA(val data: ArrayList<Menssage>) :
    RecyclerView.Adapter<MsgA.MsgViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MsgViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_msg_chat, parent, false)
        return MsgViewHolder(view)
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onBindViewHolder(holder: MsgViewHolder, position: Int) {
        val msg = data[position]
        if (msg.user_id == sharedPref.getUserId().toLong()) {
            holder.leftLayout.visibility = View.VISIBLE
            holder.rightLayout.visibility = View.GONE
            holder.leftMsg.text = msg.message
        } else {
            holder.rightLayout.visibility = View.VISIBLE
            holder.leftLayout.visibility = View.GONE
            holder.rightMsg.text =  msg.message
        }
    }

    class MsgViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var leftLayout: LinearLayout = itemView.findViewById(R.id.left_layout)
        var leftMsg: TextView = itemView.findViewById(R.id.left_msg)

        var rightLayout: LinearLayout = itemView.findViewById(R.id.right_layout)
        var rightMsg: TextView = itemView.findViewById(R.id.right_msg)
    }
}