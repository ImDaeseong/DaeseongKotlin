package com.daeseong.spannable_test

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main5Activity : AppCompatActivity() {

    private val tag = Main5Activity::class.java.simpleName

    private lateinit var tv1: TextView

    private val sUrl1 = "https://naver.com"
    private val sUrl2 = "https://google.com"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        tv1 = findViewById(R.id.tv1)
        tv1.text = "텍스트 링크클릭 효과 \r\n텍스트 링크클릭 효과"

        settv1()
    }

    private fun settv1() {

        val spannableString = SpannableString(tv1.text)

        val clickableSpan1 = ClickableSpanEx(this, sUrl1)
        val clickableSpan2 = ClickableSpanEx(this, sUrl2)

        spannableString.setSpan(clickableSpan1, 4, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(clickableSpan2, 17, 22, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        tv1.text = spannableString
        tv1.movementMethod = LinkMovementMethod.getInstance()
    }
}