package com.example.bookmyparking.Contact


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bookmyparking.About.AboutActivity
import com.example.bookmyparking.Home.homePage
import com.example.bookmyparking.Product.ShopAllActivity
import com.example.bookmyparking.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class ContactActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menuIcon: ImageView

    private lateinit var nameEditText: EditText
    private lateinit var emailEditText: EditText
    private lateinit var phoneEditText: EditText
    private lateinit var messageEditText: EditText
    private lateinit var submitButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)
Log.i("ContactActivity","ContactActivity1")
//        Log.i("ShopAllActivity","ShopAllActivity")

        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)
        Log.i("ContactActivity","ContactActivity2")

        nameEditText = findViewById(R.id.editTextName)
        emailEditText = findViewById(R.id.editTextEmail)
        phoneEditText = findViewById(R.id.editTextPhone)
        messageEditText = findViewById(R.id.editTextMessage)
        submitButton = findViewById(R.id.buttonSubmit)

        Log.i("ContactActivity","ContactActivity3")
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val headerView = navigationView.getHeaderView(0)
            val userEmailTextView = headerView.findViewById<TextView>(R.id.tvUserEmail)
            userEmailTextView.text = currentUser.email
        }

        Log.i("ContactActivity","ContactActivity4")
        menuIcon = findViewById(R.id.menuIcon)
        menuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        Log.i("ContactActivity","ContactActivity5")
        val headerTitle = findViewById<TextView>(R.id.headerTitle)
        headerTitle.text = "Contact Us"

        // Setup cart icon
        val cartIcon = findViewById<ImageView>(R.id.cartIcon)
        cartIcon.setOnClickListener {
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
        }

        // Setup form submission
        submitButton.setOnClickListener {
            if (validateForm()) {
                // Here you would typically send the form data to your backend
                Toast.makeText(this, "Message sent successfully!", Toast.LENGTH_SHORT).show()
                clearForm()
            }
        }
    }

    private fun validateForm(): Boolean {
        var isValid = true

        if (nameEditText.text.toString().trim().isEmpty()) {
            nameEditText.error = "Name is required"
            isValid = false
        }

        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        if (emailEditText.text.toString().trim().isEmpty()) {
            emailEditText.error = "Email is required"
            isValid = false
        } else if (!emailEditText.text.toString().trim().matches(emailPattern.toRegex())) {
            emailEditText.error = "Enter a valid email address"
            isValid = false
        }

        if (messageEditText.text.toString().trim().isEmpty()) {
            messageEditText.error = "Message is required"
            isValid = false
        }

        return isValid
    }

    private fun clearForm() {
        nameEditText.text.clear()
        emailEditText.text.clear()
        phoneEditText.text.clear()
        messageEditText.text.clear()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                val intent = Intent(this, homePage::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_shop -> {
                val intent = Intent(this, ShopAllActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_categories -> {
                Toast.makeText(this, "Categories Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_wishlist -> {
                Toast.makeText(this, "Wishlist Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_cart -> {
                Toast.makeText(this, "Cart Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_orders -> {
                Toast.makeText(this, "My Orders Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_address -> {
                Toast.makeText(this, "My Addresses Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_contact -> {
                // Already on Contact page, just close drawer
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_about -> {
                val intent = Intent(this, AboutActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_settings -> {
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show()
            }
            R.id.nav_logout -> {
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()
                // Navigate to login screen (replace with your login activity)
                // val intent = Intent(this, LoginActivity::class.java)
                // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                // startActivity(intent)
                // finish()
            }
        }

        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}