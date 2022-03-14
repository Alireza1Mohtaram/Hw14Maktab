package com.alireza.hw14.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.alireza.hw14.R
import com.alireza.hw14.data.model.PhotoX
import com.alireza.hw14.ui.viewmodels.HomeFragmentViewModel
import com.alireza.hw14.ui.viewmodels.PhotoListViewModelFactory


class HomeFragment : Fragment(R.layout.fragment_home) {

    private val homeFragmentViewModel: HomeFragmentViewModel by viewModels(factoryProducer = {
        PhotoListViewModelFactory()
    })

    lateinit var photoList: RecyclerView
    lateinit var progress: ProgressBar
    val adapter = PhotoRecyclerAdapter()



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        photoList = view.findViewById(R.id.recyclerView)
        progress = view.findViewById(R.id.progressBar)

        homeFragmentViewModel.showProgres.observe(viewLifecycleOwner, Observer {
            if (it == true) {
                progress.visibility = View.VISIBLE
            } else {
                progress.visibility = View.GONE
            }

        })

        photoList.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {

                if(!recyclerView.canScrollVertically(1))
                    homeFragmentViewModel.getNewListItem().observe(viewLifecycleOwner) {
                        append(it) }
            }
        })
        homeFragmentViewModel.list.observe(viewLifecycleOwner, Observer {
            init(it )
        })

    }
    private fun init(it: List<PhotoX>) {
        //val adapter = PhotoRecyclerAdapter()
        adapter.setData(it)
        photoList.adapter = adapter
        photoList.Recycler().setViewCacheSize(it.size - 1)
        photoList.layoutManager = LinearLayoutManager(requireContext())

    }
    private fun append(it: List<PhotoX>) {
        adapter.appendData(it)
        photoList.adapter = adapter
      //  adapter.notifyItemInserted(it.size)
        photoList.Recycler().setViewCacheSize(it.size - 1)
        //photoList.scrollToPosition(photoList.childCount)
        photoList.layoutManager = LinearLayoutManager(requireContext())

    }


}

