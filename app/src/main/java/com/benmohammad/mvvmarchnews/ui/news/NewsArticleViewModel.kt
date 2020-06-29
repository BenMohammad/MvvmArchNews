package com.benmohammad.mvvmarchnews.ui.news

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.benmohammad.mvvmarchnews.repository.api.network.Resource
import com.benmohammad.mvvmarchnews.repository.model.news.NewsArticles
import com.benmohammad.mvvmarchnews.repository.repo.news.NewsRepository
import javax.inject.Inject

class NewsArticleViewModel @Inject constructor(
    private val newsRepository: NewsRepository
): ViewModel() {

    private fun newsArticles(countryKey: String): LiveData<Resource<List<NewsArticles>?>> =
        newsRepository.getNewsArticles(countryKey)

    fun getNewsArticles(countryKey: String) = newsArticles(countryKey)

    private fun newsArticlesFromOnlyServer(countryKey: String) =
        newsRepository.getNewsArticlesFromServerOnly(countryKey)

    fun getNewsArticlesFromServer(countryKey: String) = newsArticlesFromOnlyServer(countryKey)
}