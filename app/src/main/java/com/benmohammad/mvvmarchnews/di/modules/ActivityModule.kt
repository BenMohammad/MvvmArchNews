package com.benmohammad.mvvmarchnews.di.modules

import com.benmohammad.mvvmarchnews.ui.countries.CountryListingActivity
import com.benmohammad.mvvmarchnews.ui.news.NewsArticlesActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module(includes = [FragmentModule::class])
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract fun contributeNewsArticleActivity(): NewsArticlesActivity

    @ContributesAndroidInjector
    abstract fun contributeCountryListingActivity(): CountryListingActivity
}