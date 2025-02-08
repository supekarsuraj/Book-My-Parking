package com.example.bookmyparking

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signinsignup)

        val buttonSignIn: Button = findViewById(R.id.btnSignIn)
        val buttonSignUp: Button = findViewById(R.id.btnSignUp)

        buttonSignIn.setOnClickListener {
        }

        buttonSignUp.setOnClickListener {
//            val intent = Intent(this, SignUpActivity::class.java)
            startActivity(intent)
        }
    }
}
