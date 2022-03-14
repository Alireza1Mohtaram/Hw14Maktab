package com.alireza.hw14.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.alireza.hw14.R
import com.alireza.hw14.data.model.PhotoX
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso

class PhotoRecyclerAdapter() :
    RecyclerView.Adapter<PhotoRecyclerAdapter.PhotoVM>() {

    private var photos = emptyList<PhotoX>()


    class PhotoVM(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPoster: ImageView = itemView.findViewById(R.id.img_photo)
        var textDesc : TextView= itemView.findViewById(R.id.text_photo)

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoVM {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val item = inflater.inflate(R.layout.photo_item, parent, false)
        return PhotoVM(item)
    }

    override fun onBindViewHolder(holder: PhotoVM, position: Int) {
        val photo = photos[position]
        loadImageWithLink(holder.imgPoster, photo)
        holder.textDesc.text = photo.title

    }
    override fun getItemCount(): Int {
        return photos.size
    }
    private fun loadImage(imageView: ImageView, photoX: PhotoX) {
            Picasso.get()
                .load("https://live.staticflickr.com/${photoX.server}/${photoX.id}_${photoX.secret}.jpg")
                .into(imageView);
    }
    private fun loadImageWithLink(imageView: ImageView, photoX: PhotoX) {
        Glide.with(imageView.context)
            .load(photoX.url_s)
            .into(imageView)
            

    }
    fun setData(listOfPhoto : List<PhotoX>){
        this.photos = listOfPhoto
        this.notifyItemInserted(itemCount)
    }

    fun appendData(it: List<PhotoX>) {
        (photos as MutableList).addAll(it)
        this.notifyItemInserted(itemCount)
    }


}