package com.pdguru.airlinedatabase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.pdguru.airlinedatabase.model.AirlineInfo
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_list.view.*

class MainListingAdapter(
    private val airlines: List<AirlineInfo>,
    private val onItemClick: (airline: AirlineInfo) -> Unit
) :
    RecyclerView.Adapter<MainListingAdapter.ViewHolder>() {
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    private val picasso: Picasso = Picasso.get()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val vh = ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_list, parent, false)
        )
        vh.itemView.setOnClickListener { onItemClick.invoke(airlines[vh.adapterPosition]) }
        return vh
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemView.item_label.text = airlines[position].name
        picasso.load(BASE_URL + airlines[position].logoURL)
            .placeholder(R.drawable.ic_flight_32)
            .into(holder.itemView.item_logo)
    }

    override fun getItemCount(): Int {
        return airlines.size
    }
}
