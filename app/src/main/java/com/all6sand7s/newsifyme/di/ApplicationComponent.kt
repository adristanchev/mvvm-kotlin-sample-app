package com.all6sand7s.newsifyme.di

import com.all6sand7s.newsifyme.BuildConfig
import com.all6sand7s.newsifyme.NewsApplication
import com.all6sand7s.newsifyme.view.BaseActivity
import com.all6sand7s.newsifyme.view.HomeActivity
import dagger.Component
import javax.inject.Singleton

/**
 * Created by adris on 2/26/2018.
 */
@Singleton
@Component(modules = arrayOf(CommonAndroidModule::class, NetworkModule::class, ViewModelModule::class))
interface ApplicationComponent {
    companion object {
        fun init(application: NewsApplication) : ApplicationComponent {
            return DaggerApplicationComponent.builder()
                    .networkModule(NetworkModule(BuildConfig.BASE_URL))
                    .commonAndroidModule(CommonAndroidModule(application.applicationContext))
                    .viewModelModule(ViewModelModule())
                    .build()
        }
    }

    fun inject(newsApplication: NewsApplication)
    fun inject(activity: HomeActivity)
    fun inject(baseActivity: BaseActivity)
}