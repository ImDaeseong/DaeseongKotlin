package com.daeseong.uidrawer

import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.*
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainTab3Fragment : Fragment() {

    companion object {
        private const val TAG = "MainTab3Fragment"
    }

    private lateinit var wvWebView: WebView
    private lateinit var pProgressBar: ProgressBar
    private lateinit var mContext: Context
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var bRefresh = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        mContext = container!!.context
        return inflater.inflate(R.layout.fragment_main_tab3, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        wvWebView = view.findViewById(R.id.webview)
        pProgressBar = view.findViewById(R.id.progressbar1)
        swipeRefreshLayout = view.findViewById(R.id.swLayout)

        swipeRefreshLayout.setColorSchemeColors(
            resources.getColor(R.color.refresh_progress_3),
            resources.getColor(R.color.refresh_progress_2),
            resources.getColor(R.color.refresh_progress_3)
        )

        swipeRefreshLayout.setOnRefreshListener {
            try {
                Log.e(TAG, "onRefresh")
                bRefresh = true
                wvWebView.url?.let { wvWebView.loadUrl(it) }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        try {
            initWebView("https://m.naver.com/")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        clearWebViewResource()
    }

    private fun clearWebViewResource() {
        try {
            if (::wvWebView.isInitialized) {
                wvWebView.removeAllViews()
                (wvWebView.parent as? ViewGroup)?.removeView(wvWebView)
                wvWebView.tag = null
                wvWebView.clearHistory()
                wvWebView.destroy()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun initWebView(url: String) {

        val webSettings = wvWebView.settings
        webSettings.apply {
            javaScriptEnabled = true// 자바 스크립트 사용
            loadsImagesAutomatically = true//웹뷰가 앱에 등록되어 있는 이미지 리소스를 자동으로 로드하도록 설정하는 속성
            setSupportZoom(false)//확대 축소 기능
            cacheMode = WebSettings.LOAD_NO_CACHE//캐시모드를 사용하지 않고 네트워크를 통해서만 호출
            //setAppCacheEnabled(false)//앱 내부 캐시 사용 여부 설정
            domStorageEnabled = true//로컬 스토리지 사용 여부를 설정하는 속성으로 팝업창등을 '하루동안 보지 않기' 기능 사용에 필요
            userAgentString = "app"//웹에서 해당 속성을 통해 앱에서 띄운 웹뷰로 인지
            allowFileAccess = true// 웹 뷰 내에서 파일 액세스 활성화 여부
            useWideViewPort = true//웹뷰에 맞게 출력하기
            loadWithOverviewMode = true
            builtInZoomControls = false// 안드로이드 내장 줌 컨트롤 사용 X

            // API 레벨 16부터 이용할 수 있다.
            javaScriptCanOpenWindowsAutomatically = true// javascript 가  window.open()을 사용할 수 있도록 설정
            saveFormData = false// 폼의 입력값를 저장하지 않는다
            savePassword = false// 암호를 저장하지 않는다.
            layoutAlgorithm = WebSettings.LayoutAlgorithm.SINGLE_COLUMN // 컨텐츠 사이즈 맞추기
        }

        wvWebView.addJavascriptInterface(WebJavaScriptInterface(mContext), "Android")

        wvWebView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                try {
                    Log.e(TAG, "url:$url")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return true
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
                try {
                    val url = request.url.toString()
                    Log.e(TAG, "request url:$url")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return true
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
            }

            override fun onLoadResource(view: WebView?, url: String?) {
                super.onLoadResource(view, url)
            }

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {
                view.loadUrl("about:blank")
                pProgressBar.visibility = View.GONE
                swipeRefreshLayout.isRefreshing = false
                bRefresh = false
                super.onReceivedError(view, errorCode, description, failingUrl)
            }
        }

        wvWebView.webChromeClient = object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(view.context)
                    .setTitle("알림1")
                    .setMessage(message)
                    .setPositiveButton("네") { _, _ ->
                        result.confirm()
                    }
                    .setCancelable(false)
                    .create()
                    .show()
                return true
            }

            override fun onJsConfirm(view: WebView, url: String, message: String, result: JsResult): Boolean {
                AlertDialog.Builder(view.context)
                    .setTitle("알림1")
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

            override fun onJsPrompt(view: WebView, url: String, message: String, defaultValue: String, result: JsPromptResult): Boolean {
                return super.onJsPrompt(view, url, message, defaultValue, result)
            }

            override fun onProgressChanged(view: WebView, newProgress: Int) {
                if (newProgress == 100) {
                    pProgressBar.visibility = View.GONE
                    swipeRefreshLayout.isRefreshing = false
                    bRefresh = false
                } else {
                    if (bRefresh) {
                        if (!swipeRefreshLayout.isRefreshing) swipeRefreshLayout.isRefreshing = true
                    } else {
                        if (pProgressBar.visibility == View.GONE) {
                            pProgressBar.visibility = View.VISIBLE
                        }
                        pProgressBar.progress = newProgress
                    }
                }
                super.onProgressChanged(view, newProgress)
            }

            override fun onReceivedTitle(view: WebView, title: String) {
                super.onReceivedTitle(view, title)
                try {
                    Log.e(TAG, "onReceivedTitle:$title")
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        wvWebView.setOnKeyListener(View.OnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_BACK && wvWebView.canGoBack()) {
                    Log.e(TAG, "setOnKeyListener")
                    wvWebView.goBack()
                    return@OnKeyListener true
                }
            }
            false
        })
        wvWebView.loadUrl(url)
    }

    inner class WebJavaScriptInterface(private val context: Context) {

        @JavascriptInterface
        fun Javascript_makeText(toast: String?) {
            Toast.makeText(context, toast, Toast.LENGTH_SHORT).show()
        }

        @JavascriptInterface
        fun Javascript_finish() {
            (context as Activity).finish()
        }
    }
}