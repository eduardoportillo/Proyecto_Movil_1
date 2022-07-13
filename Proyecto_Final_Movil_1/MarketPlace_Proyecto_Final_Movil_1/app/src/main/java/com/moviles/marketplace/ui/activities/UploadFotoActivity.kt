package com.moviles.marketplace.ui.activities

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.moviles.marketplace.R
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.ActivityUploadFotoBinding
import com.moviles.marketplace.models.Photo
import com.moviles.marketplace.ui.fragments.productuser.ProductUserFragment
import java.io.File


class UploadFotoActivity : AppCompatActivity(), ProductRepository.UploadImageListener {

    private lateinit var binding: ActivityUploadFotoBinding

    private val fileResult = 1

    private var idProduct: Long = 14

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
//            val intent = Intent(this, ActivityBottomNavigationBinding::class.java)
//            startActivity(intent) // TODO aca ver la forma de llevar a fragment mis productos

            var fragmentProductUser = supportFragmentManager.findFragmentById(R.id.navigation_product_user) as ProductUserFragment

            val fm: FragmentManager = supportFragmentManager
            val ft: FragmentTransaction = fm.beginTransaction()
            ft.add(fragmentProductUser.id, fragmentProductUser)
            ft.commit()
        }
    }

    private fun fileManager() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR2) {
            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
        }
        intent.type = "image/*" // TODO ver por que no camptura bien el nombre del archivo.
        startActivityForResult(intent, fileResult)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

//        if (requestCode == fileResult && resultCode == RESULT_OK) {
//            val uri = data?.data
//            uri?.let {
//                val file = File(it.path)
//                ProductRepository().uploadImage(idProduct, file, this)
//            }
//        }
//    }

        if (requestCode == fileResult) {
            if (resultCode == RESULT_OK && data != null) {

                val clipData = data.clipData

                if (clipData != null) {
                    for (i in 0 until clipData.itemCount) {
                        val uri = clipData.getItemAt(i).uri
                        uri?.let { fileUpload(it) }
                    }
                } else {
                    val uri = data.data

                    uri?.let { fileUpload(it) }
                }
            }
        }
    }

    private fun fileUpload(mUri: Uri) {
        val file = File(mUri.path)
        ProductRepository().uploadImage(idProduct, file, this)
    }

    override fun uploadImagehReady(photo: Photo) {
        Log.d("error_response_api", photo.toString())
    }

    override fun onUploadImagehError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }
}