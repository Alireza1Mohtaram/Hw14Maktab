package com.alireza.hw14.ui.viewmodels

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alireza.hw14.data.model.PhotoX
import com.alireza.hw14.data.repository.SearchRepository

class SearchViewModels (private val searchRepository : SearchRepository  ): ViewModel() {

    val showProgres : LiveData<Boolean>

    init {
        this.showProgres = searchRepository.showProgress
    }
    fun getListOfPhotoSearch(text:String): LiveData<List<PhotoX>> {
        return searchRepository.getPhotoList(text)
    }
}