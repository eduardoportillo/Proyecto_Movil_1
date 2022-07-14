package com.moviles.marketplace.ui.activities.chat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviles.marketplace.R
import com.moviles.marketplace.api.ChatRepository
import com.moviles.marketplace.databinding.ActivityChatBinding
import com.moviles.marketplace.models.Menssage

class ChatActivity : AppCompatActivity(), ChatRepository.GetMessageForChatListener {

    private lateinit var theAdapter: MsgA
    private lateinit var binding: ActivityChatBinding

    private var idChat: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)
        intent.extras?.let {
            idChat = it.getLong("idChat")
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        theAdapter = MsgA(arrayListOf())
        binding.recyclerViewMenssage.apply {
            layoutManager =
                LinearLayoutManager(this@ChatActivity, LinearLayoutManager.VERTICAL, false)
            adapter = theAdapter
        }
    }

    override fun onResume() {
        super.onResume()
        setupButtons()
        setupFetch()
    }

    private fun setupButtons() {}

    private fun setupFetch() {
        fetchMsg()
    }

    private fun fetchMsg() {
        ChatRepository().getMessageForChat(idChat, this)
    }

    override fun getMessageForChatReady(menssage: ArrayList<Menssage>) {
        theAdapter.setNewData(menssage)
    }

    override fun onGetMessageForChatError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }
}