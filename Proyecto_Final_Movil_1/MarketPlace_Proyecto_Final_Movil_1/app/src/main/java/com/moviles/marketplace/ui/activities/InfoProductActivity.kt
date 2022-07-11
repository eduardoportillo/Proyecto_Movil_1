package com.moviles.marketplace.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.moviles.marketplace.R
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.ActivityInfoProductBinding
import com.moviles.marketplace.databinding.ActivityProductFormBinding
import com.moviles.marketplace.models.Product

class InfoProductActivity : AppCompatActivity(), ProductRepository.GetProductByIdListener {

    private lateinit var binding: ActivityInfoProductBinding

    private var idProduct: Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_product)

        binding = ActivityInfoProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            idProduct = it.getLong("idProduct")
        }

        fetchProductById()
    }

    fun fetchProductById() {
        ProductRepository().getProductById(idProduct, this)

    }

    override fun getProductByIdReady(product: Product) {

        val context = binding.imgProduct.context

        if (product.photos != null && product.photos?.size != 0) {
            val url: String = product.photos.get(0).url.toString()
            Glide.with(context)
                .load(url)
                .into(binding.imgProduct)
        } else {
            Glide.with(context).clear(binding.imgProduct)
            context.getDrawable(R.drawable.noimage)
        }

        binding.idProductLabel.text = product.id.toString()
        binding.tituloLabel.text = product.title.toString()
        binding.descriptionLabe.text = product.description.toString()
        binding.priceLabel.text = product.price.toString()
        binding.categoryLabel.text = product.category?.name
    }

    override fun onGetProductByIdError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }


}