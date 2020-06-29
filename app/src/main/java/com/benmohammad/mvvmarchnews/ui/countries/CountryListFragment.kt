package com.benmohammad.mvvmarchnews.ui.countries

import com.benmohammad.mvvmarchnews.repository.model.countries.Country

class CountryListFragment {


    interface OnCountriesListClickListener{
        fun onCountriesListClick(country: Country)
    }
}