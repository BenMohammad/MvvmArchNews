package com.benmohammad.mvvmarchnews.di.modules

import com.benmohammad.mvvmarchnews.ui.countries.CountryListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Suppress("unused")
@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    internal abstract fun contributeCountryListFragment(): CountryListFragment
}