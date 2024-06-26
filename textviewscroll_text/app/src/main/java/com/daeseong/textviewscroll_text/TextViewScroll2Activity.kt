package com.daeseong.textviewscroll_text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TextViewScroll2Activity : AppCompatActivity() {

    companion object {
        private val tag = TextViewScroll2Activity::class.java.simpleName
    }

    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var textScroller: TextScroller

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_scroll2)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)

        // 스크롤 클래스
        textScroller = TextScroller(tv1, tv2)
    }

    override fun onDestroy() {
        super.onDestroy()
        textScroller.stop()
    }

    override fun onPause() {
        super.onPause()
        textScroller.stop()
    }

    override fun onResume() {
        super.onResume()
        textScroller.start()
    }
}