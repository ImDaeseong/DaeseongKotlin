package com.daeseong.webview_test

import android.annotation.TargetApi
import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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

    private var sTitle: String? = null
    private var context: Context? = null
    private var webView: WebView? = null

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.isDefaultNetworkActive
    }

    private fun isWifiAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return !cm.isActiveNetworkMetered
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_web_view1)

        context = applicationContext

        webView = findViewById<WebView>(R.id.webview1)
        webView!!.settings.javaScriptEnabled = true//웹뷰에서 자바스크립트 실행 가능

        //webView!!.clearCache(true)//캐시 지우기
        //webView!!.reload()//현재 웹뷰 새로고침
        //webView!!.stopLoading()//로딩 중단

        //스크롤 없애기기
        //webView!!.isVerticalScrollBarEnabled = false;
        //webView!!.isHorizontalScrollBarEnabled = false;


        webView!!.webViewClient = CustomWebViewClient()

        //웹뷰에서 자바스크립트 alert과 confirm 이 동작하게 처리
        webView!!.webChromeClient = object : WebChromeClient() {
            //alert 처리
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {

                AlertDialog.Builder(view.context)
                    .setTitle("알림")
                    .setMessage(message)
                    .setPositiveButton(android.R.string.ok,
                        DialogInterface.OnClickListener { dialog, which ->
                            result.confirm()
                        })
                    .setCancelable(false)
                    .create()
                    .show()
                return true
                //return super.onJsAlert(view, url, message, result)
            }

            //confirm 처리
            override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {

                AlertDialog.Builder(view.context)
                    .setTitle("알림")
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
                //return super.onJsConfirm(view, url, message, result)
            }

            override fun onReceivedTitle(view: WebView?, title: String) {
                super.onReceivedTitle(view, title)

                Log.e(tag, "title:$title")
            }
        }

        //네트워크 연결 여부
        if(isNetworkAvailable(this)){
            webView!!.loadUrl("file:///android_asset/test1.html")
        }else {
            webView!!.loadUrl("about:blank");
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

    inner class CustomWebViewClient : WebViewClient() {

        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            try {

                Log.d("test1.html 에서 링크 클릭시:", url)

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

        @TargetApi(Build.VERSION_CODES.N)
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {

            Log.d("test1.html 에서 링크 클릭시:", request.url.toString())

            try {

                val url = request.url.toString()
                if (url.startsWith("app://")) {

                    val intent = Intent(context!!.applicationContext, MainActivity::class.java)
                    startActivity(intent)
                } else {

                    view.loadUrl(url)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return true
            //return super.shouldOverrideUrlLoading(view, request);
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

            sTitle = view.title
            Log.d("onPageFinished", sTitle)
        }
    }

}
