package com.benmohammad.mvvmarchnews.di.modules

import android.content.Context
import android.content.res.Resources
import androidx.room.Room
import com.benmohammad.mvvmarchnews.app.App
import com.benmohammad.mvvmarchnews.repository.api.ApiServices
import com.benmohammad.mvvmarchnews.repository.api.network.LiveDataCallAdapter
import com.benmohammad.mvvmarchnews.repository.api.network.LiveDataCallAdapterFactoryForRetrofit
import com.benmohammad.mvvmarchnews.repository.db.AppDatabase
import com.benmohammad.mvvmarchnews.repository.db.countries.CountriesDao
import com.benmohammad.mvvmarchnews.repository.db.news.NewsArticlesDao
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module(includes = [PreferencesModule::class, ActivityModule::class, ViewModelModule::class])
class AppModule {

    companion object {
        private const val BASE_URL = "https://newsapi.org/v2/"
    }

    @Singleton
    @Provides
    fun provideNewsService(): ApiServices {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactoryForRetrofit())
            .build()
            .create(ApiServices::class.java)
    }

    @Singleton
    @Provides
    fun provideDB(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "news-db").build()

    }
    @Singleton
    @Provides
    fun provideUserDao(db: AppDatabase): NewsArticlesDao {
        return db.newsArticlesDao()
    }

    @Singleton
    @Provides
    fun provideCountriesDao(db: AppDatabase): CountriesDao {
        return db.countriesDao()
    }


    @Singleton
    @Provides
    fun provideContext(application: App): Context {
        return application.applicationContext
    }

    @Provides
    @Singleton
    fun provideResources(application: App): Resources = application.resources
}