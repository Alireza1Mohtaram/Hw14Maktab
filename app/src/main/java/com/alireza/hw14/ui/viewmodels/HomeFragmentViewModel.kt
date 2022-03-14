package com.alireza.hw14.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.alireza.hw14.data.model.PhotoX
import com.alireza.hw14.data.repository.HomeRepository

class HomeFragmentViewModel(private val homeRepository: HomeRepository) : ViewModel() {


    var list: LiveData<List<PhotoX>>
   // var listOfPag: MutableLiveData<List<PhotoX>>
    val showProgres: LiveData<Boolean>
    var page: MutableLiveData<Int> = MutableLiveData<Int>(1)


    //private val homeRepository = HomeRepository()
    // injection , edit this section

    init {
        this.showProgres = homeRepository.showProgress
        this.list = homeRepository.getPhotoList()
        //this.listOfPag = homeRepository.getPhotoListPag()

    }

    fun getNewListItem(): LiveData<List<PhotoX>> {
        page.value = page.value?.plus(1)
        return homeRepository.getPhotoListPag(page.value!!)
    }

    fun plusPage() {
        page.value = page.value?.plus(1)
        getNewListItem()
    }

}
    
    
