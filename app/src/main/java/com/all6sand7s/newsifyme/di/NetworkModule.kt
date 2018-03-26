package com.all6sand7s.newsifyme.di

import android.content.Context
import com.all6sand7s.newsifyme.BuildConfig
import com.all6sand7s.newsifyme.network.ApiKeyInterceptor
import com.all6sand7s.newsifyme.network.NewsApi
import com.all6sand7s.newsifyme.network.NewsDataProvider
import com.google.gson.Gson
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Created by adris on 2/26/2018.
 */
@Module(includes = [CommonAndroidModule::class])
class NetworkModule(val baseUrl: String) {

    @Provides
    fun provideLogginInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        if (BuildConfig.DEBUG) {
            interceptor.level = HttpLoggingInterceptor.Level.BODY
        } else {
            interceptor.level = HttpLoggingInterceptor.Level.NONE
        }
        return interceptor
    }

    @Provides
    @Singleton
    fun provideCache(context: Context): Cache {
        val cacheSize : Long = 20 * 1024 * 1024 // 20 MB
        return Cache(context.cacheDir, cacheSize)
    }

    @Provides
    fun provideClient(apiKeyInterceptor : ApiKeyInterceptor, interceptor: HttpLoggingInterceptor, cache: Cache): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor(apiKeyInterceptor)
                .addInterceptor(interceptor)
                .cache(cache)
                .build()
    }

    @Provides
    @Singleton
    fun provideScoresDataProvider(api: NewsApi, bus: Bus): NewsDataProvider {
        val provider = NewsDataProvider(bus, api)
        provider.attachToBus(bus)
        return provider
    }

    @Provides
    fun provideApiKeyInterceptor() : ApiKeyInterceptor {
        return ApiKeyInterceptor()
    }

    @Provides
    fun provideGson(): Gson {
        return Gson()
    }

    @Provides
    fun provideBus(): Bus {
        return Bus()
    }

    @Provides
    fun provideNewsApi(retrofit: Retrofit): NewsApi {
        return retrofit.create(NewsApi::class.java)
    }

    @Provides
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(okHttpClient)
                .build()
    }
}