package com.alireza.hw14.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alireza.hw14.R
import com.alireza.hw14.data.model.PhotoX
import com.alireza.hw14.ui.viewmodels.SearchViewModelFactory
import com.alireza.hw14.ui.viewmodels.SearchViewModels

class SearchFragment : Fragment(R.layout.fragment_search) {


    private val searchVM:SearchViewModels by viewModels(factoryProducer = {
        SearchViewModelFactory()
    })
    lateinit var photoList :RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var searchView: SearchView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoList = view.findViewById(R.id.recyclerView2)
        progressBar = view.findViewById(R.id.progressBar2)
        searchView = view.findViewById(R.id.searchView)

        initFirstListData()


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                searchVM.getListOfPhotoSearch(searchView.query.toString()).observe(viewLifecycleOwner,
                    Observer {
                        setListData(it)
                    })
                return true
            }

            override fun onQueryTextChange(p0: String?): Boolean {
            return true
            }
        })

        searchVM.showProgres.observe(viewLifecycleOwner, Observer {
            if (it == true) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })

    }
    fun setListData(it : List<PhotoX>){
        val adapter = PhotoRecyclerAdapter()
        adapter.setData(it)
        photoList.adapter = adapter
        photoList.Recycler().setViewCacheSize(it.size - 1)
        photoList.layoutManager = LinearLayoutManager(requireContext())
    }
    fun initFirstListData(){
        searchVM.getListOfPhotoSearch("").observe(viewLifecycleOwner,
            Observer {
                setListData(it)
            })

    }



    }
