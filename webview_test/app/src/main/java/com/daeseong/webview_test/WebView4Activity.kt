package com.daeseong.webview_test

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.Button
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class WebView4Activity : AppCompatActivity() {

    private val tag = WebView4Activity::class.java.simpleName

    private lateinit var sTitle: String
    private lateinit var context: Context
    private lateinit var webView: WebView
    private lateinit var webJavaScriptInterface: WebJavaScriptInterface
    private lateinit var progressBar: ProgressBar

    companion object {
        @SuppressLint("ObsoleteSdkInt")
        fun isNetworkAvailable(context: Context): Boolean {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val networkInfo: NetworkInfo? = connectivityManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view4)

        init()

        // 네트워크 연결 여부
        if (isNetworkAvailable(this)) {
            initWebWebView("file:///android_asset/test3.html")
        } else {
            initWebWebView("about:blank")
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

    override fun onPause() {
        super.onPause()

        // 백그라운드 실행시 - 웹화면 일시중지
        try {
            Class.forName("android.webkit.WebView")
                .getMethod("onPause", *arrayOf<Class<*>>())
                .invoke(webView, *arrayOf<Any>())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()

        // 백그라운드 실행시 - 웹화면 재시작
        try {
            Class.forName("android.webkit.WebView")
                .getMethod("onResume", *arrayOf<Class<*>>())
                .invoke(webView, *arrayOf<Any>())
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        // 메모리 해제
        clearWebView()
    }

    private fun init() {
        context = applicationContext
        webView = findViewById(R.id.webview1)
        progressBar = findViewById(R.id.progressBar1)
    }

    private fun initWebWebView(url: String) {

        // 웹뷰 세팅
        val webSettings: WebSettings = webView.settings
        webSettings.javaScriptEnabled = true
        webSettings.loadsImagesAutomatically = true
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        webSettings.domStorageEnabled = true
        webSettings.allowFileAccess = false
        webSettings.builtInZoomControls = true
        webSettings.builtInZoomControls = true
        webSettings.displayZoomControls = true

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            webSettings.textZoom = 100
        }

        webSettings.javaScriptCanOpenWindowsAutomatically = true
        webSettings.saveFormData = false
        webSettings.savePassword = false
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN

        webJavaScriptInterface = WebJavaScriptInterface(this)
        webView.addJavascriptInterface(webJavaScriptInterface, "Android")

        webView.webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                progressBar.visibility = View.GONE

                sTitle = view?.title.orEmpty()
                Log.d("onPageFinished", sTitle)

                webJavaScriptInterface.Javascript_htmlTojavaScript()
                Log.e(tag, "html에 있는 javascript 호출")
            }

            @RequiresApi(api = Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {
                Log.d(tag, request?.url.toString())
                return true
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                super.onReceivedError(view, errorCode, description, failingUrl)

                // error 400 보다 작으면 정상 처리
                if (errorCode < 400) {
                    return
                }

                webView.loadUrl("about:blank")
                progressBar.visibility = View.GONE
            }

            @RequiresApi(api = Build.VERSION_CODES.M)
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)

                // error 400 보다 작으면 정상 처리
                if (error?.errorCode ?: 0 < 400) {
                    return
                }

                webView.loadUrl("about:blank")
                progressBar.visibility = View.GONE
            }
        }

        webView.webChromeClient = object : WebChromeClient() {

            override fun onJsAlert(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                val sTitle = "<font color=\"#000000\">%s</font>".format("알림")
                val sMessage = "<font color=\"#000000\">%s</font>".format(message)
                val builder = AlertDialog.Builder(view?.context ?: return false, R.style.AlertDialogTheme)
                builder.setTitle(Html.fromHtml(sTitle))
                builder
                    .setMessage(Html.fromHtml(sMessage))
                    .setCancelable(false)
                    .setPositiveButton("확인") { dialog, which -> result?.confirm() }
                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()
                val btnPositive: Button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                btnPositive.setTextColor(Color.BLACK)
                return true
            }

            override fun onJsConfirm(view: WebView?, url: String?, message: String?, result: JsResult?): Boolean {
                val sTitle = "<font color=\"#000000\">%s</font>".format("알림")
                val sMessage = "<font color=\"#000000\">%s</font>".format(message)

                val builder = AlertDialog.Builder(view?.context ?: return false, R.style.AlertDialogTheme)
                builder.setTitle(Html.fromHtml(sTitle))
                builder
                    .setMessage(Html.fromHtml(sMessage))
                    .setCancelable(false)
                    .setPositiveButton("확인") { _, _ -> result?.confirm() }
                    .setNegativeButton("취소") { _, _ -> result?.cancel() }

                val alertDialog: AlertDialog = builder.create()
                alertDialog.show()

                val btnNegative: Button = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                btnNegative.setTextColor(Color.BLACK)

                val btnPositive: Button = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                btnPositive.setTextColor(Color.BLACK)

                return true
            }

            override fun onJsPrompt(view: WebView?, url: String?, message: String?, defaultValue: String?, result: JsPromptResult?): Boolean {
                return super.onJsPrompt(view, url, message, defaultValue, result)
            }

            override fun onProgressChanged(view: WebView?, newProgress: Int) {
                super.onProgressChanged(view, newProgress)

                if (newProgress == 100) {
                    progressBar.visibility = View.GONE
                } else {
                    if (progressBar.visibility == View.GONE)
                        progressBar.visibility = View.VISIBLE
                    progressBar.progress = newProgress
                }
            }

            override fun onReceivedTitle(view: WebView?, title: String?) {
                super.onReceivedTitle(view, title)
                Log.e(tag, "title:$title")
            }
        }

        webView.loadUrl(url)
    }

    private fun clearWebView() {
        try {
            webView.removeAllViews()
            (webView.parent as? ViewGroup)?.removeView(webView)
            webView.tag = null
            webView.clearHistory()
            webView.destroy()
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
        }
    }

    private inner class WebJavaScriptInterface(private val mContext: Context) {

        @JavascriptInterface
        fun Javascript_htmlTojavaScript() {
            webView.loadUrl("javascript:EndLoad_Message()")
        }

        @JavascriptInterface
        fun Javascript_finish() {
            (mContext as? Activity)?.finish()
        }

        @JavascriptInterface
        fun Javascript_webToNative(message: String) {
            val sMsg = "%s".format(message)
            Log.e(tag, sMsg)
        }

        @JavascriptInterface
        fun Javascript_getString(): String {
            return "html로 문자열 전달"
        }

        @JavascriptInterface
        fun Javascript_getInt(): Int {
            return 1
        }

        @JavascriptInterface
        fun Javascript_getBoolean(): Boolean {
            return true
        }
    }
}
