package com.all6sand7s.newsifyme.di

import com.all6sand7s.newsifyme.network.NewsDataProvider
import com.all6sand7s.newsifyme.viewmodel.HomeViewModel
import dagger.Module
import dagger.Provides

/**
 * Created by adris on 2/27/2018.
 */
@Module(includes = [NetworkModule::class, CommonAndroidModule::class])
class ViewModelModule {

    @Provides
    fun provideHomeViewModel(githubDataProvider: NewsDataProvider) = HomeViewModel(githubDataProvider)
}