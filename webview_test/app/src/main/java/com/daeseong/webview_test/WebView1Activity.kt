package com.daeseong.webview_test

import android.annotation.TargetApi
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class WebView1Activity : AppCompatActivity() {

    private val tag = WebView1Activity::class.java.simpleName

    lateinit var sTitle: String
    lateinit var context: Context
    lateinit var webView: WebView

    companion object {
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view1)

        context = applicationContext

        webView = findViewById(R.id.webview1)
        webView.settings.javaScriptEnabled = true

        webView.webViewClient = CustomWebViewClient()

        webView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(view.context)
                    .setTitle("알림")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok) { dialog, which ->
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
                    .setPositiveButton("네") { dialog, which ->
                        result.confirm()
                    }
                    .setNegativeButton("아니오") { dialog, which ->
                        result.cancel()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                Log.e(tag, "title:$title")
            }
        }

        if (isNetworkAvailable(this)) {
            webView.loadUrl("file:///android_asset/test1.html")
        } else {
            webView.loadUrl("about:blank")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private inner class CustomWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            Log.d("test1.html 에서 링크 클릭시:", url)

            try {
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
            Log.d("test1.html 에서 링크 클릭시:", request.url.toString())

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

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            sTitle = view?.title ?: ""
            Log.d("onPageFinished", sTitle)
        }
    }
}
