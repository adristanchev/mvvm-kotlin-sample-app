package com.all6sand7s.newsifyme.view

import android.os.Bundle
import android.webkit.WebView
import com.all6sand7s.newsifyme.R
import kotterknife.bindView

/**
 * Created by adris on 2/27/2018.
 */
class ArticleDetailedActivity : BaseActivity() {

    companion object {
        const val URL_KEY = "url_key"
    }

    private val webView : WebView by bindView(R.id.webView)

    override fun getLayoutResId() = R.layout.activity_second

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        webView.loadUrl(intent.getStringExtra(URL_KEY))
    }

}