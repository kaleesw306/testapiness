package com.example.appiness.Adapter

import Product
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.appiness.R
import com.example.appiness.Utilities.onClickListner

class Productadapter(private val products: List<Product>,
                     private val listener: onClickListner) : RecyclerView.Adapter<Productadapter.ProductViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).
        inflate(R.layout.product_item, parent,
            false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = products[position]
        holder.bind(product)
        holder.itemView.setOnClickListener {
            listener.onClick(position,product)
        }
    }

    override fun getItemCount(): Int {
       return products.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val name = itemView.findViewById<TextView>(R.id.name)
        private val description= itemView.findViewById<TextView>(R.id.description)
        private val price = itemView.findViewById<TextView>(R.id.price)
        private val imageView= itemView.findViewById<ImageView>(R.id.image)

        fun bind(product: Product) {
            name.text = product.name

            description.text = product.description

            price.text = "Price: ${product.price}"





            Glide.with(itemView.context).applyDefaultRequestOptions(
                RequestOptions.placeholderOf(R.drawable.placeholder)
            )
                .load(product.imagePath?.get("square"))
                .into(imageView)
        }
    }
}
