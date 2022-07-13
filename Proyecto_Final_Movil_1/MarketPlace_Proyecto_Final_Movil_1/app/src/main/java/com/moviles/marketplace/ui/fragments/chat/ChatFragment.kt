package com.moviles.marketplace.ui.fragments.chat


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.marketplace.models.Chat
import com.moviles.marketplace.api.ChatRepository
import com.moviles.marketplace.databinding.FragmentChatBinding
import com.moviles.marketplace.ui.activities.chat.ChatActivity

class ChatFragment : Fragment(), ChatAdapter.ChatListEventListener,
    ChatRepository.GetChatListener {

    private var _binding: FragmentChatBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentChatBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onResume() {
        super.onResume()
        setupFetch()
    }

    private fun setupFetch() {
        fetchChat()
    }

    private fun fetchChat() {
        ChatRepository().getChat(this)
    }

    override fun onVerChatClick(idChat: Long) {
        var chatActivity = Intent(activity, ChatActivity::class.java)
        chatActivity.putExtra("idChat", idChat)
        startActivity(chatActivity)
    }

    override fun getChatReady(chats: ArrayList<Chat>) {
        val adapter = ChatAdapter(chats, this)
        binding.recycleViewChat.layoutManager = LinearLayoutManager(this.context)
        binding.recycleViewChat.adapter = adapter
    }

    override fun onGetChatError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

}