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
import com.example.bookmyparking.R
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
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

//    fun fetchProductsFromFirestore() {
//
//
//        val db = FirebaseFirestore.getInstance()
//        db.collection("products")
//            .get()
//            .addOnSuccessListener { result ->
//                val productList = mutableListOf<Product>()
//                for (document in result) {
//                    try {
//                        val product = document.toObject(Product::class.java)
//                        productList.add(product)
//                    } catch (e: Exception) {
//                        Log.e("FirebaseFetch", "Error converting document: ${e.message}")
//                    }
//                }
//
//                Log.i("fetchProductsFromFirestore", "Fetched products = ${productList[0]}")
//
//                // Use the list somewhere, like sending to adapter
//            }
//            .addOnFailureListener { exception ->
//                Log.e("FirebaseFetch", "Firestore fetch failed: ${exception.message}")
//            }
//    }

    fun fetchProductsFromFirestore(recyclerView: RecyclerView, context: Context) {
        val db = FirebaseFirestore.getInstance()
        db.collection("products")
            .get()
            .addOnSuccessListener { result ->
                val productList = mutableListOf<Product>()
                for (document in result) {
                    try {
                        // Make sure to get the product information for details page
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
                    // Update the RecyclerView with the fetched products
                    recyclerView.layoutManager = GridLayoutManager(context, 2)
                    recyclerView.adapter = ProductAdapter(productList)
                    Log.i("FirebaseFetch", "Successfully loaded ${productList.size} products")
                } else {
                    Log.w("FirebaseFetch", "No products found in Firestore")
                }
            }
            .addOnFailureListener { exception ->
                Log.e("FirebaseFetch", "Firestore fetch failed: ${exception.message}")
                Toast.makeText(context, "Failed to load products from database", Toast.LENGTH_SHORT).show()
            }
    }


}



