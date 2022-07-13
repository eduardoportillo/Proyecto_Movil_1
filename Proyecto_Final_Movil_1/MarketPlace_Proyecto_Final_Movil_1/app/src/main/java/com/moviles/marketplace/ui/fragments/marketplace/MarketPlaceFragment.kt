package com.moviles.marketplace.ui.fragments.marketplace

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marketplace.models.Search
import com.moviles.marketplace.MarketPlaceApplication.Companion.sharedPref
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.FragmentMarketplaceBinding
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.ui.activities.*

class MarketPlaceFragment : Fragment(), MarketPlaceAdapter.ProductListEventListener,
    ProductRepository.GetAllProductsWithSearchListener {
    private var _binding: FragmentMarketplaceBinding? = null

    private val binding get() = _binding!!

    var idCategory: Long? = null

    private var products: ArrayList<Product> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMarketplaceBinding.inflate(inflater, container, false)
        val root: View = binding.root
        return root
    }

    override fun onResume() {
        super.onResume()
        setupButtons()
        fetchSetup()

    }

    private fun setupButtons() {
        binding.btnFilterUbication.setOnClickListener {
            val mapsRadiusIntent = Intent(activity, MapsRadius::class.java)
            startActivity(mapsRadiusIntent)
        }

        binding.btnVender.setOnClickListener {
            val productFormIntent = Intent(activity, ProductFormActivity::class.java)
            startActivity(productFormIntent)
        }
        binding.btnFilterCategory.setOnClickListener {
            val categoryFilterIntent = Intent(activity, CategoryFilterActivity::class.java)
            startActivity(categoryFilterIntent)
        }

    }

    private fun fetchSetup() {
        fetchProductSearchList()
    }


    private fun fetchProductSearchList() {

        val search: Search
        if (idCategory != null) {
            search = Search(
                latitude = sharedPref.getLatitude(),
                longitude = sharedPref.getLongitude(),
                radius = sharedPref.getRadius(),
            )
        } else {
            search = Search(
                latitude = sharedPref.getLatitude(),
                longitude = sharedPref.getLongitude(),
                radius = sharedPref.getRadius(),
                category_id = this.idCategory
            )
        }

        ProductRepository().getAllProductsWithSearch(search, this)

        val adapter = MarketPlaceAdapter(products, this)
        binding.recyclerViewMarketPlace.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerViewMarketPlace.adapter = adapter
    }

    override fun onVerProductClick(idProduct: Long) {
        val productForm = Intent(activity, InfoProductActivity::class.java)
        productForm.putExtra("idProduct", idProduct)
        startActivity(productForm)
    }

    override fun getAllProductsWithSearchReady(products: ArrayList<Product>) {
        val adapter = MarketPlaceAdapter(products, this)
        binding.recyclerViewMarketPlace.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerViewMarketPlace.adapter = adapter
    }

    override fun onGetAllProductsWithSearchError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }
}