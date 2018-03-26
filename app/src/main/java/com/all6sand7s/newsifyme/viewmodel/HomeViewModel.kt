package com.all6sand7s.newsifyme.viewmodel

import com.all6sand7s.newsifyme.model.News
import com.all6sand7s.newsifyme.network.NewsDataProvider
import io.reactivex.Notification
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * Created by adris on 2/27/2018.
 */
class HomeViewModel(private val newsDataProvider: NewsDataProvider) : BaseViewModel() {

    private val successPublishSubject: PublishSubject<News> = PublishSubject.create()
    private val errorPublishSubject: PublishSubject<String> = PublishSubject.create()
    private val compositeDisposable = CompositeDisposable()

    fun searchNews(query: String) {
        compositeDisposable.add(newsDataProvider.searchNews(query)
                .materialize()
                .subscribe({ notifyResultStream(it)}))
    }

    private fun notifyResultStream(it: Notification<News>) {
        when {
            it.isOnNext -> successPublishSubject.onNext(it.value)
            it.isOnError -> errorPublishSubject.onNext(it.error.localizedMessage)
        }
    }

    fun getSuccessObservable(): Observable<News> = successPublishSubject.hide()

    fun getErrorObservable(): Observable<String> = errorPublishSubject.hide()

    override fun onUnsubscribe() {
        super.onUnsubscribe()
        compositeDisposable.dispose()
    }
}