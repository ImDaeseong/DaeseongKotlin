package com.daeseong.toolbar_test

import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
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

        InitToolbar()

        InitWebView()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (webView?.canGoBack() == true) {
            webView?.goBack()
        } else {
            finish()
        }
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        try {
            //안드로이드 8.0 오레오 버전에서만 오류 발생
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        } catch (ex: Exception) {
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN
            )
        }
    }

    private fun InitToolbar() {
        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "제목"
    }

    private fun InitWebView() {
        webView = findViewById(R.id.webview1)
        webView?.settings?.javaScriptEnabled = true

        // 웹뷰안에 새 창이 뜨지 않도록 방지
        webView?.webViewClient = WebViewClient()
        webView?.webChromeClient = WebChromeClient()

        webView?.loadUrl("https://www.youtube.com")
    }
}
