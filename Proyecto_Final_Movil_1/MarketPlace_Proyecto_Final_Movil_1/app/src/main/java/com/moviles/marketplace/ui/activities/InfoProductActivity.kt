package com.moviles.marketplace.ui.activities

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.marketplace.models.Chat
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.R
import com.moviles.marketplace.api.ChatRepository
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.ActivityInfoProductBinding
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.models.Response
import com.moviles.marketplace.ui.activities.chat.ChatActivity
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem


class InfoProductActivity : AppCompatActivity(), ProductRepository.GetProductByIdListener,
    ProductRepository.DeleteProductListener, ChatRepository.CreateChatListener {

    private lateinit var binding: ActivityInfoProductBinding

    private var idProduct: Long = -1

    val fotosCarouselList = mutableListOf<CarouselItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_info_product)

        binding = ActivityInfoProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.extras?.let {
            idProduct = it.getLong("idProduct")
        }
    }

    override fun onResume() {
        super.onResume()
        fetchProductById()
    }

    fun fetchProductById() {
        ProductRepository().getProductById(idProduct, this)
    }


    fun validateProductUser() {
        binding.btnUpdate.visibility = 1
        binding.btnEliminar.visibility = 1

        binding.btnUpdate.setOnClickListener {
            val intent = Intent(this, ProductFormActivity::class.java)
            intent.putExtra("idProduct", idProduct)
            startActivity(intent)
        }

        binding.btnEliminar.setOnClickListener {
            ProductRepository().deleteProduct(idProduct, this)
        }
    }

    override fun getProductByIdReady(product: Product) {
        for (photo in product.photos!!) {
            fotosCarouselList.add(CarouselItem(photo.url))
        }
        binding.carousel.addData(fotosCarouselList)

        binding.idProductLabel.text = product.id.toString()
        binding.tituloLabel.text = product.title.toString()
        binding.descriptionLabe.text = product.description.toString()
        binding.priceLabel.text = product.price.toString()
        binding.categoryLabel.text = product.category?.name

        if (sharedPref.getUserId() == product.user_id?.toInt()) {
            validateProductUser()
        } else {
            binding.btnChat.visibility = 1

            binding.btnChat.setOnClickListener {
                val chat = Chat(
                    seller_id = product.user_id,
                    product_id = product.id
                )
                ChatRepository().createChat(chat, this)
            }
        }
    }

    override fun onGetProductByIdError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

    override fun deleteProductReady(response: Response) {
        val toast = Toast.makeText(
            applicationContext,
            "Producto Eliminado con Exito", Toast.LENGTH_SHORT
        )
        toast.show()
        finish()
    }

    override fun onDeleteProductError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

    override fun createChatReady(chat: Chat) {
        val intentChat = Intent(this, ChatActivity::class.java)
        intentChat.putExtra("idChat", chat.id)
        startActivity(intentChat)
    }

    override fun oCreateChatError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }


}