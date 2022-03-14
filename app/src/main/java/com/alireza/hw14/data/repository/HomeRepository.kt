package com.alireza.hw14.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.alireza.hw14.data.datasource.DataSource
import com.alireza.hw14.data.model.PhotoX

class HomeRepository (private val dataSource:DataSource){

     val showProgress = MutableLiveData<Boolean>()

     fun getPhotoList() : MutableLiveData<List<PhotoX>>{
          val photoListLiveData = MutableLiveData<List<PhotoX>>()
          dataSource.getListOfPhoto(object : com.alireza.hw14.data.datasource.Callback<List<PhotoX>> {
               override fun onResponse(data: List<PhotoX>) {
                    photoListLiveData.postValue(data)
                    showProgress.value = false
               }
               override fun onFail(error: Throwable) {
                    showProgress.value = true
               }
          })
          return photoListLiveData
     }
     fun getPhotoListPag(page:Int) : MutableLiveData<List<PhotoX>>{
          val photoListLiveData = MutableLiveData<List<PhotoX>>()
          dataSource.getListOfPhotoPag(object : com.alireza.hw14.data.datasource.Callback<List<PhotoX>> {
               override fun onResponse(data: List<PhotoX>) {
                    photoListLiveData.postValue(data)
                    showProgress.value = false
               }
               override fun onFail(error: Throwable) {
                    showProgress.value = true
               }
          },page)
          return photoListLiveData
     }

}