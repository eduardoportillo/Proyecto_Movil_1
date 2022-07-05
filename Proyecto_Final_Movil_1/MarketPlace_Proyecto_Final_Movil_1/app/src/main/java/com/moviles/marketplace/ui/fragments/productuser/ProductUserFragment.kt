package com.moviles.marketplace.ui.fragments.productuser

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.marketplace.models.Search
import com.moviles.marketplace.api.ProductRepository
import com.moviles.marketplace.databinding.FragmentProductUserBinding
import com.moviles.marketplace.models.Product
import com.moviles.marketplace.ui.fragments.marketplace.MarketPlaceAdapter

class ProductUserFragment : Fragment(), ProductUserAdapter.ProductListUserEventListener,
    ProductRepository.GetProductByUserListener {
    private var _binding: FragmentProductUserBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentProductUserBinding.inflate(inflater, container, false)
        val root: View = binding.root


        return root
    }
    override fun onResume() {
        super.onResume()
        fetchSetup()
    }

    private fun fetchSetup() {
        fetchProductByUserList()
    }

    private fun fetchProductByUserList() {
        ProductRepository().getProduct(this)
    }

    override fun onVerProductUserClick(idProduct: Long) {
        TODO("Not yet implemented")
    }

    override fun getProductByUserReady(products: ArrayList<Product>) {
        val adapter = ProductUserAdapter(products, this)
        binding.recyclerViewProductUser.layoutManager = GridLayoutManager(this.context, 2)
        binding.recyclerViewProductUser.adapter = adapter
    }

    override fun onProductByUserError(t: Throwable) {
        Log.d("error_response_api", t.toString())
    }
}