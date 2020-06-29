package com.benmohammad.mvvmarchnews.repository.repo.countries

import android.content.Context
import androidx.lifecycle.LiveData
import com.benmohammad.mvvmarchnews.app.AppExecutors
import com.benmohammad.mvvmarchnews.repository.db.countries.CountriesDao
import com.benmohammad.mvvmarchnews.repository.model.countries.Country
import com.benmohammad.mvvmarchnews.utils.CountryNameMapping
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CountriesRepository @Inject constructor(
    private val countriesDao: CountriesDao,
    private val context: Context,
    private val appExecutors: AppExecutors
) {

    fun getCountries(): LiveData<List<Country>> {
        getCountriesDataFromAssets()

        return countriesDao.getCountries()
    }

    private fun getCountriesDataFromAssets() {
        val list: List<String> = context.assets.list("countries")!!.asList<String>()

        val listOfCountries = ArrayList<Country>()

        for(item in list) {
            val country = Country()
            country.countryName = item
            country.displayName = (item.replace("_", " ").replace(".png", ""))
            country.countryFlagUrl = "file://android_assets/countries/$item"
            country.countryKey = CountryNameMapping.getCountryKey(item)
            listOfCountries.add(country)

        }
    }}