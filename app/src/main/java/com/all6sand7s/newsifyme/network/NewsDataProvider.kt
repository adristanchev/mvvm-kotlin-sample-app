package com.all6sand7s.newsifyme.network

import com.all6sand7s.newsifyme.model.News
import com.squareup.otto.Bus
import io.reactivex.Observable

/**
 * Created by adris on 2/26/2018.
 */
class NewsDataProvider(val bus: Bus, val githubApi: NewsApi) : BaseDataProvider(), INewsDataProvider {
    override fun searchNews(query: String): Observable<News> {
        val searchReposObservable = githubApi.searchNews(query)
        return getObservableTransformerTemplate(searchReposObservable)
    }

}


