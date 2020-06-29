package com.benmohammad.mvvmarchnews.ui.countries

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.benmohammad.mvvmarchnews.R
import com.benmohammad.mvvmarchnews.repository.model.countries.Country
import com.benmohammad.mvvmarchnews.ui.countries.CountryListFragment.OnCountriesListClickListener
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_country_list.view.*

class CountryListRecyclerViewAdapter(
    private val mValues: List<Country>,
    private val mListener: OnCountriesListClickListener
): RecyclerView.Adapter<CountryListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener {
            v ->
            val country  = v.tag as Country

            mListener?.onCountriesListClick(country)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_country_list, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = mValues.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val country = mValues[position]
        holder.tv_country_name.text = country.displayName

        Glide.with(holder.mView.context).load(Uri.parse(country.countryFlagUrl))
            .apply (
                RequestOptions()
                    .placeholder(R.drawable.loading_banner_image)
                    .error(R.drawable.loading_banner_image)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
            )
            .into(holder.iv_country_image)

        with(holder.mView) {
            tag = country
            setOnClickListener(mOnClickListener)
        }

    }

    inner class ViewHolder(val mView: View): RecyclerView.ViewHolder(mView) {
        val iv_country_image: ImageView = mView.iv_country_image
        val tv_country_name: TextView = mView.tv_country_name

        override fun toString(): String {
            return super.toString() + " '" + tv_country_name.text + "'"
        }
    }


}