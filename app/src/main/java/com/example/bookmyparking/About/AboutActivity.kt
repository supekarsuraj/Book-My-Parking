package com.example.bookmyparking.About


import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.bookmyparking.Contact.ContactActivity
import com.example.bookmyparking.Home.homePage
import com.example.bookmyparking.Product.ShopAllActivity
import com.example.bookmyparking.R
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class AboutActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menuIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        // Initialize views
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)

        // Setup current user email in nav header
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val headerView = navigationView.getHeaderView(0)
            val userEmailTextView = headerView.findViewById<TextView>(R.id.tvUserEmail)
            userEmailTextView.text = currentUser.email
        }

        // Setup menu icon
        menuIcon = findViewById(R.id.menuIcon)
        menuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        // Set header title
        val headerTitle = findViewById<TextView>(R.id.headerTitle)
        headerTitle.text = "About Us"

        // Setup cart icon
        val cartIcon = findViewById<ImageView>(R.id.cartIcon)
        cartIcon.setOnClickListener {
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
        }
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
                Toast.makeText(this, "Contact Us Selected", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, ContactActivity::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_about -> {
                // Already on About page, just close drawer
                drawerLayout.closeDrawer(GravityCompat.START)
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