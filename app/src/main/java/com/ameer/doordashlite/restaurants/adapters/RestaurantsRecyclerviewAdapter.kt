package com.ameer.doordashlite.restaurants.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.ameer.doordashlite.R
import com.ameer.doordashlite.restaurants.RecyclerViewClickListener
import com.ameer.doordashlite.restaurants.data.models.Restaurants
import kotlinx.android.synthetic.main.restaurants_nearby_item_layout.view.*

class RestaurantsRecyclerviewAdapter(private val listener : RecyclerViewClickListener) : RecyclerView.Adapter<RestaurantsRecyclerviewAdapter.ViewHolder>(){

    val diffCallback = object : DiffUtil.ItemCallback<Restaurants>() {
        override fun areItemsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Restaurants, newItem: Restaurants): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.restaurants_nearby_item_layout,parent,false)
        return ViewHolder(view,listener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        when(holder) {
            is ViewHolder -> {
                holder.bindItems(differ.currentList.get(position))
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    fun submitList(restaurantsList: List<Restaurants>) {
        differ.submitList(restaurantsList)
    }
     class ViewHolder(view: View, private val listener: RecyclerViewClickListener) : RecyclerView.ViewHolder(view) {
         fun bindItems(values: Restaurants) = with(itemView){

             itemView.setOnClickListener{
                 listener?.onItemSelected(adapterPosition,values)
             }
             var arr:Any

             arr = values.name.split("(")
             itemView.txtRestName.text = arr[0]
             if(values.description.length>45) {
                 arr = values.description.split(".")
                 if(arr[0].length>45) {
                     itemView.txtRestDesc.text = arr[0].substring(0,40)
                 }
                 else
                     itemView.txtRestDesc.text = arr[0].trim()
             }
             else
                 itemView.txtRestDesc.text = values.description
             itemView.txtRestStatus.text = values.status
             itemView.imgCover.load(values.cover_img_url) {
                 placeholder(R.drawable.door_dash)
             }

         }

    }

}

