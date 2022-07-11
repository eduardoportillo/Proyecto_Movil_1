package com.moviles.marketplace.ui.activities

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.commit
import com.example.marketplace.models.Search
import com.moviles.marketplace.MarketPlaceApplication
import com.moviles.marketplace.R
import com.moviles.marketplace.api.CategoryRepository
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.ActivityCategoryFilterBinding
import com.moviles.marketplace.models.Category
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.ui.fragments.MapsFragment
import com.moviles.marketplace.ui.fragments.marketplace.MarketPlaceFragment


class CategoryFilterActivity : AppCompatActivity(), CategoryRepository.GetCategoriesListener,
    CategoryRepository.CreateCategoryListener, AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityCategoryFilterBinding
    private lateinit var arrayCategoriesAdapter: ArrayAdapter<String>
    private lateinit var categories: ArrayList<Category>
    private var categorySelected: String? = null
    private var categoryId: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category_filter)

        binding = ActivityCategoryFilterBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        fetchGetCategories()
        setupButton()
    }


    private fun setupButton() {
        binding.btnFiltrarByCategory.setOnClickListener {
            finish()
        }
    }

    private fun fetchGetCategories() {
        CategoryRepository().getCategories(this)
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

    override fun createCategoryReady(category: Category) {
        binding.errorCategoryLabel.setTextColor(Color.parseColor("#008000"))
        binding.errorCategoryLabel.text = "          Categoria " + category.name + " Creada"
        fetchGetCategories()
    }

    override fun onCreateCategoryError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, positon: Int, p3: Long) {
        categorySelected = arrayCategoriesAdapter.getItem(positon)
        categoryId = categories[positon].id
//        setCategory(categoryId!!) // ver si funciona
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {}

//    override fun setCategory(idCategory: Long) {
//        // ver cuando este cuerdo
//    }

}