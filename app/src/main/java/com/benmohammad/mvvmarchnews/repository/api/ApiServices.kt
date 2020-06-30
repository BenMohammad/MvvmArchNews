package com.benmohammad.mvvmarchnews.repository.api

import androidx.lifecycle.LiveData
import com.benmohammad.mvvmarchnews.repository.api.network.Resource
import com.benmohammad.mvvmarchnews.repository.model.news.NewsSource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.QueryMap


const val NEWS_API_KEY = "28679d41d4454bffaf6a4f40d4b024cc"

interface ApiServices {



    @GET("top-headlines")
    fun getNewsSource(@QueryMap options: Map<String, String>): LiveData<Resource<NewsSource>>


    /**
     * Fetch news articles from Google news using GET API Call on given Url
     * Using Call, By Retrofit
     */
    @GET("top-headlines?sources=google-news&apiKey=" + NEWS_API_KEY)
    fun getNewsSourceUsingCall(): Call<NewsSource>

}