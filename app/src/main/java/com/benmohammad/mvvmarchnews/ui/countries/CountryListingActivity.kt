package com.benmohammad.mvvmarchnews.ui.countries

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.benmohammad.mvvmarchnews.R
import com.benmohammad.mvvmarchnews.repository.model.countries.Country
import com.benmohammad.mvvmarchnews.ui.BaseActivity
import com.benmohammad.mvvmarchnews.ui.news.NewsArticlesActivity
import com.benmohammad.mvvmarchnews.ui.news.NewsArticlesActivity.Companion.KEY_COUNTRY_SHORT_KEY

class CountryListingActivity : BaseActivity(), CountryListFragment.OnCountriesListClickListener {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_countries)
        if(savedInstanceState == null) {
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, CountryListFragment.newInstance(1)).commit()
        }

    }

    override fun onCountriesListClick(country: Country) {
        val intent = Intent(this, NewsArticlesActivity::class.java)
        intent.putExtra(KEY_COUNTRY_SHORT_KEY, country.countryKey)
        startActivity(intent)
    }
}
