package com.alireza.hw14.data.datasource

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.alireza.hw14.data.model.PhotoX

interface DataSource {
    fun getListOfPhoto(callback: Callback<List<PhotoX>>)
    fun getListOfPhotoSearch(callback: Callback<List<PhotoX>> , text: String)
    fun getListOfPhotoPag(callback: Callback<List<PhotoX>>, page: Int)
}

// whats Best solutions ?