package com.daeseong.newbanner_test.Banner2_style

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.os.Build
import android.util.AttributeSet
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.webkit.*
import android.widget.RelativeLayout
import com.daeseong.newbanner_test.MainActivity

class BannerView @JvmOverloads constructor(private val activity: Activity, private val url: String, private var WIDTH: Int = 0, private var HEIGHT: Int = 0, attrs: AttributeSet? = null, defStyle: Int = 0)
    : RelativeLayout(activity, attrs, defStyle), View.OnTouchListener {

    private val tag = BannerView::class.java.simpleName

    private var banner2style: WebView = WebView(context)
    private var mDensity: Float = 0f

    init {
        init(activity.applicationContext)
        load()
    }

    private fun init(context: Context) {
        val dm = DisplayMetrics()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(dm)
        mDensity = dm.density
        setOnTouchListener(this)
    }

    private fun load() {
        val layoutParams = LayoutParams(
            if (WIDTH > 0) (WIDTH * mDensity).toInt() else LayoutParams.WRAP_CONTENT,
            if (HEIGHT > 0) (HEIGHT * mDensity).toInt() else LayoutParams.WRAP_CONTENT
        )

        addView(banner2style, layoutParams)
        banner2style.setOnTouchListener(this)

        val webViewClient = object : WebViewClient() {
            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                //Log.e(tag, "onPageStarted")
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                //Log.e(tag, "onPageFinished")
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                //Log.e(tag, "shouldOverrideUrlLoading")
                return false
            }

            override fun onReceivedError(view: WebView, request: WebResourceRequest, error: WebResourceError) {
                super.onReceivedError(view, request, error)
                //Log.e(tag, "onReceivedError")
            }
        }
        banner2style.webViewClient = webViewClient

        with(banner2style.settings) {

            setSupportZoom(false)
            javaScriptEnabled = true
            allowFileAccess = true

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                safeBrowsingEnabled = true
            }

            cacheMode = WebSettings.LOAD_NO_CACHE

            val databasePath = banner2style.context.getDir("wall", Context.MODE_PRIVATE).path
            this.databasePath = databasePath

            databaseEnabled = true
            domStorageEnabled = true
        }

        banner2style.loadUrl(url)
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
