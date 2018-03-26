package com.all6sand7s.newsifyme.network

import com.all6sand7s.newsifyme.model.News
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by adris on 2/26/2018.
 */
interface NewsApi {

    @GET("everything?language=en&sort=date&pagesize=50")
    fun searchNews(@Query("q") query : String) : Observable<News>
}