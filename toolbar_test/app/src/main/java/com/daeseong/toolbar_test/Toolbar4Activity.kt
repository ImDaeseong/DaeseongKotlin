package com.daeseong.toolbar_test

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar


class Toolbar4Activity : AppCompatActivity() {

    private val tag: String = Toolbar4Activity::class.java.simpleName

    private var webView: WebView? = null

    private var toolbar: Toolbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_toolbar4)

        toolbar  = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar!!.title = "제목"


        webView  = findViewById<WebView>(R.id.webview1)
        webView!!.settings.javaScriptEnabled = true

        // 웹뷰안에 새 창이 뜨지 않도록 방지
        webView!!.webViewClient = WebViewClient()
        webView!!.webChromeClient = WebChromeClient()

        webView!!.loadUrl("https://www.youtube.com")
    }

    override fun onBackPressed() {
        super.onBackPressed()

        if(webView!!.canGoBack()) {
            webView!!.goBack()
        } else {
            finish()
        }
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)

        requestWindowFeature(Window.FEATURE_NO_TITLE)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
    }
}
