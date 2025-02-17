package com.example.bookmyparking.signloginpage

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmyparking.R
import com.google.firebase.firestore.FirebaseFirestore

class signIn : AppCompatActivity() {

    private lateinit var etUsername: EditText
    private lateinit var etUserphone: EditText
    private lateinit var etUserPassword: EditText
    private lateinit var btnSignUp: Button
    private val db = FirebaseFirestore.getInstance()
    private val firebaseHelper = FirebaseHelper()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_up_in)

        etUsername = findViewById(R.id.et_Username)
        etUserphone = findViewById(R.id.et_Userphone)
        etUserPassword = findViewById(R.id.et_Userlocation)
        btnSignUp = findViewById(R.id.btn_sign_in)

        btnSignUp.setOnClickListener {
            val username = etUsername.text.toString().trim()
            val phone = etUserphone.text.toString().trim()
            val password = etUserPassword.text.toString().trim()

            if (username.isEmpty() || phone.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            firebaseHelper.checkIfPhoneExists(phone) { exists ->
                if (exists) {
                    Toast.makeText(this, "Phone number already registered!", Toast.LENGTH_SHORT).show()
                } else {
                    saveUserToFirestore(username, phone, password)
                }
            }
        }
    }

    private fun saveUserToFirestore(name: String, phone: String, password: String) {
        val user = hashMapOf(
            "name" to name,
            "phone" to phone,
            "password" to password
        )
        Log.i("saveUserToFirestore","user$user")

        db.collection("users")
            .add(user)
            .addOnSuccessListener {
                Toast.makeText(this, "User registered successfully!", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener {
                Toast.makeText(this, "Registration failed!", Toast.LENGTH_SHORT).show()
            }
    }
}
