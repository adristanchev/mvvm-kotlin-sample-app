package com.all6sand7s.newsifyme.view

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.SearchView
import com.all6sand7s.newsifyme.R
import com.all6sand7s.newsifyme.adapter.NewsAdapter
import com.all6sand7s.newsifyme.di.ApplicationComponent
import com.all6sand7s.newsifyme.extension.showToast
import com.all6sand7s.newsifyme.model.News
import com.all6sand7s.newsifyme.viewmodel.HomeViewModel
import com.jakewharton.rxbinding2.widget.RxSearchView
import kotterknife.bindView
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class HomeActivity : BaseActivity() {
    private val searchView: SearchView by bindView(R.id.searchView)
    private val newsRecyclerView: RecyclerView by bindView(R.id.newsRecyclerView)

    @Inject
    lateinit var homeViewModel: HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        prepareViewModel()
    }

    override fun getLayoutResId() = R.layout.activity_main

    private fun prepareViewModel() {
        homeViewModel.getErrorObservable().subscribe({ showToast(it) })
        homeViewModel.getSuccessObservable().subscribe({ setupRecyclerView(it) })

        RxSearchView.queryTextChanges(searchView)
                .filter({ t -> !t.isEmpty() })
                .debounce(2, TimeUnit.SECONDS)
                .subscribe({ s -> homeViewModel.searchNews(s.toString()) })
    }

    private fun setupRecyclerView(news: News) {
        newsRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        val newsAdapter = NewsAdapter(this, news.articles)
        newsRecyclerView.adapter = newsAdapter
        newsAdapter.getNewsClickSubject().subscribe({
            val intent = Intent(this, ArticleDetailedActivity::class.java)
            intent.putExtra(ArticleDetailedActivity.URL_KEY, it)
            startActivity(intent)
        })
    }

    override fun injectIntoObjectGraph(applicationComponent: ApplicationComponent) {
        super.injectIntoObjectGraph(applicationComponent)
        applicationComponent.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        homeViewModel.onUnsubscribe()
    }

}
