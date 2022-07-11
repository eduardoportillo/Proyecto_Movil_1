package com.moviles.marketplace.ui.fragments.productuser

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

class ProductUserAdapter(
    val data: ArrayList<Product>,
    val listener: ProductUserAdapter.ProductListUserEventListener
) : RecyclerView.Adapter<ProductUserAdapter.ProductUserViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ProductUserAdapter.ProductUserViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_grid_product, parent, false)
        return ProductUserAdapter.ProductUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductUserAdapter.ProductUserViewHolder, position: Int) {
        val product = data[position]
        val context = holder.imageProductItem.context

        if (product.photos != null && product.photos?.size != 0) {
            val url: String = product.photos.get(0).url.toString()
            Glide.with(context)
                .load(url)
                .into(holder.imageProductItem)
        } else {
            Glide.with(context).clear(holder.imageProductItem)
            context.getDrawable(R.drawable.noimage)
        }

        holder.titleProductItem.text = product.title

        holder.precioProductItem.text = product.price.toString() + " Bs."

        holder.layaoutItemProduct.setOnClickListener{
            listener.onVerProductUserClick(product.id as Long)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    class ProductUserViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imageProductItem: ImageView = view.findViewById(R.id.imageProductItem)
        val titleProductItem: TextView = view.findViewById(R.id.titleProductItem)
        var precioProductItem: TextView = itemView.findViewById(R.id.precioProductItem)
        var layaoutItemProduct: View = itemView.findViewById(R.id.layaoutItemProduct)
    }

    interface ProductListUserEventListener {
        fun onVerProductUserClick(idProduct: Long)
    }

}