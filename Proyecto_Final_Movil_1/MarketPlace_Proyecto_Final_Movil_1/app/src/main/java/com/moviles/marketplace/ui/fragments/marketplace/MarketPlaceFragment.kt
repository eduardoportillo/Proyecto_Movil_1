package com.moviles.marketplace.ui.fragments.marketplace

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marketplace.models.Search
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.FragmentMarketplaceBinding
import com.moviles.marketplace.models.Product

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
        fetchSetup()
    }

    fun fetchSetup(){
        fetchProductSerchList()
    }


    private fun fetchProductSerchList() {
        val serch = Search(
            latitude= "-17.774281",
            longitude= "-63.184093",
            radius= 10000,
        )
        ProductRepository().getAllProductsWithSearch(serch, this)
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