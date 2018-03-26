package com.all6sand7s.newsifyme.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by adris on 3/2/2018.
 */
class ApiKeyInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
                .newBuilder()
                .addHeader("x-api-key", "488d3b4fead442d08116bfe6b0ee0b7f")
                .build()
        return chain.proceed(request)
    }

}