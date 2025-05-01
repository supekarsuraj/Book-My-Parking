package com.example.bookmyparking.Product
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.Adaptar.ProductAdapter
import com.example.bookmyparking.Home.homePage
import com.example.bookmyparking.R
import com.example.bookmyparking.signloginpage.FirebaseHelper
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class ShopAllActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val firebaseHelper = FirebaseHelper()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menuIcon: ImageView
    private lateinit var recyclerViewProducts: RecyclerView
    private lateinit var loadingIndicator: ProgressBar
    private lateinit var emptyState: LinearLayout
    private lateinit var productCount: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.i("ShopAllActivity","ShopAllActivity")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shop_all_screen)
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)
        menuIcon = findViewById(R.id.menuIcon)
        recyclerViewProducts = findViewById(R.id.recyclerViewAllProducts)
        loadingIndicator = findViewById(R.id.loadingIndicator)
        emptyState = findViewById(R.id.emptyState)
        productCount = findViewById(R.id.productCount)

        // Get user email if logged in
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        if (currentUser != null) {
//            val headerView = navigationView.getHeaderView(0)
//            val userEmailTextView = headerView.findViewById<TextView>(R.id.tvUserEmail)
//            userEmailTextView.text = currentUser.email
//        }
Log.i("Suraj","123")
        // Update header title
        val headerTitle = findViewById<TextView>(R.id.headerTitle)
        headerTitle.text = "Flawless"

        // Setup drawer toggle
        menuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        // Setup cart icon click
        val cartIcon = findViewById<ImageView>(R.id.cartIcon)
        cartIcon.setOnClickListener {
            Toast.makeText(this, "Cart clicked", Toast.LENGTH_SHORT).show()
            // Navigate to cart activity
        }

        // Setup filter icon click
        val filterIcon = findViewById<ImageView>(R.id.filterIcon)
        filterIcon.setOnClickListener {
            Toast.makeText(this, "Filter options", Toast.LENGTH_SHORT).show()
            // Show filter dialog or options
        }

        // Setup RecyclerView for products
        recyclerViewProducts.layoutManager = GridLayoutManager(this, 2)

        // Show loading state
        loadingIndicator.visibility = View.VISIBLE
        recyclerViewProducts.visibility = View.GONE
        emptyState.visibility = View.GONE

        // Load all products
        loadAllProducts()
    }

    private fun loadAllProducts() {
        // Call your Firebase Helper to fetch all products
        firebaseHelper.fetchProductsFromFirestore(object : FirebaseHelper.ProductsCallback {
            override fun onProductsLoaded(products: List<Product>) {
                loadingIndicator.visibility = View.GONE

                if (products.isEmpty()) {
                    // Show empty state
                    recyclerViewProducts.visibility = View.GONE
                    emptyState.visibility = View.VISIBLE
                    productCount.text = "No products found"
                } else {
                    // Show products
                    recyclerViewProducts.visibility = View.VISIBLE
                    emptyState.visibility = View.GONE
                    productCount.text = "Showing ${products.size} products"

                    // Set the adapter
                    recyclerViewProducts.adapter = ProductAdapter(products)
                }
            }

            override fun onError(message: String) {
                loadingIndicator.visibility = View.GONE
                recyclerViewProducts.visibility = View.GONE
                emptyState.visibility = View.VISIBLE
                Toast.makeText(this@ShopAllActivity, "Error: $message", Toast.LENGTH_SHORT).show()
            }
        })
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here
        when (item.itemId) {
            R.id.nav_home -> {
                // Navigate to home page
                val intent = Intent(this, homePage::class.java)
                startActivity(intent)
                finish()
            }
            R.id.nav_shop -> {
                // We're already on shop all, just close drawer
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_categories -> {
                Toast.makeText(this, "Categories Selected", Toast.LENGTH_SHORT).show()
                // Navigate to categories screen
                // Intent code here
            }
            R.id.nav_wishlist -> {
                Toast.makeText(this, "Wishlist Selected", Toast.LENGTH_SHORT).show()
                // Navigate to wishlist screen
                // Intent code here
            }
            R.id.nav_cart -> {
                Toast.makeText(this, "Cart Selected", Toast.LENGTH_SHORT).show()
                // Navigate to cart screen
                // Intent code here
            }
            R.id.nav_profile -> {
                Toast.makeText(this, "Profile Selected", Toast.LENGTH_SHORT).show()
                // Navigate to profile screen
                // Intent code here
            }
            R.id.nav_orders -> {
                Toast.makeText(this, "My Orders Selected", Toast.LENGTH_SHORT).show()
                // Navigate to orders screen
                // Intent code here
            }
            R.id.nav_address -> {
                Toast.makeText(this, "My Addresses Selected", Toast.LENGTH_SHORT).show()
                // Navigate to addresses screen
                // Intent code here
            }
            R.id.nav_contact -> {
                Toast.makeText(this, "Contact Us Selected", Toast.LENGTH_SHORT).show()
                // Navigate to contact screen
                // Intent code here
            }
            R.id.nav_about -> {
                Toast.makeText(this, "About Us Selected", Toast.LENGTH_SHORT).show()
                // Navigate to about screen
                // Intent code here
            }
            R.id.nav_settings -> {
                Toast.makeText(this, "Settings Selected", Toast.LENGTH_SHORT).show()
                // Navigate to settings screen
                // Intent code here
            }
            R.id.nav_logout -> {
                // Handle logout
                FirebaseAuth.getInstance().signOut()
                Toast.makeText(this, "Logged out successfully", Toast.LENGTH_SHORT).show()

                // Navigate to login screen (replace with your login activity)
                // val intent = Intent(this, LoginActivity::class.java)
                // intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                // startActivity(intent)
                // finish()
            }
        }

        // Close the drawer
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