package com.alireza.hw14.data.repository

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.alireza.hw14.data.datasource.Callback
import com.alireza.hw14.data.datasource.DataSource
import com.alireza.hw14.data.model.PhotoX

class SearchRepository (private val dataSourceSearch: DataSource) {

    val showProgress = MutableLiveData<Boolean>()

    fun getPhotoList(text:String) : MutableLiveData<List<PhotoX>>{
        val tempLive = MutableLiveData<List<PhotoX>>()
         dataSourceSearch.getListOfPhotoSearch(object : Callback<List<PhotoX>> {
            override fun onResponse(data: List<PhotoX>) {tempLive.postValue(data)
            showProgress.postValue(false)
            }
            override fun onFail(error: Throwable) {
                showProgress.postValue(true)

            }
        },text)
        return tempLive
    }

}