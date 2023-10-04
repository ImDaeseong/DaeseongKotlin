package com.daeseong.spannable_test

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private lateinit var tv1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tv1 = findViewById(R.id.tv1)
        tv1.text = "텍스트 링크클릭 효과 \r\n텍스트 링크클릭 효과"

        settv1()
    }

    private fun settv1() {
        val spannable = tv1.text as Spannable

        val sUrl = "https://naver.com"

        // 1st link click
        spannable.setSpan(makeClickableSpan(sUrl), 4, 8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        // 2nd link click
        spannable.setSpan(makeClickableSpan(sUrl), 17, 22, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        //클릭 가능하게 설정
        tv1.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun makeClickableSpan(url: String) = object : ClickableSpan() {
        override fun onClick(view: View) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        }
    }
}