package com.ameer.doordashlite.restaurants.adapters

import androidx.recyclerview.widget.DiffUtil
import com.ameer.doordashlite.restaurants.data.models.Restaurants

class RestaurantsDiffCallBack(private val oldList: List<Restaurants>, private val newList : List<Restaurants>) :
    DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size

    override fun getNewListSize(): Int = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {

        return (oldList.get(oldItemPosition).id == newList.get(newItemPosition).id)
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        if(oldList.get(oldItemPosition).description != oldList.get(oldItemPosition).description) {
            return false
        }
        if(oldList.get(oldItemPosition).cover_img_url != oldList.get(oldItemPosition).cover_img_url) {
            return false
        }
        if(oldList.get(oldItemPosition).delivery_fee != oldList.get(oldItemPosition).delivery_fee) {
            return false
        }
        if(oldList.get(oldItemPosition).name != oldList.get(oldItemPosition).name) {
            return false
        }
        if(oldList.get(oldItemPosition).status != oldList.get(oldItemPosition).status) {
            return false
        }
        return true
    }
}