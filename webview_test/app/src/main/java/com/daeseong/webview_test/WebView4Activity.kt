package com.daeseong.webview_test

import android.app.Activity
import android.content.Context
import android.content.DialogInterface
import android.graphics.Bitmap
import android.graphics.Color
import android.net.ConnectivityManager
import android.net.http.SslError
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import java.lang.reflect.Method

class WebView4Activity : AppCompatActivity() {

    private val tag: String = WebView4Activity::class.java.simpleName

    private var sTitle: String? = null
    private var context: Context? = null
    private var webView: WebView? = null

    private var progressBar: ProgressBar? = null
    private var webJavaScriptInterface: webJavaScriptInterface? = null

    private fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connectivityManager.isDefaultNetworkActive
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_web_view4)

        init()

        //네트워크 연결 여부
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

        //백그라운드 실행시 - 웹화면 일시중지

        try {

            val pause = WebView::class.java.getMethod("onPause")
            pause.invoke(webView)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()

        //백그라운드 실행시 - 웹화면 재시작

        try {

            val resume = WebView::class.java.getMethod("onResume")
            resume.invoke(webView)

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        clearWebView()
    }

    private fun init() {
        context = applicationContext
        webView = findViewById<WebView>(R.id.webview1)
        progressBar = findViewById<ProgressBar>(R.id.progressBar1)
    }

    private fun initWebWebView(url: String) {

        // 웹뷰 세팅
        val webSettings = webView!!.settings
        webSettings.javaScriptEnabled = true // 자바 스크립트 사용

        webSettings.loadsImagesAutomatically = true //웹뷰가 앱에 등록되어 있는 이미지 리소스를 자동으로 로드하도록 설정하는 속성
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE //캐시모드를 사용하지 않고 네트워크를 통해서만 호출
        //webSettings.setAppCacheEnabled(false) //앱 내부 캐시 사용 여부 설정
        webSettings.domStorageEnabled = true //로컬 스토리지 사용 여부를 설정하는 속성으로 팝업창등을 '하루동안 보지 않기' 기능 사용에 필요
        webSettings.allowFileAccess = false // 웹 뷰 내에서 파일 액세스 활성화 여부

        //webSettings.useWideViewPort = true//웹뷰에 맞게 출력하기
        //webSettings.loadWithOverviewMode = true

        //WebView에서 확대/축소 가능
        webSettings.builtInZoomControls = true // 안드로이드 내장 줌 컨트롤 사용 X
        webSettings.setSupportZoom(true) //확대 축소 기능
        webSettings.displayZoomControls = true //줌 컨트롤 보이기/숨기기

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
            webSettings.textZoom = 100
        }

        // API 레벨 16부터 이용할 수 있다.
        webSettings.javaScriptCanOpenWindowsAutomatically = true // javascript 가  window.open()을 사용할 수 있도록 설정
        webSettings.saveFormData = false // 폼의 입력값를 저장하지 않는다
        webSettings.savePassword = false // 암호를 저장하지 않는다.
        webSettings.layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기

        webJavaScriptInterface = webJavaScriptInterface(this, webView!!)
        webView!!.addJavascriptInterface(webJavaScriptInterface!!, "Android")

        webView!!.webViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                progressBar!!.visibility = View.GONE;

                if (view != null) {
                    sTitle = view.title ?: ""
                    Log.e(tag, "onPageFinished: $sTitle")
                }

                webJavaScriptInterface!!.Javascript_htmlTojavaScript()
                Log.e(tag, "html에 있는 javascript 호출")
            }

            override fun shouldOverrideUrlLoading(view: WebView?, request: WebResourceRequest?): Boolean {

                if (request != null) {
                    Log.d(tag, request.url.toString())
                }

                return true
                //return super.shouldOverrideUrlLoading(view, request)
            }

            @RequiresApi(Build.VERSION_CODES.M)
            override fun onReceivedError(view: WebView?, request: WebResourceRequest?, error: WebResourceError?) {
                super.onReceivedError(view, request, error)

                //error 400 보다 작으면 정상 처리
                if (error != null) {
                    if(error.errorCode < 400){
                        return;
                    }
                }

                webView!!.loadUrl("about:blank")
                progressBar!!.visibility = View.GONE
            }

            override fun onReceivedError(view: WebView?, errorCode: Int, description: String?, failingUrl: String?) {
                super.onReceivedError(view, errorCode, description, failingUrl)

                //error 400 보다 작으면 정상 처리
                if (errorCode < 400) {
                    return
                }

                webView!!.loadUrl("about:blank")
                progressBar!!.visibility = View.GONE
            }

            override fun onReceivedHttpError(view: WebView?, request: WebResourceRequest?, errorResponse: WebResourceResponse?) {
                super.onReceivedHttpError(view, request, errorResponse)

            }

            override fun onReceivedSslError(view: WebView?, handler: SslErrorHandler?, error: SslError?) {
                super.onReceivedSslError(view, handler, error)

                try {

                    val sTitle = String.format("<font color=\"#000000\">%s</font>", "알림")
                    val sMessage = String.format("<font color=\"#000000\">%s</font>", "이 사이트 보안 인증서는 신뢰하는 보안 인증서가 아닙니다.\n계속하시겠습니까?")

                    val builder = AlertDialog.Builder(view!!.context, R.style.AlertDialogTheme)
                    builder.setTitle(Html.fromHtml(sTitle))
                    builder
                        .setMessage(Html.fromHtml(sMessage))
                        .setCancelable(false)
                        .setPositiveButton(
                            "계속"
                        ) { _, _ ->
                            handler?.proceed()
                        }
                        .setNegativeButton(
                            "취소"
                        ) { _, _ ->
                            handler?.cancel()
                        }

                    val alertDialog = builder.create()
                    alertDialog.show()

                    val btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                    btnNegative.setTextColor(Color.BLACK)

                    val btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                    btnPositive.setTextColor(Color.BLACK)
                } catch (ex: java.lang.Exception) {
                }
            }
        }

        webView!!.webChromeClient = object : WebChromeClient() {

            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {

                val sTitle = String.format("<font color=\"#000000\">%s</font>", "알림")
                val sMessage = String.format("<font color=\"#000000\">%s</font>", message)

                val builder = AlertDialog.Builder(view.context, R.style.AlertDialogTheme)
                builder.setTitle(Html.fromHtml(sTitle))
                builder
                    .setMessage(Html.fromHtml(sMessage))
                    .setCancelable(false)
                    .setPositiveButton(
                        "확인"
                    ) { dialog, which -> result.confirm() }

                val alertDialog = builder.create()
                alertDialog.show()

                val btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                btnPositive.setTextColor(Color.BLACK)
                return true
                //return super.onJsAlert(view, url, message, result);
            }

            override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {

                val sTitle = String.format("<font color=\"#000000\">%s</font>", "알림")
                val sMessage = String.format("<font color=\"#000000\">%s</font>", message)

                val builder = AlertDialog.Builder(view.context, R.style.AlertDialogTheme)
                builder.setTitle(Html.fromHtml(sTitle))
                builder
                    .setMessage(Html.fromHtml(sMessage))
                    .setCancelable(false)
                    .setPositiveButton(
                        "확인"
                    ) { dialog, id -> // 프로그램을 종료
                        //EventActivity.this.finish();
                        result.confirm()
                    }
                    .setNegativeButton(
                        "취소"
                    ) { dialog, id -> // 다이얼로그를 취소
                        //dialog.cancel();
                        result.cancel()
                    }

                val alertDialog = builder.create()
                alertDialog.show()

                val btnNegative = alertDialog.getButton(DialogInterface.BUTTON_NEGATIVE)
                btnNegative.setTextColor(Color.BLACK)

                val btnPositive = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE)
                btnPositive.setTextColor(Color.BLACK)
                return true
                //return super.onJsConfirm(view, url, message, result);
            }

            override fun onJsPrompt(view: WebView, url: String, message: String, defaultValue: String, result: JsPromptResult): Boolean {
                return super.onJsPrompt(view, url, message, defaultValue, result)

            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                super.onProgressChanged(view, newProgress)

                if (newProgress == 100) {
                    progressBar!!.visibility = View.GONE
                } else {
                    if (progressBar!!.visibility == View.GONE) progressBar!!.visibility = View.VISIBLE
                    progressBar!!.progress = newProgress
                }
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)

                Log.e(tag, "title:$title")
            }
        }

        webView!!.loadUrl(url)
    }

    private fun clearWebView() {
        try {
            if (webView != null) {
                webView!!.removeAllViews()
                // in android 5.1(sdk:21) we should invoke this to avoid memory leak
                // see (https://coolpers.github.io/webview/memory/leak/2015/07/16/
                // android-5.1-webview-memory-leak.html)
                (webView!!.parent as ViewGroup).removeView(webView)
                webView!!.tag = null
                webView!!.clearHistory()
                webView!!.destroy()
                webView = null
            }
        } catch (e: java.lang.Exception) {
        }
    }
}

class webJavaScriptInterface(var mContext: Context, var webView : WebView) {

    @JavascriptInterface
    fun Javascript_htmlTojavaScript() {
        webView!!.loadUrl("javascript:EndLoad_Message()")
    }

    @JavascriptInterface
    fun Javascript_finish() {
        (mContext as Activity).finish()
    }

    @JavascriptInterface
    fun Javascript_webToNative(message: String?) {

        val sMsg = String.format("%s", message)
        Log.e("Javascript_webToNative", sMsg)
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