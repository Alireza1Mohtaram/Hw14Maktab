package com.alireza.hw14.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import com.alireza.hw14.data.datasource.RemoteDatasource
import com.alireza.hw14.data.repository.HomeRepository
import com.alireza.hw14.data.repository.SearchRepository

class SearchViewModelFactory :ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        val repo = SearchRepository(RemoteDatasource())
        return SearchViewModels(repo) as T
    }

}