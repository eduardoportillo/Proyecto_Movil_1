package com.moviles.marketplace.ui.fragments.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.marketplace.R
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.ui.fragments.marketplace.MarketPlaceAdapter

class ChatAdapter(val data: ArrayList<Product>, val listener: ChatListEventListener) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = data[position]

        holder.idChatLabel.text = chat.id.toString()
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idChatLabel: TextView = itemView.findViewById(R.id.idChatLabel)
        var nombreVendedorLabel: TextView = itemView.findViewById(R.id.nombreVendedorLabel)
        var compradorChatLabel: TextView = itemView.findViewById(R.id.compradorChatLabel)
        var productoChatLabel: TextView = itemView.findViewById(R.id.productoChatLabel)
    }

    interface ChatListEventListener {
        fun onVerChatClick(idProduct: Long)
    }
}