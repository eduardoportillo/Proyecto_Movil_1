package com.moviles.marketplace.ui.activities

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.moviles.marketplace.api.CategoryRepository
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.ActivityProductFormBinding
import com.moviles.marketplace.models.Category
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.ui.fragments.MapsFragment


class ProductFormActivity : AppCompatActivity(), ProductRepository.CreteProductListener,
    CategoryRepository.CreateCategoryListener,
    CategoryRepository.GetCategoriesListener, AdapterView.OnItemSelectedListener, MapsFragment.latLngEventListener {

    private lateinit var binding: ActivityProductFormBinding

    private lateinit var arrayCategoriesAdapter: ArrayAdapter<String>
    private var categorySelected: String? = null
    private var categoryId: Long? = null
    private lateinit var categories: ArrayList<Category>


    private lateinit var latitude: String
    private lateinit var longitude: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.moviles.marketplace.R.layout.activity_login)

        binding = ActivityProductFormBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

    override fun onResume() {
        super.onResume()
        setupButtonListeners()
        fetchGetCategories()
    }

    private fun setupButtonListeners() {
        binding.btnAddCategoria.setOnClickListener {
            if (binding.addCategoryInput.text.toString().isEmpty()) {
                binding.errorCategoryLabel.text = "El nombre de la categoria no puede estar vacio"
            } else {
                if (binding.addCategoryInput.text.toString().isBlank()) {
                    binding.errorCategoryLabel.text = "El nombre de la categoria no puede en blanco"
                } else {
                    binding.errorCategoryLabel.text = ""

                    val category = Category(
                        name = binding.addCategoryInput.text.toString()
                    )
                    CategoryRepository().createCategory(category, this)
                }
            }
        }


        binding.btnSiguente.setOnClickListener {
            val product = Product(
                title = binding.tituloInput.text.toString(),
                description = binding.descripcionInput.text.toString(),
                price = binding.percioInput.text.toString().toDouble(),
                category_id = categoryId,
                longitude = this.longitude,
                latitude = this.latitude

            )
            ProductRepository().createProduct(product, this)
        }

        binding.btnCancelar.setOnClickListener { finish() }
    }

    private fun fetchGetCategories() {
        CategoryRepository().getCategories(this)
    }

    override fun createCategoryReady(category: Category) {
        binding.errorCategoryLabel.setTextColor(Color.parseColor("#008000"))
        binding.errorCategoryLabel.text = "          Categoria " + category.name + " Creada"
        fetchGetCategories()
    }

    override fun onCreateCategoryError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

    override fun getCategoriesReady(categories: ArrayList<Category>) {
        this.categories = categories
        arrayCategoriesAdapter =
            ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
        for (category in categories) {
            arrayCategoriesAdapter.add(category.name)
        }
        binding.spinnerCategorias.onItemSelectedListener = this
        binding.spinnerCategorias.adapter = arrayCategoriesAdapter
    }

    override fun onGetCategoriesError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, positon: Int, p3: Long) {
        categorySelected = arrayCategoriesAdapter.getItem(positon)
        categoryId = categories[positon].id
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

    override fun creteProductReady(product: Product) {
        TODO("Not yet implemented")
    }

    override fun onCreteProductError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

    override fun latLngSend(latitude: String,longitude: String) {
        binding.errorCategoryLabel.text = "latitude: " + latitude + " longitude: " + longitude
    }

}

