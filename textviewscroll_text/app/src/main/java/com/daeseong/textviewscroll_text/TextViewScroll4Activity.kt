package com.daeseong.textviewscroll_text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TextViewScroll4Activity : AppCompatActivity() {

    companion object {
        private val tag = TextViewScroll4Activity::class.java.simpleName
    }

    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var textScrollerAni: TextScrollerAni

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_scroll4)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)

        // 스크롤 클래스
        textScrollerAni = TextScrollerAni(tv1, tv2)
    }

    override fun onDestroy() {
        super.onDestroy()
        textScrollerAni.stop()
    }

    override fun onPause() {
        super.onPause()
        textScrollerAni.stop()
    }

    override fun onResume() {
        super.onResume()
        textScrollerAni.start()
    }
}