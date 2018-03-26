package com.all6sand7s.newsifyme.network

import com.all6sand7s.newsifyme.model.News
import io.reactivex.Observable

/**
 * Created by adris on 2/26/2018.
 */
interface INewsDataProvider {

    fun searchNews(query: String) : Observable<News>
}