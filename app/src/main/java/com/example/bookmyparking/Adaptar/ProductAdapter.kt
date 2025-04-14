package com.example.bookmyparking.Adaptar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.Product.Product
import com.example.bookmyparking.R

import android.graphics.BitmapFactory
import android.util.Base64


class ProductAdapter(private val productList: List<Product>) :
    RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgProduct: ImageView = view.findViewById(R.id.imgProduct)
        val tvCategory: TextView = view.findViewById(R.id.tvCategory)
        val tvProductName: TextView = view.findViewById(R.id.tvProductName)
        val tvPrice: TextView = view.findViewById(R.id.tvPrice)
        val ratingBar: RatingBar = view.findViewById(R.id.ratingBar)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_product, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]

        // Check if we have a base64 string or a resource ID
        if (product.image.isNotEmpty()) {
            try {
                // Decode base64 image string to Bitmap
                val imageBytes = Base64.decode(product.image, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                holder.imgProduct.setImageBitmap(bitmap)
            } catch (e: Exception) {
                // If decoding fails, use a default image
                holder.imgProduct.setImageResource(R.drawable.product8_cleanser_concentrate)
            }
        } else if (product.imageResource != 0) {
            // Use resource ID directly
            holder.imgProduct.setImageResource(product.imageResource)
        } else {
            // Default image if neither is available
            holder.imgProduct.setImageResource(R.drawable.product8_cleanser_concentrate)
        }

        holder.tvCategory.text = product.category
        holder.tvProductName.text = product.name
        holder.tvPrice.text = "$${product.price}"
        holder.ratingBar.rating = product.rating
    }

    override fun getItemCount(): Int = productList.size
}

