package com.example.bookmyparking.followUs


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.followUs.FollowImage
import com.example.bookmyparking.R

class FollowAdapter(private val imageList: List<FollowImage>) :
    RecyclerView.Adapter<FollowAdapter.FollowViewHolder>() {

    class FollowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgFollow: ImageView = view.findViewById(R.id.imgFollow)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_follow_image, parent, false)
        return FollowViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowViewHolder, position: Int) {
        val image = imageList[position]
        holder.imgFollow.setImageResource(image.imageRes)
    }

    override fun getItemCount() = imageList.size
}
