package com.moviles.marketplace.ui.activities

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.moviles.marketplace.R
import com.moviles.marketplace.api.CategoryRepository
import com.moviles.marketplace.api.ChatRepository
import com.moviles.marketplace.databinding.ActivityLoginBinding
import com.moviles.marketplace.databinding.ActivityProductFormBinding
import com.moviles.marketplace.models.Category

class ProductFormActivity : AppCompatActivity(), CategoryRepository.CreateCategoryListener,
    CategoryRepository.GetCategoriesListener, AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityProductFormBinding

    private lateinit var arrayCategoriesAdapter: ArrayAdapter<String>
    private var categorySelected: String? = null
    private var categoryId: Long? = null
    private lateinit var categories: ArrayList<Category>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        binding.btnSiguente.setOnClickListener {}
        binding.btnCancelar.setOnClickListener { finish() }
    }

    private fun fetchGetCategories() {
        CategoryRepository().getCategories(this)
    }

    override fun createCategoryReady(category: Category) {
        binding.errorCategoryLabel.setTextColor(Color.parseColor("#008000"))
        binding.errorCategoryLabel.text = "Categoria Creada"
        fetchGetCategories()
    }

    override fun onCreateCategoryError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

    override fun getCategoriesReady(categories: ArrayList<Category>) {
        this.categories = categories
        arrayCategoriesAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item)
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

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }
}

