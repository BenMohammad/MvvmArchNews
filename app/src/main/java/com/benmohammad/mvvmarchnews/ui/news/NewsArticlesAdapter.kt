package com.benmohammad.mvvmarchnews.ui.news

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.benmohammad.mvvmarchnews.R
import com.benmohammad.mvvmarchnews.repository.model.news.NewsArticles
import com.benmohammad.mvvmarchnews.utils.extensions.inflate
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.row_news_article.view.*

class NewsArticlesAdapter(
    private val listener: (NewsArticles) -> Unit
): RecyclerView.Adapter<NewsArticlesAdapter.NewsHolder>() {

    private var newsArticles: List<NewsArticles> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsHolder =
        NewsHolder(parent.inflate(R.layout.row_news_article))

    override fun getItemCount(): Int = newsArticles.size

    override fun onBindViewHolder(holder: NewsHolder, position: Int) =
        holder.bind(newsArticles[position], listener)

    class NewsHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bind(newsArticle: NewsArticles, listener: (NewsArticles) -> Unit) = with(itemView) {
            tvNewsItemTitle.text = newsArticle.title
            tvNewsAuthor.text = newsArticle.author
            tvListItemDateTime.text = newsArticle.publishedAt

            Glide.with(context).load(newsArticle.urlToImage)
                .apply(
                    RequestOptions()
                        .placeholder(R.drawable.loading_banner_image)
                        .error(R.drawable.loading_banner_image)
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                )
                .into(ivNewsImage)

            setOnClickListener{listener(newsArticle)}
        }
    }

    fun replaceItems(items: List<NewsArticles>) {
        newsArticles = items
        notifyDataSetChanged()
    }
}