package com.moviles.marketplace.ui.fragments.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.marketplace.models.Chat
import com.moviles.marketplace.R
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.ui.fragments.marketplace.MarketPlaceAdapter

class ChatAdapter(val data: ArrayList<Chat>, val listener: ChatListEventListener) :
    RecyclerView.Adapter<ChatAdapter.ChatViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list_chat, parent, false)
        return ChatViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChatViewHolder, position: Int) {
        val chat = data[position]

        holder.idChatLabel.text = chat.id.toString()
        holder.nombreVendedorLabel.text = chat.seller?.name.toString()
        holder.compradorChatLabel.text = chat.buyer?.name.toString()
        holder.productoChatLabel.text = chat.product?.title.toString()

        holder.constraintChat.setOnClickListener {
            listener.onVerChatClick(chat.id!!)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ChatViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val idChatLabel: TextView = itemView.findViewById(R.id.idChatLabel)
        var nombreVendedorLabel: TextView = itemView.findViewById(R.id.nombreVendedorLabel)
        var compradorChatLabel: TextView = itemView.findViewById(R.id.compradorChatLabel)
        var productoChatLabel: TextView = itemView.findViewById(R.id.productoChatLabel)
        var constraintChat: ConstraintLayout= itemView.findViewById(R.id.constraintChat)
    }

    interface ChatListEventListener {
        fun onVerChatClick(idChat: Long)
    }
}