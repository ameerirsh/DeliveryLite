package com.ameer.doordashlite.restaurants.repository


import com.ameer.doordashlite.networking.DoorDashApi
import com.ameer.doordashlite.restaurants.data.models.Restaurants

class RestaurantRepository(private val api: DoorDashApi) : RestaurantApiRequest() {

     suspend fun getListRestaurants(lat: String, long:String) : List<Restaurants>? {
        val restaurantsListResponse = safeApiCall(
            call = {api.getRestaurants(lat,long)},
            errorMessage = "Error fetching restaurant list"
        )
        return restaurantsListResponse
    }

    suspend fun getRestaurants(lat: String, long:String) = apiRequest { api.getRestaurants(lat,long) }

    suspend fun getRestaurantDetails(id: String) = apiRequest { api.getRestaurantDetails(id)}
}