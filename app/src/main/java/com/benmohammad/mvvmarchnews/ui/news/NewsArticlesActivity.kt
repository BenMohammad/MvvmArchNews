package com.benmohammad.mvvmarchnews.ui.news

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.benmohammad.mvvmarchnews.R
import com.benmohammad.mvvmarchnews.ui.BaseActivity
import com.benmohammad.mvvmarchnews.utils.ToastUtil
import com.benmohammad.mvvmarchnews.utils.extensions.getViewModel
import com.benmohammad.mvvmarchnews.utils.extensions.load
import com.benmohammad.mvvmarchnews.utils.extensions.observe
import com.benmohammad.mvvmarchnews.utils.extensions.toast
import kotlinx.android.synthetic.main.activity_news_articles.*
import kotlinx.android.synthetic.main.empty_layout_news_article.*
import kotlinx.android.synthetic.main.progress_layout_news_article.*

class NewsArticlesActivity: BaseActivity() {

    companion object {
        val KEY_COUNTRY_SHORT_KEY: String = "COUNTRY_SHORT_KEY"

    }

    private lateinit var adapter: NewsArticlesAdapter

    private val newsArticleViewModel by lazy {
        getViewModel<NewsArticleViewModel>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news_articles)

        news_list.setEmptyView(empty_view)
        news_list.setProgressView(progress_view)

        adapter = NewsArticlesAdapter {
            toast(it.description.toString())
        }
        news_list.adapter = adapter
        news_list.layoutManager = LinearLayoutManager(this)

        getNewsOfCountry(intent.getStringExtra(KEY_COUNTRY_SHORT_KEY))

    }

    fun getNewsOfCountry(countryKey: String) {
        newsArticleViewModel.getNewsArticles(countryKey).observe(this) {
            when{
                it.status.isLoading() -> {
                    news_list.showProgressView()
                }
                it.status.isSuccessful() -> {
                    it.load(news_list) {
                        it?.let {
                            adapter.replaceItems(it)
                        }
                    }
                }
                it.status.isError() -> {
                    if(it.errorMessage != null) {
                        ToastUtil.showCustomToast(this, it.errorMessage.toString())

                    }                }
            }

        }
    }}