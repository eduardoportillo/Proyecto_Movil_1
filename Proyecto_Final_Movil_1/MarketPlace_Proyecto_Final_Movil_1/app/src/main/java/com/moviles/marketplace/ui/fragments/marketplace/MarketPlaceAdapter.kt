package com.moviles.marketplace.ui.fragments.marketplace


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.moviles.marketplace.R
import com.moviles.marketplace.models.Product


class MarketPlaceAdapter(val data: ArrayList<Product>, val listener: ProductListEventListener) :
    RecyclerView.Adapter<MarketPlaceAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = data[position]
        val context = holder.imageProductItem.context

        if (product.photos?.get(0) != null) {
            val url: String = product.photos.get(0).url.toString()
            Glide.with(context)
                .load(url)
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(holder.imageProductItem)
        } else {
            Glide.with(context).clear(holder.imageProductItem)
            holder.imageProductItem.setImageDrawable(R.drawable.ic_baseline_person.toDrawable())
        }

        holder.titleProductItem.text = product.title

        holder.precioProductItem.text = product.price.toString() + " Bs."
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageProductItem: ImageView = view.findViewById(R.id.imageProductItem)
        val titleProductItem: TextView = view.findViewById(R.id.titleProductItem)
        var precioProductItem: TextView = itemView.findViewById(R.id.precioProductItem)
    }

    interface ProductListEventListener {
        fun onVerProductClick(idProduct: Long)
    }


}

