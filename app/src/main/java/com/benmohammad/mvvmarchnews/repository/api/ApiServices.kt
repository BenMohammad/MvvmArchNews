package com.benmohammad.mvvmarchnews.repository.api

import androidx.lifecycle.LiveData
import com.benmohammad.mvvmarchnews.repository.api.network.Resource
import com.benmohammad.mvvmarchnews.repository.model.news.NewsSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap

const val NEWS_API_KEY = "API KEY"

interface ApiServices {



    @GET("top-headlines")
    fun getNewsSources(@QueryMap options: Map<String, String>): LiveData<Resource<NewsSource>>

    @GET("top-headlines?sources=google-news&apiKey=" + NEWS_API_KEY)
    fun getNewsSourceUsingCall(): Call<NewsSource>

}