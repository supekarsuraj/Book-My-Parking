package com.example.bookmyparking.DeliveryOrder


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookmyparking.R

class OrderFeatureAdapter(private val featureList: List<DeliveryOrder>) :
    RecyclerView.Adapter<OrderFeatureAdapter.OrderFeatureViewHolder>() {

    class OrderFeatureViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val imgFeature: ImageView = view.findViewById(R.id.imgOrderFeature)
        val tvFeatureTitle: TextView = view.findViewById(R.id.tvFeatureTitle)
        val tvFeatureDescription: TextView = view.findViewById(R.id.tvFeatureDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderFeatureViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_delivery_order, parent, false)
        return OrderFeatureViewHolder(view)
    }

    override fun onBindViewHolder(holder: OrderFeatureViewHolder, position: Int) {
        val feature = featureList[position]
        holder.imgFeature.setImageResource(feature.imageRes)
        holder.tvFeatureTitle.text = feature.title
        holder.tvFeatureDescription.text = feature.description
    }

    override fun getItemCount() = featureList.size
}
