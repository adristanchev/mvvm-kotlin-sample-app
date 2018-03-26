package com.all6sand7s.newsifyme.di

import android.content.Context
import android.content.res.AssetManager
import android.os.Handler
import android.os.Looper
import com.squareup.otto.Bus
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * Created by adris on 2/26/2018.
 */
@Module
class CommonAndroidModule(private val context: Context) {

    @Provides
    fun applicationContext(): Context {
        return context
    }

    @Provides
    fun uiHanlder(): Handler {
        return Handler(Looper.getMainLooper())
    }

//    @Provides
//    internal fun provideSharedPreferences(context: Context): SharedPreferences {
//        return context.getSharedPreferences(Constant.Application.SHARED_PREFERENCES, Context.MODE_PRIVATE)
//    }

    @Provides
    fun assets(): AssetManager {
        return context.assets
    }

//    @Singleton
//    @Provides
//    internal fun provideBus(): Bus {
//        return Bus()
//    }

}