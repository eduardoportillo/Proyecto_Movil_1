package com.moviles.marketplace.ui.fragments.marketplace

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
import com.moviles.marketplace.ui.activities.MapsRadius
import com.moviles.marketplace.ui.activities.ProductFormActivity

class MarketPlaceFragment : Fragment(), MarketPlaceAdapter.ProductListEventListener,
    ProductRepository.GetAllProductsWithSearchListener {
    private var _binding: FragmentMarketplaceBinding? = null

    private val binding get() = _binding!!

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

    private fun setupButtons(){
        binding.btnFilterUbication.setOnClickListener{
            val editProfileIntent = Intent(activity, MapsRadius::class.java)
            startActivity(editProfileIntent)
        }

        binding.btnVender.setOnClickListener{
            val editProfileIntent = Intent(activity, ProductFormActivity::class.java)
            startActivity(editProfileIntent)
        }

    }

    private fun fetchSetup(){
        fetchProductSearchList()
    }


    private fun fetchProductSearchList() {
        val search = Search(
            latitude= sharedPref.getLatitude(),
            longitude= sharedPref.getLongitude(),
            radius= sharedPref.getRadius(),
        )
        ProductRepository().getAllProductsWithSearch(search, this)
    }

    override fun onVerProductClick(idProduct: Long) {
        // TODO: Implementar cuando se oprima en el producto
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