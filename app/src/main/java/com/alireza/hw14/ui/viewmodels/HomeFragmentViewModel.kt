package com.alireza.hw14.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.alireza.hw14.data.model.PhotoX
import com.alireza.hw14.data.repository.HomeRepository

class HomeFragmentViewModel : ViewModel(){
    

    val list:LiveData<List<PhotoX>>
    val showProgres : LiveData<Boolean>
    private val homeRepository = HomeRepository()

    init {
        this.showProgres = homeRepository.showProgress
        this.list = homeRepository.list
        homeRepository.getDataFromServer()
    }

   }
    
    
