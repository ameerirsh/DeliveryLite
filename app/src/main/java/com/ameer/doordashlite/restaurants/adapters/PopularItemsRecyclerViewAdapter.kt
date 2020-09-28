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
import com.ameer.doordashlite.restaurants.data.models.PopularItems
import kotlinx.android.synthetic.main.popular_items_item_layout.view.*
import kotlinx.android.synthetic.main.restaurants_nearby_item_layout.view.imgCover


class PopularItemsRecyclerViewAdapter(private val listener : RecyclerViewClickListener) : RecyclerView.Adapter<PopularItemsRecyclerViewAdapter.ViewHolder>(){

    val diffCallback = object : DiffUtil.ItemCallback<PopularItems>() {
        override fun areItemsTheSame(oldItem: PopularItems, newItem: PopularItems): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PopularItems, newItem: PopularItems): Boolean {
            return oldItem == newItem
        }
    }
    private val differ = AsyncListDiffer(this, diffCallback)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.popular_items_item_layout,parent,false)
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

    fun submitList(popularItemsList: List<PopularItems>?) {

        differ.submitList(popularItemsList!!)

    }
    class ViewHolder(view: View, private val listener: RecyclerViewClickListener) : RecyclerView.ViewHolder(view) {
        fun bindItems(values: PopularItems) = with(itemView){

            itemView.setOnClickListener{
                listener?.onItemSelected(adapterPosition,values)
            }
            itemView.txtItemPrice.text = "$ "+((values.price!!/100).toString())
            itemView.txtItemName.text = values.name
            itemView.imgCover.load(values.img_url) {
                placeholder(R.drawable.door_dash)
            }
        }

    }

}

