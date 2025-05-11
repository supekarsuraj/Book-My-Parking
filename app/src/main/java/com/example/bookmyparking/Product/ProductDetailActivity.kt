package com.example.bookmyparking.Product

import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Base64
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmyparking.R

class ProductDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product_detail)
        findViewById<ImageButton>(R.id.btnBack).setOnClickListener {
            finish()
        }
        val imgProduct = findViewById<ImageView>(R.id.imgProductDetail)
        val tvCategory = findViewById<TextView>(R.id.tvProductCategory)
        val tvProductName = findViewById<TextView>(R.id.tvProductName)
        val tvPrice = findViewById<TextView>(R.id.tvProductPrice)
        val tvDescription = findViewById<TextView>(R.id.tvProductDescription)
        val category = intent.getStringExtra("category") ?: ""
        val name = intent.getStringExtra("name") ?: ""
        val price = intent.getDoubleExtra("price", 0.0)
        val description = intent.getStringExtra("description") ?: ""
        val image = intent.getStringExtra("image")
        val imageResource = intent.getIntExtra("imageResource", 0)
        tvCategory.text = category
        tvProductName.text = name
        tvPrice.text = "$${price} + Free Shipping"
        tvDescription.text = description
        if (!image.isNullOrEmpty()) {
            try { val imageBytes = Base64.decode(image, Base64.DEFAULT)
                val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
                imgProduct.setImageBitmap(bitmap)
            } catch (e: Exception) {
                if (imageResource != 0) {
                    imgProduct.setImageResource(imageResource)
                } else {
                    imgProduct.setImageResource(R.drawable.product8_cleanser_concentrate)
                }
            }
        } else if (imageResource != 0) {
            imgProduct.setImageResource(imageResource)
        } else {
            imgProduct.setImageResource(R.drawable.product8_cleanser_concentrate)
        }
    }
}