package com.benmohammad.mvvmarchnews.ui.countries

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.benmohammad.mvvmarchnews.R
import com.benmohammad.mvvmarchnews.di.base.Injectable
import com.benmohammad.mvvmarchnews.repository.model.countries.Country
import com.benmohammad.mvvmarchnews.utils.extensions.observe
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_country_list.*
import java.lang.RuntimeException
import javax.inject.Inject

class CountryListFragment: Fragment(), Injectable {


    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var columnCount = 1
    private var listener: OnCountriesListClickListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            columnCount = it.getInt(ARG_COLUMN_COUNT)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_country_list, container, false)
        val countriesViewModel = ViewModelProviders.of(this, viewModelFactory).get(CountriesViewModel::class.java)

        val listOfCountries = ArrayList<Country>()
        if(view is RecyclerView) {
            with(view) {
                layoutManager = when{
                    columnCount <= 1 -> LinearLayoutManager(context)

                    else -> GridLayoutManager(context, columnCount)
                }
                adapter = CountryListRecyclerViewAdapter(listOfCountries, listener)
            }
        }

        countriesViewModel.getCountries().observe(this) {
            listOfCountries.clear()
            listOfCountries.addAll(it)
            recyclerview_countries.adapter?.notifyDataSetChanged()
        }
        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if(context is OnCountriesListClickListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnListFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface OnCountriesListClickListener{
        fun onCountriesListClick(country: Country)
    }

    companion object{
        const val ARG_COLUMN_COUNT = "column-count"

        @JvmStatic
        fun newInstance(columnCount: Int) =
            CountryListFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_COLUMN_COUNT, columnCount)
                }
            }
        }
    }