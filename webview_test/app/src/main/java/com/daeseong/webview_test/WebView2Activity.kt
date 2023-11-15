package com.daeseong.webview_test

import android.annotation.TargetApi
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class WebView2Activity : AppCompatActivity() {

    private val tag = WebView2Activity::class.java.simpleName

    lateinit var sTitle: String
    lateinit var context: Context
    lateinit var webView: WebView
    lateinit var progressBar: ProgressBar

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
        setContentView(R.layout.activity_web_view2)

        context = applicationContext

        progressBar = findViewById(R.id.progressBar1)

        webView = findViewById(R.id.webview1)
        webView.settings.javaScriptEnabled = true

        webView.addJavascriptInterface(WebJavaScriptInterface(this), "Android")

        webView.webViewClient = CustomWebViewClient()

        webView.webChromeClient = object : WebChromeClient() {
            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                if (newProgress == 100) {
                    progressBar.visibility = View.GONE
                } else {
                    if (progressBar.visibility == View.GONE)
                        progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }

            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                AlertDialog.Builder(view?.context!!)
                    .setTitle("알림1")
                    .setMessage(message)
                    .setPositiveButton("네") { _, _ ->
                        result?.confirm()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                AlertDialog.Builder(view?.context!!)
                    .setTitle("알림1")
                    .setMessage(message)
                    .setPositiveButton("네") { _, _ ->
                        result?.confirm()
                    }
                    .setNegativeButton("아니오") { _, _ ->
                        result?.cancel()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                Log.e(tag, "title:$title")
            }
        }

        if (isNetworkAvailable(this)) {
            webView.loadUrl("file:///android_asset/test2.html")
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

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            try {

                if (url != null) {

                    Log.e(tag,"UrlLoading1:$url")

                    if (url!!.startsWith("app://")) {
                        val intent = Intent(context.applicationContext, MainActivity::class.java)
                        startActivity(intent)
                    } else {
                        view?.loadUrl(url)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return true
        }

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            try {

                val url = request?.url.toString()

                Log.e(tag,"UrlLoading2:$url")

                if (url.startsWith("app://")) {
                    val intent = Intent(context.applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    view?.loadUrl(url)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return true
        }

        override fun onReceivedError(
            view: WebView?,
            errorCode: Int,
            description: String?,
            failingUrl: String?
        ) {
            super.onReceivedError(view, errorCode, description, failingUrl)
            progressBar.visibility = View.GONE
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            progressBar.visibility = View.GONE
            sTitle = view?.title ?: ""
            Log.e(tag,"onPageFinished:$sTitle")
        }
    }

    private inner class WebJavaScriptInterface(private val activity: Activity) {

        @JavascriptInterface
        fun Javascript_makeText(message: String) {
            Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun Javascript_finish() {
            activity.finish()
        }
    }
}
