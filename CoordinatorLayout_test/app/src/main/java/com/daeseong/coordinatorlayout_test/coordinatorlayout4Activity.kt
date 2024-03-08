package com.daeseong.coordinatorlayout_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.appbar.AppBarLayout

class coordinatorlayout4Activity : AppCompatActivity() {

    private val tag = coordinatorlayout4Activity::class.java.simpleName

    private lateinit var aB1: AppBarLayout
    private lateinit var web1: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coordinatorlayout4)

        aB1 = findViewById(R.id.aB1)
        aB1.addOnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (verticalOffset == 0) {
                Log.e(tag, "툴바가 완전히 펼쳐진 상태")
            } else {
                Log.e(tag, "툴바가 축소된 상태")
            }
        }

        //시작시 축소된 상태
        aB1.setExpanded(false)

        web1 = findViewById(R.id.web1)
        web1.settings.javaScriptEnabled = true
        web1.webViewClient = WebViewClient()
        web1.loadUrl("https://m.naver.com/")
    }
}