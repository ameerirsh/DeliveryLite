package com.ameer.deliverylite.restaurants.data.models

data class Restaurants(var id: String, var name: String, var description: String, var cover_img_url: String, var status: String, var delivery_fee: Double, var menus: List<Menus>?)

data class Menus(var popular_items: List<PopularItems>?, var is_catering: Boolean, var subtitle: String, var id: String, var name: String)

data class PopularItems(var price: Double?, var description: String?, var img_url: String?, var id: String?, var name: String?)




