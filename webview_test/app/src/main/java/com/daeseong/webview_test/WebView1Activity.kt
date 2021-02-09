package com.daeseong.webview_test

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
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
        }

        //네트워크 연결 여부
        if(IsConnect()){
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

            sTitle = view.title
            Log.d(tag, "onPageFinished:$sTitle")
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }

        override fun onReceivedError(
            view: WebView,
            request: WebResourceRequest,
            error: WebResourceError
        ) {
            super.onReceivedError(view, request, error)
        }
    }

}
