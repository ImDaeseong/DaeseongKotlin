package com.daeseong.newbanner_test.Banner2_style

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.View.OnTouchListener
import android.view.WindowManager
import android.webkit.*
import android.widget.RelativeLayout
import com.daeseong.newbanner_test.MainActivity

class BannerView @JvmOverloads constructor(private val activity: Activity, private val url: String, WIDTH: Int, HEIGHT: Int,  attrs: AttributeSet? = null, defStyle: Int = 0) : RelativeLayout(activity, attrs, defStyle), OnTouchListener {

    private val tag = BannerView::class.java.simpleName

    private var banner2style: WebView?
    private var mDensity = 0f
    private var WIDTH = 0 //300
    private var HEIGHT = 0 //50

    init {
        this.WIDTH = WIDTH
        this.HEIGHT = HEIGHT
        banner2style = null
        init(activity.applicationContext)
        load()
    }

    private fun init(context: Context) {
        val windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val dm = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(dm)
        mDensity = dm.density
        setOnTouchListener(this)
    }

    private fun load() {

        banner2style = WebView(context)

        if (WIDTH > 0 && HEIGHT > 0) {

            //Log.e(tag, (WIDTH * mDensity).toInt().toString())

            addView( banner2style, LayoutParams( (WIDTH * mDensity).toInt(), (HEIGHT * mDensity).toInt() ) )
        } else {

            //Log.e(tag, WIDTH.toString())

            addView( banner2style, LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT) )
        }

        banner2style!!.setOnTouchListener(this)

        val webViewClient: WebViewClient = object : WebViewClient() {

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)

                Log.e(tag, "onPageStarted")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)

                Log.e(tag, "onPageFinished")
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                Log.e(tag, "shouldOverrideUrlLoading")

                return false
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)

                Log.e(tag, "onReceivedError")
            }
        }
        banner2style!!.webViewClient = webViewClient

        val webSettings = banner2style!!.settings
        webSettings.setSupportZoom(false)
        webSettings.javaScriptEnabled = true
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            webSettings.allowUniversalAccessFromFileURLs = true
        }

        webSettings.setRenderPriority(WebSettings.RenderPriority.HIGH)
        webSettings.cacheMode = WebSettings.LOAD_NO_CACHE
        val databasePath = banner2style!!.context.getDir("wall", Context.MODE_PRIVATE).path
        webSettings.databaseEnabled = true
        webSettings.domStorageEnabled = true
        webSettings.databasePath = databasePath

        banner2style!!.loadUrl(url)
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        when (event.action) {
            MotionEvent.ACTION_UP -> {
                val intent = Intent(activity, MainActivity::class.java)
                activity.startActivity(intent)
                activity.finish()
            }
            MotionEvent.ACTION_DOWN -> { }
            else -> { }
        }
        return true
    }
}