package com.alireza.hw14.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.alireza.hw14.data.datasource.RemoteDatasource
import com.alireza.hw14.data.repository.HomeRepository

class PhotoListViewModelFactory :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repo = HomeRepository(RemoteDatasource())
        return HomeFragmentViewModel(repo) as T
    }

}