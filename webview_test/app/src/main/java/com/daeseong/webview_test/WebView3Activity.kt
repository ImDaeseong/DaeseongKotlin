package com.daeseong.webview_test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.webkit.CookieManager
import android.webkit.CookieSyncManager
import android.webkit.WebResourceError
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class WebView3Activity : AppCompatActivity() {

    private val tag = WebView3Activity::class.java.simpleName

    private lateinit var context: Context
    private lateinit var webView: WebView
    private lateinit var backPressCloseHandler: BackPressCloseHandler

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
        setContentView(R.layout.activity_web_view3)

        // 백버튼 2번 클릭시 종료
        backPressCloseHandler = BackPressCloseHandler(this)

        context = applicationContext

        webView = findViewById(R.id.webview1)
        webView.settings.defaultTextEncodingName = "UTF-8"
        webView.settings.javaScriptEnabled = true
        webView.settings.cacheMode = WebSettings.LOAD_NO_CACHE
        webView.webViewClient = CustomWebViewClient()

        // 네트워크 연결 여부
        if (isNetworkAvailable(this)) {
            webView.loadUrl("https://m.naver.com")
        } else {
            webView.loadUrl("about:blank")
        }

        // 쿠키 즉시 싱크를 위한 싱크매니저 등록
        CookieSyncManager.createInstance(applicationContext)
        CookieManager.getInstance().setAcceptCookie(true)
    }

    override fun onResume() {
        super.onResume()

        // 쿠키 즉시 싱크 시작
        CookieSyncManager.getInstance().startSync()
    }

    override fun onPause() {
        super.onPause()

        // 쿠키 즉시 싱크 중지
        CookieSyncManager.getInstance().stopSync()
    }

    override fun onBackPressed() {
        backPressCloseHandler.onBackPressed()
    }

    private inner class CustomWebViewClient : WebViewClient() {

        @RequiresApi(Build.VERSION_CODES.M)
        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("Url", request?.url.toString())
            intent.putExtra("ErrCode", error?.errorCode)
            intent.putExtra("Desdescription", error?.description)
            startActivity(intent)
        }

        override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
            super.onReceivedError(view, errorCode, description, failingUrl)

            val intent = Intent(applicationContext, MainActivity::class.java)
            intent.putExtra("Url", failingUrl)
            intent.putExtra("ErrCode", errorCode)
            intent.putExtra("Desdescription", description)
            startActivity(intent)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
            if (url?.startsWith("http://127.0.0.1") == true) {
                if (url.contains("/login")) {
                    Log.e(tag, "shouldOverrideUrlLoading")
                }
            }
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            // 하나의 페이지 전환이 일어날 때마다, 쿠키를 즉시 싱크하도록 한다.
            CookieSyncManager.getInstance().sync()
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }
    }

    private inner class BackPressCloseHandler(private val activity: Activity) {
        private var backKeyPressedTime: Long = 0
        private var toast: Toast? = null

        fun onBackPressed() {
            if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis()
                showmakeText()
                return
            }
            if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
                toast?.cancel()
                activity.finish()
                android.os.Process.killProcess(android.os.Process.myPid())
            }
        }

        private fun showmakeText() {
            toast = Toast.makeText(activity, "'뒤로'버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT)
            toast?.show()
        }
    }
}
