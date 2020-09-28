package com.ameer.doordashlite.restaurants

import android.view.View
import com.ameer.doordashlite.restaurants.data.models.Restaurants
import com.ameer.doordashlite.restaurants.data.models.PopularItems

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, restaurants: Restaurants)
    fun onItemSelected(position: Int, item: Restaurants)
    fun onItemSelected(position: Int, item: PopularItems)
}