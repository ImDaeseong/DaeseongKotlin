package com.daeseong.webview_test

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class WebView2Activity : AppCompatActivity() {

    private val tag: String = WebView2Activity::class.java.simpleName

    private var sTitle: String? = null
    private var context: Context? = null
    private var webView: WebView? = null
    private var progressBar: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view2)

        context = applicationContext

        progressBar = findViewById<ProgressBar>(R.id.progressBar1)

        webView = findViewById<WebView>(R.id.webview2)
        webView!!.settings.javaScriptEnabled = true//웹뷰에서 자바스크립트 실행 가능


        //webView!!.setBackgroundColor(0)
        //webView!!.isHorizontalScrollBarEnabled = false
        //webView!!.isVerticalScrollBarEnabled = false

        //inner class
        webView!!.addJavascriptInterface(webJavaScriptInterfaceIn(), "Android")

        //외북 class
        //webView!!.addJavascriptInterface(webJavaScriptInterface(this, webView), "Android")


        webView!!.webViewClient = CustomWebViewClient()

        //웹뷰에서 자바스크립트 alert과 confirm 이 동작하게 처리
        webView!!.webChromeClient = object : WebChromeClient() {

            override fun onProgressChanged(view: WebView, newProgress: Int) {

                if (newProgress == 100) {

                    progressBar!!.visibility = View.GONE

                } else {

                    if (progressBar!!.visibility == View.GONE)
                        progressBar!!.visibility = View.VISIBLE

                    progressBar!!.progress = newProgress
                }
                super.onProgressChanged(view, newProgress)
            }

            //alert 처리
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {

                AlertDialog.Builder(view.context)
                    .setTitle("알림1")
                    .setMessage(message)
                    .setPositiveButton(
                        "네"
                    ) { _, _ ->
                        result.confirm()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
                //return super.onJsAlert(view, url, message, result);
            }

            //confirm 처리
            override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {

                AlertDialog.Builder(view.context)
                    .setTitle("알림1")
                    .setMessage(message)
                    .setPositiveButton(
                        "네"
                    ) { _, _ ->
                        result.confirm()
                    }
                    .setNegativeButton(
                        "아니오"
                    ) { _, _ ->
                        result.cancel()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
                //return super.onJsConfirm(view, url, message, result);
            }
        }

        //네트워크 연결 여부
        if(IsConnect()){
            webView!!.loadUrl("file:///android_asset/test2.html")
        }else {
            webView!!.loadUrl("about:blank")
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {

        //웹뷰에서 백버튼 클릭시
        if (keyCode == KeyEvent.KEYCODE_BACK && webView!!.canGoBack()) {
            webView!!.goBack()
            return true
        }
        return super.onKeyDown(keyCode, event)
    }

    private fun IsConnect(): Boolean {
        var bConnected = false
        try {
            val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo = connectivityManager.activeNetworkInfo
            if (networkInfo != null && networkInfo.isConnected) bConnected = true
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return bConnected
    }

    inner class CustomWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            try {

                Log.d(tag, "shouldOverrideUrlLoading:$url")

                if (url.startsWith("app://")) {
                    val intent = Intent(context!!.applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    view.loadUrl(url)
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
            return true
            //return super.shouldOverrideUrlLoading(view, url);
        }

        override fun onPageFinished(view: WebView, url: String) {
            super.onPageFinished(view, url)

            progressBar!!.visibility = View.GONE

            sTitle = view.title
            Log.d(tag, "onPageFinished:$sTitle")
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            progressBar!!.setVisibility(View.VISIBLE)
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            super.onReceivedError(view, request, error)

            progressBar!!.visibility = View.GONE
        }
    }


    inner class webJavaScriptInterfaceIn  {

        @JavascriptInterface
        fun Javascript_makeText(message: String) {
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun Javascript_finish() {

            try {

                finish()

            }catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

}


class webJavaScriptInterface  {

    private var context: Context? = null
    private var webView: WebView? = null

    constructor(context: Context?, webView: WebView?) {
        this.context = context
        this.webView = webView
    }

    @JavascriptInterface
    fun Javascript_makeText(message: String) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
    }

    @JavascriptInterface
    fun Javascript_finish() {

        try {

            val activity = context as Activity
            activity.finish()

        }catch (e: Exception) {
            e.printStackTrace()
        }
    }
}