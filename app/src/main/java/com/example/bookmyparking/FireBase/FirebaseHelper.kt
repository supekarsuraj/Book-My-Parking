package com.example.bookmyparking.signloginpage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.Adaptar.ProductAdapter
import com.example.bookmyparking.Product.Product
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream

class FirebaseHelper {

    private val db = FirebaseFirestore.getInstance()

    fun checkIfPhoneExists(phone: String, callback: (Boolean) -> Unit) {
        db.collection("users")
            .whereEqualTo("phone", phone)
            .get()
            .addOnSuccessListener { documents ->
                callback(!documents.isEmpty)
            }
            .addOnFailureListener {
                callback(false)
            }
    }

    fun uploadProduct(
        context: Context,
        drawableResId: Int,
        fileName: String,
        category: String,
        name: String,
        price: Double,
        rating: Float,
        id: String,
        information: String
    ) {
        val bitmap = BitmapFactory.decodeResource(context.resources, drawableResId)
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val imageData = baos.toByteArray()
        val base64Image = Base64.encodeToString(imageData, Base64.DEFAULT)
        val product = hashMapOf(
            "category" to category,
            "name" to name,
            "price" to price,
            "rating" to rating,
            "image" to base64Image,
            "productId" to id,
            "productInformation" to information
        )
        FirebaseFirestore.getInstance()
            .collection("products")
            .add(product)
            .addOnSuccessListener {
                Toast.makeText(context, "$name uploaded!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(context, "Error saving $name", Toast.LENGTH_SHORT).show()
            }
    }

    // Legacy method for backwards compatibility - redirect to callback version
    fun fetchProductsFromFirestore(recyclerView: RecyclerView, context: Context) {
        fetchProductsFromFirestore(object : ProductsCallback {
            override fun onProductsLoaded(products: List<Product>) {
                if (products.isNotEmpty()) {
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    recyclerView.adapter = ProductAdapter(products)
                    Log.i("FirebaseFetch", "Successfully loaded ${products.size} products")
                } else {
                    Log.w("FirebaseFetch", "No products found in Firestore")
                    Toast.makeText(context, "No products found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onError(message: String) {
                Log.e("FirebaseFetch", "Firestore fetch failed: $message")
                Toast.makeText(context, "Failed to load products from database", Toast.LENGTH_SHORT).show()
            }
        })
    }

    // Interface for ProductsCallback
    interface ProductsCallback {
        fun onProductsLoaded(products: List<Product>)
        fun onError(message: String)
    }

    // Method to fetch products using callback
    fun fetchProductsFromFirestore(callback: ProductsCallback) {
        val db = FirebaseFirestore.getInstance()
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val productList = mutableListOf<Product>()
                for (document in result) {
                    try {
                        val product = Product(
                            image = document.getString("image") ?: "",
                            category = document.getString("category") ?: "",
                            name = document.getString("name") ?: "",
                            price = document.getDouble("price") ?: 0.0,
                            rating = document.getDouble("rating")?.toFloat() ?: 0.0f,
                            productId = document.getString("productId") ?: "",
                            productInformation = document.getString("productInformation") ?: "No description available for this product."
                        )
                        productList.add(product)
                    } catch (e: Exception) {
                        Log.e("FirebaseFetch", "Error converting document: ${e.message}")
                    }
                }

                if (productList.isNotEmpty()) {
                    Log.i("FirebaseFetch", "Successfully loaded ${productList.size} products")
                    callback.onProductsLoaded(productList)
                } else {
                    Log.w("FirebaseFetch", "No products found in Firestore")
                    callback.onProductsLoaded(emptyList())
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirebaseFetch", "Firestore fetch failed: ${exception.message}")
                callback.onError("Failed to load products: ${exception.message}")
            }
    }
}