package com.daeseong.admob_test

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.LoadAdError

class AdView2Activity : AppCompatActivity() {

    private val tag: String = AdView2Activity::class.java.simpleName

    private var webView: WebView? = null
    private var interstitialAd: InterstitialAd? = null

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_view2)

        webView = findViewById<WebView>(R.id.webview1)
        webView!!.settings.javaScriptEnabled = true
        webView!!.webViewClient = WebViewClient()
        webView!!.loadUrl("https://www.youtube.com")

        initinterstitialAd()

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            showinterstitialAd()
        }
    }

    private fun initinterstitialAd() {

        interstitialAd = InterstitialAd(this)
        interstitialAd!!.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        val adRequest = AdRequest.Builder().build()
        interstitialAd!!.loadAd(adRequest)
        interstitialAd!!.adListener = object : AdListener() {

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
                Log.e(tag, "광고 로드 완료후 전면광고 자동 호출")

                if (interstitialAd!!.isLoaded) {
                    interstitialAd!!.show()
                }
            }

            override fun onAdOpened() {
                super.onAdOpened()
                Log.e(tag, "onAdOpened")
            }
        }
    }

    private fun showinterstitialAd() {

        if (interstitialAd!!.isLoaded) {

            Log.e(tag, "interstitialAd.show()")

            interstitialAd!!.show()

        } else {
            Log.e(tag, "interstitialAd.loadAd()")

            val adRequest = AdRequest.Builder().build()
            interstitialAd!!.loadAd(adRequest)
        }
    }

}