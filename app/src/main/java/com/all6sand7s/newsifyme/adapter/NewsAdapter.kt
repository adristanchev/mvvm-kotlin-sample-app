package com.all6sand7s.newsifyme.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.all6sand7s.newsifyme.R
import com.all6sand7s.newsifyme.model.Article
import com.squareup.picasso.Picasso
import io.reactivex.subjects.PublishSubject
import kotterknife.bindView


/**
 * Created by adris on 3/2/2018.
 */
class NewsAdapter(private val context: Context,
                  private var data: MutableList<Article> = mutableListOf()) : RecyclerView.Adapter<NewsViewHolder>() {

    private val clickSubject: PublishSubject<String> = PublishSubject.create()

    public fun getNewsClickSubject() = clickSubject.hide()

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NewsViewHolder {
        return NewsViewHolder(LayoutInflater.from(context).inflate(R.layout.view_item_news, parent, false))
    }

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: NewsViewHolder?, position: Int) {
        holder?.bindNews(data[position], context, clickSubject)
    }
}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val image: ImageView by bindView(R.id.newsImage)
    val title: TextView by bindView(R.id.titleTextView)
    val source: TextView by bindView(R.id.sourceTextView)
    val date: TextView by bindView(R.id.dateTextView)

    fun bindNews(article: Article, context: Context, clickSubject: PublishSubject<String>) {
        if (article.urlToImage != null)
            Picasso.with(context).load(article.urlToImage).error(R.drawable.image_not_availabe).into(image)
        else
            image.setImageResource(R.drawable.image_not_availabe)

        title.text = article.title
        source.text = article.source?.name ?: ""
        date.text = article.publishedAt
        itemView.setOnClickListener { clickSubject.onNext(article.url) }
    }

}
