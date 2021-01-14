package com.technoficials.browser

import android.app.Activity
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.os.Handler
import android.view.View
import android.view.animation.AnimationUtils
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var mAdView : AdView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        web_browser.loadUrl("http://www.google.com")
        web_browser.settings.javaScriptEnabled = true
        web_browser.canGoBack()
        web_browser.webViewClient = WebClient(this)
        search_btn.setOnClickListener {
            val URL = url_text.text.toString()
            web_browser.loadUrl(URL)
        }

        back_btn.setOnClickListener{
            web_browser.goBack()
        }

        MobileAds.initialize(this) {}

        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)
    }

    class WebClient internal constructor(private val activity: Activity):WebViewClient(){
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
        ): Boolean {
            // return super.shouldOverrideUrlLoading(view, request)
            view?.loadUrl(request?.url.toString())
            return true
        }
    }
}