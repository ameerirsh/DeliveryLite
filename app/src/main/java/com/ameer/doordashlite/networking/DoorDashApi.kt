package com.ameer.doordashlite.networking

import com.ameer.doordashlite.restaurants.data.models.RestaurantDetail
import com.ameer.doordashlite.restaurants.data.models.Restaurants
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface DoorDashApi {

    @GET("restaurant")
    suspend fun getRestaurants(@Query("lat") lat:String, @Query("lng") long:String): Response<List<Restaurants>>

    @GET("restaurant/{id}/")
    suspend fun getRestaurantDetails(@Path(value="id", encoded = true) id: String): Response<RestaurantDetail>

    companion object {

        operator fun invoke() : DoorDashApi {
            var gson: Gson = GsonBuilder().setLenient().create()
            val okHttpClient = OkHttpBuilder.okHttpClient
            var url = "https://api.doordash.com/v2/"
            return Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(DoorDashApi::class.java)
        }
    }
}