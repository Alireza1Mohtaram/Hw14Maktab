package com.alireza.hw14.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alireza.hw14.R
import com.alireza.hw14.ui.viewmodels.SearchViewModels

class SearchFragment : Fragment() {


    private val searchVM:SearchViewModels by viewModels()
    lateinit var photoList :RecyclerView
    lateinit var progressBar: ProgressBar
    lateinit var searchView: SearchView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        photoList = view.findViewById(R.id.recyclerView2)
        progressBar = view.findViewById(R.id.progressBar)
        searchView = view.findViewById(R.id.searchView)

        searchView.setOnSearchClickListener {
            searchVM.getSearchResultFromServer(searchView.query.toString())
        }

        searchVM.showProgres.observe(viewLifecycleOwner, Observer {
            if (it == true) progressBar.visibility = View.VISIBLE
            else progressBar.visibility = View.GONE
        })
        searchVM.listOfSearch.observe(viewLifecycleOwner, Observer {
            photoList.adapter =  PhotoRecyclerAdapter(it)
            photoList.layoutManager = LinearLayoutManager(requireContext())
        })
    }



    }
