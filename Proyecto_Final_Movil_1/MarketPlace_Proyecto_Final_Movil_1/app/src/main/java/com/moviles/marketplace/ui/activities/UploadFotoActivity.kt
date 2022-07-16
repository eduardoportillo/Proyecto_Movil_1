package com.moviles.marketplace.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.moviles.marketplace.BottomNavigationActivity
import com.moviles.marketplace.R
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.ActivityUploadFotoBinding
import com.moviles.marketplace.models.Photo
import com.moviles.marketplace.ui.fragments.productuser.ProductUserFragment


class UploadFotoActivity : AppCompatActivity(), ProductRepository.UploadImageListener {

    private var iterableUris: Iterator<Uri>? = null
    private lateinit var binding: ActivityUploadFotoBinding

    private var idProduct: Long = 15
    var currentUri: Uri? = null

    private var selectImageIntent =
        registerForActivityResult(ActivityResultContracts.GetMultipleContents()) { uris ->
            uris?.let {
                iterableUris = it.iterator()
                doNextImage()
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUploadFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            idProduct = it.getLong("idProduct")
        }

        binding.uploadImageView.setOnClickListener {
            selectImageInAlbum()
        }

        binding.btnSetPhotos.setOnClickListener {
            val intent = Intent(this, BottomNavigationActivity::class.java)
            startActivity(intent)
        }
    }

    fun selectImageInAlbum() {
        selectImageIntent.launch("image/*")
    }

    private fun sendImage() {
        if (currentUri == null) {
            return
        }
        ProductRepository().uploadImage(idProduct, currentUri!!, this, this)
    }

    override fun uploadImagehReady(photo: Photo) {
        doNextImage()
    }

    private fun doNextImage() {
        if (iterableUris?.hasNext()!!) {
            currentUri = iterableUris?.next()
        } else {
            currentUri = null
        }
        sendImage()
    }

    override fun onUploadImagehError(t: Throwable) {
        doNextImage()
    }
}