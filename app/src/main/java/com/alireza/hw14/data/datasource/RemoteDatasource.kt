package com.alireza.hw14.data.datasource

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.alireza.hw14.data.model.PhotoRepository
import com.alireza.hw14.data.model.PhotoX
import com.github.leonardoxh.livedatacalladapter.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RemoteDatasource()  : DataSource {

    // Asyncronation for using other thread
    override fun getListOfPhoto(callback: com.alireza.hw14.data.datasource.Callback<List<PhotoX>>) {

        val apiKey = "1c04e05bce6e626247758d120b372a73"
        val method = "flickr.photos.getPopular"
        val userId = "34427466731@N01"
        val extras = "url_s"
        val format = "json"
        val nojsoncallback = "1"
        val perPage = "5"
        val page = 1

        var lists:List<PhotoX>? = null
        RetrofitProvider.service.loadImages(
            apiKey,
            userId,
            method,
            extras,
            format,
            nojsoncallback,
            perPage,
            page.toString()
        ).enqueue(object : Callback<PhotoRepository> {
            override fun onResponse(call: Call<PhotoRepository>, response: Response<PhotoRepository>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    lists = response.body()!!.photos.photo
                    callback.onResponse(lists!!)
                }
            }
            override fun onFailure(call: Call<PhotoRepository>, t: Throwable) {
                t.message?.let { Log.d("Response", "onFailure$it") }
                callback.onFail(t)
            }
        })
    }
    override fun getListOfPhotoSearch( callback: com.alireza.hw14.data.datasource.Callback<List<PhotoX>>,text:String) {

        val apiKey = "1c04e05bce6e626247758d120b372a73"
        val method = "flickr.photos.Search"
        val userId = "34427466731@N01"
        val extras = "url_s"
        val format = "json"
        val nojsoncallback = "1"
        val perPage = "30"
        val page = "1"
        val tempLive = MutableLiveData<List<PhotoX>>()
        var lists:List<PhotoX>? = null
        RetrofitProvider.service.loadSearches(
            apiKey,
            userId,
            method,
            extras,
            format,
            nojsoncallback,
            perPage,
            page ,
            text
        ).enqueue(object : Callback<PhotoRepository> {
            override fun onResponse(call: Call<PhotoRepository>, response: Response<PhotoRepository>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    lists = response.body()!!.photos.photo
                    callback.onResponse(lists!!)
                }
            }
            override fun onFailure(call: Call<PhotoRepository>, t: Throwable) {
                t.message?.let { Log.d("Response", "onFailure$it") }
                callback.onFail(t)
            }
        })
    }

    override fun getListOfPhotoPag(
        callback: com.alireza.hw14.data.datasource.Callback<List<PhotoX>>,
        page: Int
    ) {
        val apiKey = "1c04e05bce6e626247758d120b372a73"
        val method = "flickr.photos.getPopular"
        val userId = "34427466731@N01"
        val extras = "url_s"
        val format = "json"
        val nojsoncallback = "1"
        val perPage = "5"

        var lists:List<PhotoX>?
        RetrofitProvider.service.loadImages(
            apiKey,
            userId,
            method,
            extras,
            format,
            nojsoncallback,
            perPage,
            page.toString()
        ).enqueue(object : Callback<PhotoRepository> {
            override fun onResponse(call: Call<PhotoRepository>, response: Response<PhotoRepository>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    Log.d("onResponse Ok " , page.toString())
                    Log.d("onResponse Ok " , response.body()!!.photos.photo.toString())
                    lists = response.body()!!.photos.photo
                    callback.onResponse(lists!!)
                }
            }
            override fun onFailure(call: Call<PhotoRepository>, t: Throwable) {
                t.message?.let { Log.d("Response", "onFailure$it") }
                callback.onFail(t)
            }
        })
    }

}