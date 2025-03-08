package com.example.bookmyparking.Home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookmyparking.R
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.Adaptar.ProductAdapter
import com.example.bookmyparking.Product.Product
import com.example.bookmyparking.followUs.FollowAdapter
import com.example.bookmyparking.followUs.FollowImage
import com.example.bookmyparking.reweiwes.Review
import com.example.bookmyparking.reweiwes.ReviewAdapter

class homePage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page) // This is your main layout

        // Product list
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

        // Find RecyclerView from layout
        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewProducts)
        recyclerView.layoutManager = GridLayoutManager(this, 2) // 2 columns
        recyclerView.adapter = ProductAdapter(productList)

        val reviewList = listOf(
            Review(R.drawable.profile1, "Jennifer Lewis", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit."),
            Review(R.drawable.profile2, "Alicia Heart", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit."),
            Review(R.drawable.profile3, "Michael James", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit."),
        )

        // Set up RecyclerView for Reviews
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

        // Set up RecyclerView for Follow Images
        val recyclerViewFollow: RecyclerView = findViewById(R.id.recyclerViewFollowImages)
        recyclerViewFollow.layoutManager = GridLayoutManager(this, 1) // 2 columns
        recyclerViewFollow.adapter = FollowAdapter(followImages)
    }

}
