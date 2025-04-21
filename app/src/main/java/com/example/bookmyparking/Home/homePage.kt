package com.example.bookmyparking.Home

import android.R.attr.visible
import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ScrollView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.bookmyparking.R
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.Adaptar.ProductAdapter
import com.example.bookmyparking.DeliveryOrder.DeliveryOrder
import com.example.bookmyparking.DeliveryOrder.OrderFeatureAdapter
import com.example.bookmyparking.ImportantArrays.ImpArrays
import com.example.bookmyparking.Product.Product
import com.example.bookmyparking.followUs.FollowAdapter
import com.example.bookmyparking.followUs.FollowImage
import com.example.bookmyparking.reweiwes.Review
import com.example.bookmyparking.reweiwes.ReviewAdapter
import com.example.bookmyparking.signloginpage.FirebaseHelper

class homePage : AppCompatActivity() {
    private val firebaseHelper = FirebaseHelper()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_page)

        val recyclerView: RecyclerView = findViewById(R.id.recyclerViewProducts)

        // Two options - uncomment the one you want to use:

        // OPTION 1: Load products from Firebase
        firebaseHelper.fetchProductsFromFirestore(recyclerView, this)

        /* OPTION 2: Use hardcoded products (as fallback)
        val productList = listOf(
            Product(imageResource = R.drawable.product1_hybrid_cleasing_balm, category = "Cleanser", name = "Hybrid Cleansing Balm", price = 32.90, rating = 4.5f),
            Product(imageResource = R.drawable.product2_soothing_sunscreen_gel, category = "Sunscreens", name = "Soothing Sunscreen Gel", price = 24.50, rating = 4.0f),
            Product(imageResource = R.drawable.product3_calm_hydrating_moisturizer, category = "Body Wash", name = "Skin Relief Body Wash", price = 15.00, rating = 3.8f),
            Product(imageResource = R.drawable.product4_energizing_marine_lotion, category = "Moisturizer", name = "Hydrating Face Cream", price = 19.99, rating = 4.2f),
            Product(imageResource = R.drawable.product5_mekeup_melting_cleanser, category = "Serum", name = "Vitamin C Serum", price = 27.50, rating = 4.7f),
            Product(imageResource = R.drawable.product6_balancing_daily_cleanser, category = "Lotion", name = "Hydrating Body Lotion", price = 22.75, rating = 3.9f),
            Product(imageResource = R.drawable.product7_hydrating_gel_oil, category = "Mask", name = "Detox Clay Mask", price = 18.99, rating = 4.3f),
            Product(imageResource = R.drawable.product8_cleanser_concentrate, category = "Toner", name = "Refreshing Face Toner", price = 16.50, rating = 4.1f)
        )
        recyclerView.layoutManager = GridLayoutManager(this, 2)
        recyclerView.adapter = ProductAdapter(productList)
        */

        val reviewList = listOf(
            Review(R.drawable.profile1, "Jennifer Lewis", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit."),
            Review(R.drawable.profile2, "Alicia Heart", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit."),
            Review(R.drawable.profile3, "Michael James", "Sed odio donec curabitur auctor amet tincidunt non odio enim felis tincidunt amet morbi egestas hendrerit.")
        )

        val recyclerViewReviews: RecyclerView = findViewById(R.id.recyclerViewReviews)
        recyclerViewReviews.layoutManager = LinearLayoutManager(this)
        recyclerViewReviews.adapter = ReviewAdapter(reviewList)

//
//        val recyclerViewReviews: RecyclerView = findViewById(R.id.recyclerViewReviews)
//        recyclerViewReviews.layoutManager = LinearLayoutManager(this)
//        recyclerViewReviews.adapter = ReviewAdapter(reviewList)
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
            android.os.Handler(Looper.getMainLooper()).postDelayed({
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
            android.os.Handler(Looper.getMainLooper()).postDelayed({
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

//        initProductUpload()
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
