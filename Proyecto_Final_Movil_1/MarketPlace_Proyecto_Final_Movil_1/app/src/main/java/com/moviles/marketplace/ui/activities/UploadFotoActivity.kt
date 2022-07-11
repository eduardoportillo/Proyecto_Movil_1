package com.moviles.marketplace.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.moviles.marketplace.R
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.ActivityBottomNavigationBinding
import com.moviles.marketplace.databinding.ActivityUploadFotoBinding
import okhttp3.RequestBody
import java.io.File

class UploadFotoActivity : AppCompatActivity(), ProductRepository.UploadImageListener {

    private lateinit var binding: ActivityUploadFotoBinding

    private val fileResult = 1

    private var idProduct: Long = 25

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_foto)

        binding = ActivityUploadFotoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            idProduct = it.getLong("idProduct")
        }

        binding.uploadImageView.setOnClickListener {
            fileManager();
        }

        binding.btnSetPhotos.setOnClickListener {
            val intent = Intent(this, ActivityBottomNavigationBinding::class.java)
            startActivity(intent)
        }
    }

    private fun fileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        intent.type = "image/*"
        startActivityForResult(intent, fileResult)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == fileResult) {
            if (resultCode == RESULT_OK && data != null) {

                val clipData = data.clipData

                if (clipData != null){
                    for (i in 0 until clipData.itemCount) {
                        val uri = clipData.getItemAt(i).uri
                        uri?.let { fileUpload(it) }
                    }
                }else {
                    val uri = data.data

                    uri?.let { fileUpload(it) }
                }
            }
        }
    }

    private fun fileUpload(mUri: Uri) {
        val path = mUri.toString()
        val file = File(path)
        ProductRepository().uploadImage(idProduct, file, this)
    }

    override fun uploadImagehReady(resBody: RequestBody) {
        TODO("Not yet implemented")
    }

    override fun onUploadImagehError(t: Throwable) {
        TODO("Not yet implemented")
    }


}