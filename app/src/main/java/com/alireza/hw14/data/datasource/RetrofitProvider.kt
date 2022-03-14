package com.alireza.hw14.data.datasource

import com.github.leonardoxh.livedatacalladapter.LiveDataResponseBodyConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitProvider {

    private val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.flickr.com/services/")
        .addConverterFactory(GsonConverterFactory.create())
        .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
        .build()

    val service: FlickrApi = retrofit.create(FlickrApi::class.java)
}

object RetrofitProviderCallAdapter {

    private val client = OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

    private val retrofit = Retrofit.Builder()
        .client(client)
        .baseUrl("https://api.flickr.com/services/")
        .addConverterFactory(LiveDataResponseBodyConverterFactory.create())
        .build()

    val service: FlickrApi = retrofit.create(FlickrApi::class.java)
}