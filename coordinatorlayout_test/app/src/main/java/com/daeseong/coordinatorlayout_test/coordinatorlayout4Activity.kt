package com.daeseong.coordinatorlayout_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient

class coordinatorlayout4Activity : AppCompatActivity() {

    private lateinit var web1: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinatorlayout4)

        web1 = findViewById(R.id.web1)
        web1.settings.javaScriptEnabled = true
        web1.webViewClient = WebViewClient()
        web1.loadUrl("https://m.naver.com/")
    }
}