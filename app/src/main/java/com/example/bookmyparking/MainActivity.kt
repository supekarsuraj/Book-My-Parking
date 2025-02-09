package com.example.bookmyparking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmyparking.signloginpage.logIn
import com.example.bookmyparking.signloginpage.signIn

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signinsignup)

        val buttonSignIn: Button = findViewById(R.id.btnSignIn)
        val buttonSignUp: Button = findViewById(R.id.btnSignUp)

        buttonSignIn.setOnClickListener {
            val intent = Intent(this, logIn::class.java)
            startActivity(intent)
        }

        buttonSignUp.setOnClickListener {
            val intent = Intent(this, signIn::class.java)
            startActivity(intent)
        }
    }
}
