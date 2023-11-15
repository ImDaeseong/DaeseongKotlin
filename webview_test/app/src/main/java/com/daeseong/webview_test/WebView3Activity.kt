package com.daeseong.webview_test

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.ConnectivityManager
import android.os.Bundle
import android.os.Process
import android.webkit.*
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class WebView3Activity : AppCompatActivity() {

    private val tag: String = WebView3Activity::class.java.simpleName

    private var context: Context? = null
    private var webView: WebView? = null

    private var backPressCloseHandler: BackPressCloseHandler? = null

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.isDefaultNetworkActive
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view3)

        //백버튼 2번 클릭시 종료
        backPressCloseHandler = BackPressCloseHandler(this)

        context = applicationContext

        webView = findViewById<WebView>(R.id.webview1)
        webView!!.settings.defaultTextEncodingName = "UTF-8"
        webView!!.settings.javaScriptEnabled = true
        webView!!.settings.cacheMode = WebSettings.LOAD_NO_CACHE //캐시모드를 사용하지 않고 네트워크를 통해서만 호출
        //webView!!.settings.setAppCacheEnabled(true)
        webView!!.webViewClient = CustomWebViewClient()


        //네트워크 연결 여부
        if(isNetworkAvailable(this)){
            webView!!.loadUrl("https://m.naver.com")
        }else {
            webView!!.loadUrl("about:blank")
        }

        // 쿠키 즉시 싱크를 위한 싱크매니저 등록
        //CookieSyncManager.createInstance(applicationContext)
        //CookieManager.getInstance().setAcceptCookie(true)
    }

    override fun onResume() {
        super.onResume()

        // 쿠키 즉시 싱크 시작
        //CookieSyncManager.getInstance().startSync()
    }

    override fun onPause() {
        super.onPause()

        // 쿠키 즉시 싱크 중지
        //CookieSyncManager.getInstance().stopSync()
    }

    override fun onBackPressed() {

        backPressCloseHandler!!.onBackPressed()
        //super.onBackPressed();
    }

    inner class CustomWebViewClient : WebViewClient() {

        override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
            super.onReceivedError(view, request, error)

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

        override fun shouldOverrideUrlLoading(view: WebView?, url: String): Boolean {

            if (url.startsWith("http://127.0.0.1")) {

                if (url.contains("/login")) {
                    
                }
            }
            return super.shouldOverrideUrlLoading(view, url)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            // 하나의 페이지 전환이 일어날 때마다, 쿠키를 즉시 싱크하도록 한다.
            //CookieSyncManager.getInstance().sync()
        }

        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
        }
    }
}

class BackPressCloseHandler(private val activity: Activity) {

    private var backKeyPressedTime: Long = 0

    private var toast: Toast? = null

    fun onBackPressed() {

        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis()
            showmakeText()
            return
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            toast!!.cancel()
            activity.finish()
            Process.killProcess(Process.myPid())
        }
    }

    private fun showmakeText() {
        toast = Toast.makeText(activity, "'뒤로'버튼을 한번 더 누르면 종료합니다.", Toast.LENGTH_SHORT)
        toast!!.show()
    }
}