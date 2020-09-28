package com.ameer.deliverylite.networking


import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

object OkHttpBuilder {
    val okHttpClient : OkHttpClient
        get() {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val builder = OkHttpClient.Builder()
            builder.addInterceptor(loggingInterceptor)
            return builder.build()
        }
}