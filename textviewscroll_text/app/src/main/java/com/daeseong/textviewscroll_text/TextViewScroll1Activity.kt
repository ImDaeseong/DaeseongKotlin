package com.daeseong.textviewscroll_text

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.TextView

class TextViewScroll1Activity : AppCompatActivity() {

    private lateinit var tv1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_view_scroll1)

        tv1 = findViewById(R.id.tv1)

        // 내용이 꽉찬 상태에서만 자동으로 작동
        tv1.text = "TextViewScroll1Activity TextViewScroll1Activity TextViewScroll1Activity"

        tv1.ellipsize = TextUtils.TruncateAt.MARQUEE
        tv1.isSelected = true
    }
}