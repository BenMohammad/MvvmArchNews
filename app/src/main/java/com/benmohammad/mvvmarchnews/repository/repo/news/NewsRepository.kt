package com.benmohammad.mvvmarchnews.repository.repo.news

import android.content.Context
import androidx.lifecycle.LiveData
import com.benmohammad.mvvmarchnews.app.AppExecutors
import com.benmohammad.mvvmarchnews.repository.api.ApiServices
import com.benmohammad.mvvmarchnews.repository.api.NEWS_API_KEY
import com.benmohammad.mvvmarchnews.repository.api.network.NetworkAndDBBoundResource
import com.benmohammad.mvvmarchnews.repository.api.network.NetworkResource
import com.benmohammad.mvvmarchnews.repository.api.network.Resource
import com.benmohammad.mvvmarchnews.repository.db.news.NewsArticlesDao
import com.benmohammad.mvvmarchnews.repository.model.news.NewsArticles
import com.benmohammad.mvvmarchnews.repository.model.news.NewsSource
import com.benmohammad.mvvmarchnews.utils.ConnectivityUtil
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val newsDao: NewsArticlesDao,
    private val apiServices: ApiServices, private val context: Context,
    private val appExecutors: AppExecutors = AppExecutors()
) {

    fun getNewsArticles(countryShortKey: String): LiveData<Resource<List<NewsArticles>?>> {
        val data = HashMap<String, String>()
        data.put("country", countryShortKey)
        data.put("apiKey", NEWS_API_KEY)

        return object: NetworkAndDBBoundResource<List<NewsArticles>, NewsSource>(appExecutors) {
            override fun saveCallResult(item: NewsSource) {
                if(item.articles.isEmpty()) {
                    newsDao.deleteAllArticles()
                    newsDao.insertArticles(item.articles)
                }
            }

            override fun shouldFetch(data: List<NewsArticles>?): Boolean =
                (ConnectivityUtil.isConnected(context))

            override fun loadFromDb(): LiveData<List<NewsArticles>> =
                newsDao.getNewsArticles()

            override fun createCall(): LiveData<Resource<NewsSource>> =
                apiServices.getNewsSources(data)
        }.asLIveData()
    }

    fun getNewsArticlesFromServerOnly(countryShortKey: String): LiveData<Resource<NewsSource>> {
        val data = HashMap<String, String>()
        data.put("country", countryShortKey)
        data.put("apiKey", NEWS_API_KEY)

        return object: NetworkResource<NewsSource>() {
            override fun createCall(): LiveData<Resource<NewsSource>> {
                return apiServices.getNewsSources(data)
            }
        }.asLiveData()
    }
}