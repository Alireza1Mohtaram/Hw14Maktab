package com.alireza.hw14.data.model

import com.google.gson.annotations.SerializedName

data class Photos(
    val page: Int,
    val pages: Int,
    @SerializedName("perpage")
    val perPage: Int,
    val photo: List<PhotoX>,
    val total: Int
)