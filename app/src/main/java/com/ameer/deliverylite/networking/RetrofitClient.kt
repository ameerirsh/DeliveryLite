package com.ameer.deliverylite.networking

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
    fun getRetrofitInstance(url: String) : Retrofit {
        var gson: Gson = GsonBuilder().setLenient().create()
        val okHttpClient = OkHttpBuilder.okHttpClient
        return Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }
}