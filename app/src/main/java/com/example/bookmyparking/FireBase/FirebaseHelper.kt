package com.example.bookmyparking.signloginpage

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Base64
import android.util.Log
import android.widget.Toast
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
}
