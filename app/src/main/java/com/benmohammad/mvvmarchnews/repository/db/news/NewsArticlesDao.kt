package com.benmohammad.mvvmarchnews.repository.db.news

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.benmohammad.mvvmarchnews.repository.model.news.NewsArticles

@Dao
interface NewsArticlesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<NewsArticles>): List<Long>

    @Query("SELECT * FROM news_table")
    fun getNewsArticles(): LiveData<List<NewsArticles>>

    @Query("DELETE FROM news_table")
    fun deleteAllArticles()
}