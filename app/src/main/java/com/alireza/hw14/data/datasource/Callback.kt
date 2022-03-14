package com.alireza.hw14.data.datasource

interface Callback<T> {

    fun onResponse(data: T)
    fun onFail(error: Throwable)
}