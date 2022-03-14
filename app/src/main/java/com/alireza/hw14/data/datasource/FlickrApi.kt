package com.alireza.hw14.data.datasource

import androidx.lifecycle.LiveData
import com.alireza.hw14.data.model.PhotoRepository
import com.alireza.hw14.data.model.PhotoX
import com.github.leonardoxh.livedatacalladapter.Resource
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface  FlickrApi {
    @GET("rest")
    fun loadImages(@Query("api_key") api_key:String ,
                   @Query("user_id") user_id:String ,
                   @Query("method") method:String ,
                   @Query("extras") extras:String ,
                   @Query("format") format:String ,
                   @Query("nojsoncallback") nojsoncallback:String ,
                   @Query("per_page") per_page:String ,
                   @Query("page") page:String ):Call<PhotoRepository>
    @GET("rest")
    fun loadSearches(@Query("api_key") api_key:String ,
                   @Query("user_id") user_id:String ,
                   @Query("method") method:String ,
                   @Query("extras") extras:String ,
                   @Query("format") format:String ,
                   @Query("nojsoncallback") nojsoncallback:String ,
                   @Query("per_page") per_page:String ,
                   @Query("page") page:String ,
                   @Query("text") text:String ): Call<PhotoRepository>




}