package com.benmohammad.mvvmarchnews.ui.countries

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.benmohammad.mvvmarchnews.repository.model.countries.Country
import com.benmohammad.mvvmarchnews.repository.repo.countries.CountriesRepository
import javax.inject.Inject

class CountriesViewModel @Inject constructor(
    countriesRepository: CountriesRepository
): ViewModel() {

    private var countries: LiveData<List<Country>> = countriesRepository.getCountries()
    fun getCountries() = countries
}