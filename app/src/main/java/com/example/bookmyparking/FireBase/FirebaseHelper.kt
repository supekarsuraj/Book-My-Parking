package com.example.bookmyparking.signloginpage

import com.google.firebase.firestore.FirebaseFirestore

class FirebaseHelper {

    private val db = FirebaseFirestore.getInstance()

    fun checkIfPhoneExists(phone: String, callback: (Boolean) -> Unit) {
        db.collection("users")
            .whereEqualTo("phone", phone)
            .get()
            .addOnSuccessListener { documents ->
                callback(!documents.isEmpty) // If documents exist, phone is already registered
            }
            .addOnFailureListener {
                callback(false) // Assume not registered in case of failure
            }
    }
}
