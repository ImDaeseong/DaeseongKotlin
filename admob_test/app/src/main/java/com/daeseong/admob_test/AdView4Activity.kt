package com.daeseong.admob_test

import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.rewarded.RewardItem
import com.google.android.gms.ads.rewarded.RewardedAd
import com.google.android.gms.ads.rewarded.RewardedAdCallback
import com.google.android.gms.ads.rewarded.RewardedAdLoadCallback

class AdView4Activity : AppCompatActivity() {

    private val tag: String = AdView4Activity::class.java.simpleName

    private var webView: WebView? = null
    private var rewardedAd: RewardedAd? = null//RewardedVideoAd 는 더이상 사용되지 않고 RewardedAd 변경, AdView3Activity과 동일함

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_view4)

        webView = findViewById<WebView>(R.id.webview1)
        webView!!.settings.javaScriptEnabled = true
        webView!!.webViewClient = WebViewClient()
        webView!!.loadUrl("https://www.youtube.com")

        initrewardedAd()

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            showrewardedAd()
        }
    }

    private fun initrewardedAd() {

        rewardedAd = RewardedAd(this, "ca-app-pub-3940256099942544/5224354917")
        val adRequest = AdRequest.Builder().build()

        rewardedAd!!.loadAd(adRequest, object : RewardedAdLoadCallback() {
            override fun onRewardedAdLoaded() {
                super.onRewardedAdLoaded()
                Log.e(tag, "onRewardedAdLoaded")
            }

            override fun onRewardedAdFailedToLoad(loadAdError: LoadAdError) {
                super.onRewardedAdFailedToLoad(loadAdError)
                Log.e(tag, "onRewardedAdFailedToLoad")
            }
        })
    }

    private fun showrewardedAd() {

        if (rewardedAd!!.isLoaded) {

            val rewardedAdCallback: RewardedAdCallback = object : RewardedAdCallback() {

                override fun onUserEarnedReward(rewardItem: RewardItem) {
                    Log.e(tag, "onUserEarnedReward")

                    //보상타입
                    val sType = rewardItem.type

                    //보상수량
                    val nCount = rewardItem.amount

                    Log.e(tag, "보상타입:$sType 보상수량:$nCount")
                }

                override fun onRewardedAdOpened() {
                    super.onRewardedAdOpened()
                    Log.e(tag, "onRewardedAdOpened")
                }

                override fun onRewardedAdClosed() {
                    super.onRewardedAdClosed()
                    Log.e(tag, "onRewardedAdClosed")
                }

                override fun onRewardedAdFailedToShow(adError: AdError) {
                    super.onRewardedAdFailedToShow(adError)
                    Log.e(tag, "onRewardedAdFailedToShow")
                }
            }
            rewardedAd!!.show(this, rewardedAdCallback)
        }
    }


}