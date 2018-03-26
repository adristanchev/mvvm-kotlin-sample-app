package com.all6sand7s.newsifyme

import android.app.Application
import com.all6sand7s.newsifyme.di.ApplicationComponent

/**
 * Created by adris on 2/26/2018.
 */ class NewsApplication : Application() {

    companion object {
        lateinit var graph : ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        graph = ApplicationComponent.init(this)
        graph.inject(this)
    }

    fun getObjectGraph() = graph
}
