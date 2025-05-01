package com.example.bookmyparking.Home

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.Adaptar.ProductAdapter
import com.example.bookmyparking.DeliveryOrder.DeliveryOrder
import com.example.bookmyparking.DeliveryOrder.OrderFeatureAdapter
import com.example.bookmyparking.ImportantArrays.ImpArrays
import com.example.bookmyparking.Product.Product
import com.example.bookmyparking.Product.ShopAllActivity
import com.example.bookmyparking.R
import com.example.bookmyparking.followUs.FollowAdapter
import com.example.bookmyparking.followUs.FollowImage
import com.example.bookmyparking.reweiwes.Review
import com.example.bookmyparking.reweiwes.ReviewAdapter
import com.example.bookmyparking.signloginpage.FirebaseHelper
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class homePage : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private val firebaseHelper = FirebaseHelper()
    private lateinit var drawerLayout: DrawerLayout
    private lateinit var navigationView: NavigationView
    private lateinit var menuIcon: ImageView
    private lateinit var toggle: ActionBarDrawerToggle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.navigation_drawer_layout)

        // Initialize drawer layout and navigation view
        drawerLayout = findViewById(R.id.drawerLayout)
        navigationView = findViewById(R.id.navigationView)
        navigationView.setNavigationItemSelectedListener(this)

        // Get user email if logged in
        val currentUser = FirebaseAuth.getInstance().currentUser
        if (currentUser != null) {
            val headerView = navigationView.getHeaderView(0)
            val userEmailTextView = headerView.findViewById<TextView>(R.id.tvUserEmail)
            userEmailTextView.text = currentUser.email
        }

        // Setup drawer toggle
        menuIcon = findViewById(R.id.menuIcon)
        menuIcon.setOnClickListener {
            if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                drawerLayout.closeDrawer(GravityCompat.START)
            } else {
                drawerLayout.openDrawer(GravityCompat.START)
            }
        }

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewProducts)

        // Load products from Firebase using callback version for homepage
        firebaseHelper.fetchProductsFromFirestore(object : FirebaseHelper.ProductsCallback {
            override fun onProductsLoaded(products: List<Product>) {
                if (products.isNotEmpty()) {
                    recyclerView.layoutManager = GridLayoutManager(this@homePage, 2)
                    recyclerView.adapter = ProductAdapter(products)
                } else {
                    Toast.makeText(this@homePage, "No products found", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onError(message: String) {
                Toast.makeText(this@homePage, "Error: $message", Toast.LENGTH_SHORT).show()
            }
        })

        val reviewList = listOf(
            Review(R.drawable.profile1, "Jennifer Lewis", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit."),
            Review(R.drawable.profile2, "Alicia Heart", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit."),
            Review(R.drawable.profile3, "Michael James", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit.")
        )

        val recyclerViewReviews: RecyclerView = findViewById(R.id.recyclerViewReviews)
        recyclerViewReviews.layoutManager = LinearLayoutManager(this)
        recyclerViewReviews.adapter = ReviewAdapter(reviewList)

        val followImages = listOf(
            FollowImage(R.drawable.follow_img1),
            FollowImage(R.drawable.follow_img2),
            FollowImage(R.drawable.follow_img3),
            FollowImage(R.drawable.follow_img4),
            FollowImage(R.drawable.follow_img5),
            FollowImage(R.drawable.follow_img6)
        )
        val recyclerViewFollow: RecyclerView = findViewById(R.id.recyclerViewFollowImages)
        recyclerViewFollow.layoutManager = GridLayoutManager(this, 1)
        recyclerViewFollow.adapter = FollowAdapter(followImages)

        val orderFeatures = listOf(
            DeliveryOrder(R.drawable.ic_delivery, "FREE DELIVERY", "Nullam pharetra egestas mollis"),
            DeliveryOrder(R.drawable.card, "EASY PAYMENT", "Urna est enim pellentesque"),
            DeliveryOrder(R.drawable.ic_delivery, "TRACK ORDER", "Mauris lacus nunc pellentesque"),
            DeliveryOrder(R.drawable.question_mark, "HAVE QUESTIONS?", "Vulputate enim quis sollicitudin")
        )
        val recyclerViewOrders: RecyclerView = findViewById(R.id.recyclerViewOrders)
        recyclerViewOrders.layoutManager = LinearLayoutManager(this)
        recyclerViewOrders.adapter = OrderFeatureAdapter(orderFeatures)

        val shopNowBtn: Button = findViewById(R.id.shopNowBtn)
        shopNowBtn.setOnClickListener {
            val scrollView = findViewById<ScrollView>(R.id.mainScrollView)
            scrollView?.smoothScrollTo(0, 0)
            shopNowBtn.setTextColor(Color.parseColor("#FF69B4"))
            shopNowBtn.setBackgroundColor(Color.WHITE)
            Handler(Looper.getMainLooper()).postDelayed({
                shopNowBtn.setTextColor(Color.WHITE)
                shopNowBtn.setBackgroundResource(R.drawable.btn_border)
            }, 2000)
        }

        val shopNowBtn2: Button = findViewById(R.id.shopNowBtn2)
        shopNowBtn2.setOnClickListener {
            val scrollView = findViewById<ScrollView>(R.id.mainScrollView)
            scrollView?.smoothScrollTo(0, 0)
            shopNowBtn2.setTextColor(Color.WHITE)
            shopNowBtn2.setBackgroundColor(Color.parseColor("#FF69B4"))
            Handler(Looper.getMainLooper()).postDelayed({
                shopNowBtn2.setTextColor(ContextCompat.getColor(this, R.color.baby_pink))
                shopNowBtn2.setBackgroundResource(R.drawable.pinkbtn_border)
            }, 2000)
        }

        val tvDescription = findViewById<TextView>(R.id.tvDescription)
        val tvExtra = findViewById<TextView>(R.id.tvExtra)

        val readMoreButton = findViewById<Button>(R.id.Readmore)
        val readLessButton = findViewById<Button>(R.id.ReadLess)

        tvExtra.visibility = View.GONE
        readLessButton.visibility = View.GONE

        readMoreButton.setOnClickListener {
            readMoreButton.setTextColor(Color.WHITE)
            readMoreButton.setBackgroundColor(Color.parseColor("#FF69B4"))
            Handler(Looper.getMainLooper()).postDelayed({
                readMoreButton.setTextColor(ContextCompat.getColor(this, R.color.baby_pink))
                readMoreButton.setBackgroundResource(R.drawable.pinkbtn_border)
            }, 1000)

            tvDescription.text = ImpArrays().beutyFlwlessSkin1
            tvExtra.text = ImpArrays().beutyFlwlessSkin2
            tvExtra.visibility = View.VISIBLE
            readMoreButton.visibility = View.GONE
            readLessButton.visibility = View.VISIBLE
        }

        readLessButton.setOnClickListener {
            readLessButton.setTextColor(Color.WHITE)
            readLessButton.setBackgroundColor(Color.parseColor("#FF69B4"))
            Handler(Looper.getMainLooper()).postDelayed({
                readLessButton.setTextColor(ContextCompat.getColor(this, R.color.baby_pink))
                readLessButton.setBackgroundResource(R.drawable.pinkbtn_border)
            }, 1000)
            tvExtra.visibility = View.GONE
            tvDescription.text = ImpArrays().beutyFlwlessSkin1
            readMoreButton.visibility = View.VISIBLE
            readLessButton.visibility = View.GONE
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here
        when (item.itemId) {
            R.id.nav_home -> {
                // Stay on current page, just close drawer
                drawerLayout.closeDrawer(GravityCompat.START)
            }
            R.id.nav_shop -> {
                Toast.makeText(this, "Shop All Selected", Toast.LENGTH_SHORT).show()
                // Navigate to shop all products screen
                val intent = Intent(this, ShopAllActivity::class.java)
                startActivity(intent)
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

    fun initProductUpload() {
        data class Product(
            val imageResId: Int,
            val category: String,
            val name: String,
            val price: Double,
            val rating: Float
        )
        val firebaseHelper = FirebaseHelper()
        val productList = listOf(
            Product(R.drawable.product1_hybrid_cleasing_balm, "Cleanser", "Hybrid Cleansing Balm", 32.90, 4.5f),
            Product(R.drawable.product2_soothing_sunscreen_gel, "Sunscreens", "Soothing Sunscreen Gel", 24.50, 4.0f),
            Product(R.drawable.product3_calm_hydrating_moisturizer, "Body Wash", "Skin Relief Body Wash", 15.00, 3.8f),
            Product(R.drawable.product4_energizing_marine_lotion, "Moisturizer", "Hydrating Face Cream", 19.99, 4.2f),
            Product(R.drawable.product5_mekeup_melting_cleanser, "Serum", "Vitamin C Serum", 27.50, 4.7f),
            Product(R.drawable.product6_balancing_daily_cleanser, "Lotion", "Hydrating Body Lotion", 22.75, 3.9f),
            Product(R.drawable.product7_hydrating_gel_oil, "Mask", "Detox Clay Mask", 18.99, 4.3f),
            Product(R.drawable.product8_cleanser_concentrate, "Toner", "Refreshing Face Toner", 16.50, 4.1f)
        )
        productList.forEachIndexed { index, product ->
            val fileName = "product_${index + 1}.jpg"
            val id = (index + 1).toString()
            val info = "This is a dummy description for ${product.name}. Great for daily skincare."

            firebaseHelper.uploadProduct(
                context = this,
                drawableResId = product.imageResId,
                fileName = fileName,
                category = product.category,
                name = product.name,
                price = product.price,
                rating = product.rating,
                id=id.toString(),
                information=info.toString()
            )
        }
    }
}