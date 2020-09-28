package com.ameer.doordashlite.restaurants.data.models

data class RestaurantDetail (var id: String, var name: String, var average_rating:String, var delivery_fee: String,
                             var number_of_ratings: String, var cover_img_url: String, var address: address
                             )

data class address(var lat: Double, var lng: Double)