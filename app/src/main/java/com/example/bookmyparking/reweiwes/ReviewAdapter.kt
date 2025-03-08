package com.example.bookmyparking.reweiwes



import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.R

class ReviewAdapter(private val reviewList: List<Review>) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    class ReviewViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgReviewer: ImageView = view.findViewById(R.id.imgreviewer)
        val tvReviewerName: TextView = view.findViewById(R.id.tvReviewerName)
        val tvReview: TextView = view.findViewById(R.id.tvreview)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_review, parent, false)
        return ReviewViewHolder(view)
    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val review = reviewList[position]
        holder.imgReviewer.setImageResource(review.imageRes)
        holder.tvReviewerName.text = review.name
        holder.tvReview.text = review.reviewText
    }

    override fun getItemCount() = reviewList.size
}
