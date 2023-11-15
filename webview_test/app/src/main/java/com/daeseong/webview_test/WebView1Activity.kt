package com.daeseong.webview_test

import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.*
import androidx.appcompat.app.AppCompatActivity

class WebView1Activity : AppCompatActivity() {

    private val tag: String = WebView1Activity::class.java.simpleName

    private lateinit var sTitle: String
    private lateinit var context: Context
    private lateinit var webView: WebView

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.isDefaultNetworkActive
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view1)

        context = applicationContext
        sTitle = ""

        webView = findViewById(R.id.webview1)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = CustomWebViewClient()
        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(view.context)
                    .setTitle("알림")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok) { _, _ ->
                        result.confirm()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(view.context)
                    .setTitle("알림")
                    .setMessage(message)
                    .setPositiveButton("네") { _, _ ->
                        result.confirm()
                    }
                    .setNegativeButton("아니오") { _, _ ->
                        result.cancel()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            override fun onReceivedTitle(view: WebView?, title: String) {
                super.onReceivedTitle(view, title)
                Log.e(tag, "title:$title")
            }
        }

        if (isNetworkAvailable(context)) {
            webView.loadUrl("file:///android_asset/test1.html")
        } else {
            webView.loadUrl("about:blank")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    inner class CustomWebViewClient : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            try {
                Log.d("test1.html 에서 링크 클릭시:", url)
                if (url.startsWith("app://")) {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    view.loadUrl(url)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            try {
                val url = request.url.toString()
                if (url.startsWith("app://")) {
                    val intent = Intent(context, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    view.loadUrl(url)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return true
        }

        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            super.onReceivedError(view, errorCode, description, failingUrl)
        }

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onPageFinished(view: WebView, url: String?) {
            super.onPageFinished(view, url)
            sTitle = view.title ?: ""
            Log.e(tag, "onPageFinished: $sTitle")
        }
    }
}
