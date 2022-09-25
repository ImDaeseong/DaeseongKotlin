package com.daeseong.admob_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.LoadAdError

class AdView1Activity : AppCompatActivity() {

    private val tag: String = AdView1Activity::class.java.simpleName

    private var webView: WebView? = null
    private var mAdView: AdView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_view1)

        webView = findViewById<WebView>(R.id.webview1)
        webView!!.settings.javaScriptEnabled = true
        webView!!.webViewClient = WebViewClient()
        webView!!.loadUrl("https://www.youtube.com")

        initAdView()
    }

    override fun onPause() {
        super.onPause()
        mAdView!!.pause()
    }

    override fun onResume() {
        super.onResume()
        mAdView!!.resume()
    }

    override fun onDestroy() {
        super.onDestroy()
        mAdView!!.destroy()
    }

    private fun initAdView() {

        mAdView = findViewById(R.id.adView)
        //mAdView!!.adSize = AdSize.BANNER;
        //표준 배너 크기
        //BANNER 320x50
        //LARGE_BANNER 320x100
        //MEDIUM_RECTANGLE 300x250
        //FULL_BANNER 468x60
        //LEADERBOARD 728x90
        mAdView!!.visibility = View.GONE
        val adRequest = AdRequest.Builder().build()
        mAdView!!.loadAd(adRequest)
        mAdView!!.adListener = object : AdListener() {

            override fun onAdClicked() {
                super.onAdClicked()
                Log.e(tag, "onAdClicked")
            }

            override fun onAdClosed() {
                super.onAdClosed()
                Log.e(tag, "onAdClosed")
            }

            override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                super.onAdFailedToLoad(loadAdError)
                Log.e(tag, "onAdFailedToLoad")
            }

            override fun onAdImpression() {
                super.onAdImpression()
                Log.e(tag, "onAdImpression")
            }

            override fun onAdLoaded() {
                super.onAdLoaded()

                Log.e(tag, "onAdLoaded")

                mAdView!!.visibility = View.VISIBLE
            }

            override fun onAdOpened() {
                super.onAdOpened()
                Log.e(tag, "onAdOpened")
            }
        }

    }

}