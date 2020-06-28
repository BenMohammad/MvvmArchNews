package com.benmohammad.mvvmarchnews.repository.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.benmohammad.mvvmarchnews.repository.db.countries.CountriesDao
import com.benmohammad.mvvmarchnews.repository.db.news.NewsArticlesDao
import com.benmohammad.mvvmarchnews.repository.model.countries.Country
import com.benmohammad.mvvmarchnews.repository.model.news.NewsArticles

@Database(entities = [NewsArticles::class, Country::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun newsArticlesDao(): NewsArticlesDao

    abstract fun countriesDao(): CountriesDao
}