package com.moviles.marketplace.ui.activities.chat

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import com.moviles.marketplace.api.ChatRepository
import com.moviles.marketplace.databinding.ActivityChatBinding
import com.moviles.marketplace.models.Menssage

class ChatActivity : AppCompatActivity(), ChatRepository.GetMessageForChatListener,
    ChatRepository.AddMenssageWithChatListener, ChatRepository.AddPhotoWithChatListener {

    private lateinit var theAdapter: MsgAdapter
    private lateinit var binding: ActivityChatBinding

    private var selectImageIntent = registerForActivityResult(ActivityResultContracts.GetContent())
    { uri ->
        if (uri != null) {
            sendImage(uri!!)
        }
    }

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
        theAdapter = MsgAdapter(arrayListOf())
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

    private fun setupButtons() {
        binding.sendButton.setOnClickListener {
            if (binding.chatInputText.text.isNotEmpty()) {
                val msg = Menssage(
                    chat_id = idChat,
                    message = binding.chatInputText.text.toString(),
                )
                ChatRepository().addMenssageWithChat(msg, this)
                binding.chatInputText.text.clear()
            } else {
                binding.chatInputText.error = "Escriba un mensaje"
            }
        }

        binding.btnSendImage.setOnClickListener{
            selectImageInAlbum()
        }

        binding.btnSendLocation.setOnClickListener{
            val intent = Intent(this, MapsMenssageActivity::class.java)
            intent.putExtra("idChat", idChat)
            startActivity(intent)
        }
    }

    fun selectImageInAlbum() {
        selectImageIntent.launch("image/*")
    }


    private fun sendImage(uri: Uri) {
        ChatRepository().addPhotoWithChat(idChat, uri , this, this)
    }

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

    override fun addMenssageWithChatReady(menssage: Menssage) {
        fetchMsg()
    }

    override fun oAddMenssageWithChatError(t: Throwable) {
        fetchMsg()
    }

    override fun addPhotoWithChatReady(menssage: Menssage) {
        fetchMsg()
    }

    override fun oAddPhotonWithChatError(t: Throwable) {
        fetchMsg()
    }
}