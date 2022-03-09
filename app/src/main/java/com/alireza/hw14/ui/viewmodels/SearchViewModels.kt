package com.alireza.hw14.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alireza.hw14.data.model.PhotoX
import com.alireza.hw14.data.repository.SerachRepository

class SearchViewModels : ViewModel() {

    val listOfSearch:LiveData<List<PhotoX>>
    val showProgres : LiveData<Boolean>
    private val searchRepository = SerachRepository()

    init {
        this.showProgres = searchRepository.showProgress
        this.listOfSearch = searchRepository.listOfSearching
    }

    fun getSearchResultFromServer(searchText:String){
        searchRepository.getDataFromServer(searchText)
    }



}