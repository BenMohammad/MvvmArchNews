package com.benmohammad.mvvmarchnews.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.benmohammad.mvvmarchnews.di.base.ViewModelFactory
import com.benmohammad.mvvmarchnews.di.base.ViewModelKey
import com.benmohammad.mvvmarchnews.ui.countries.CountriesViewModel
import com.benmohammad.mvvmarchnews.ui.news.NewsArticleViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(NewsArticleViewModel::class)
    abstract fun bindNewsArticleViewModel(newsArticleViewModel: NewsArticleViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CountriesViewModel::class)
    abstract fun bindCountriesViewModel(countriesViewModel: CountriesViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}