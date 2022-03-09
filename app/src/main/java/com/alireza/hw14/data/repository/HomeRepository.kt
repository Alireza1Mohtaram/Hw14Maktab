package com.alireza.hw14.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alireza.hw14.data.datasource.RetrofitDataSource
import com.alireza.hw14.data.model.PhotoRepository
import com.alireza.hw14.data.model.PhotoX
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeRepository (){

     val list = MutableLiveData<List<PhotoX>>()
     val showProgress = MutableLiveData<Boolean>()


      fun getDataFromServer() : List<PhotoX>? {

          val apiKey = "1c04e05bce6e626247758d120b372a73"
          val method = "flickr.photos.getPopular"
          val userId = "34427466731@N01"
          val extras = "url_s"
          val format = "json"
          val nojsoncallback = "1"
          val perPage = "100"
          val page = "1"

          var lists:List<PhotoX>? = null
          RetrofitDataSource.service.loadImages(
               apiKey,
               userId,
               method,
               extras,
               format,
               nojsoncallback,
               perPage,
               page
          ).enqueue(object : Callback<PhotoRepository> {
               override fun onResponse(call: Call<PhotoRepository>, response: Response<PhotoRepository>
               ) {
                    if (response.isSuccessful && response.body() != null) {
                         showProgress.value = false
                         lists = response.body()!!.photos.photo
                         list.postValue(lists!!)
                    }
               }

               override fun onFailure(call: Call<PhotoRepository>, t: Throwable) {
                    t.message?.let { Log.d("Response", "onFailure$it") }
                    showProgress.value = true

               }

          })

          return lists
     }
}