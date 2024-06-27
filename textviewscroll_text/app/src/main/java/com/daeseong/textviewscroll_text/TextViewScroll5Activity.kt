package com.daeseong.textviewscroll_text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class TextViewScroll5Activity : AppCompatActivity() {

    companion object {
        private val tag = TextViewScroll5Activity::class.java.simpleName
    }

    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var textScrollerEx : TextScrollerEx

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_scroll5)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)

        // 스크롤 클래스
        textScrollerEx = TextScrollerEx(tv1, tv2)
    }

    override fun onDestroy() {
        super.onDestroy()
        textScrollerEx.stop()
    }

    override fun onPause() {
        super.onPause()
        textScrollerEx.stop()
    }

    override fun onResume() {
        super.onResume()
        textScrollerEx.start()
    }
}