package com.alireza.hw14.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.alireza.hw14.R
import com.alireza.hw14.ui.viewmodels.HomeFragmentViewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels()

    lateinit var photoList:RecyclerView
    lateinit var progress:ProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoList = view.findViewById(R.id.recyclerView)
        progress = view.findViewById(R.id.progressBar)

        homeFragmentViewModel.showProgres.observe(viewLifecycleOwner, Observer {
            if (it == true) progress.visibility = View.VISIBLE
            else progress.visibility = View.GONE
        })
        homeFragmentViewModel.list.observe(viewLifecycleOwner, Observer {
            photoList.adapter =  PhotoRecyclerAdapter(it)
            photoList.layoutManager = LinearLayoutManager(requireContext())
        })
    }

}