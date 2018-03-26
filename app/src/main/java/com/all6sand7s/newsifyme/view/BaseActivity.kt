package com.all6sand7s.newsifyme.view

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import com.all6sand7s.newsifyme.NewsApplication
import com.all6sand7s.newsifyme.di.ApplicationComponent

/**
 * Created by adris on 2/27/2018.
 */
abstract class BaseActivity : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutResId() : Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResId())

        val objectGraph = (this.applicationContext as NewsApplication).getObjectGraph()
        objectGraph.inject(this)
        injectIntoObjectGraph(objectGraph)
    }

    open fun injectIntoObjectGraph(applicationComponent: ApplicationComponent) {
        // implementation looks like this applicationComponent.inject(context)

    }


}