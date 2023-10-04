package com.daeseong.spannable_test

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var tv4: TextView
    private lateinit var tv5: TextView
    private lateinit var tv6: TextView
    private lateinit var tv7: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tv1 = findViewById(R.id.tv1)
        tv2 = findViewById(R.id.tv2)
        tv3 = findViewById(R.id.tv3)
        tv4 = findViewById(R.id.tv4)
        tv5 = findViewById(R.id.tv5)
        tv6 = findViewById(R.id.tv6)
        tv7 = findViewById(R.id.tv7)

        tv1.text = "텍스트 색상 변경"
        tv2.text = "텍스트 색상 변경"
        tv3.text = "텍스트 색상 변경"
        tv4.text = "텍스트 색상 변경"
        tv5.text = "텍스트 색상 변경"
        tv6.text = "텍스트 이미지 추가"
        tv7.text = "텍스트 색상 볼드 처리"

        settv1()
        settv2()
        settv3()
        settv4()
        settv5()
        settv6()
        settv7()
    }

    private fun setTextWithSpan(tv: TextView, span: Any, start: Int, end: Int) {
        val spannableString = SpannableString(tv.text)
        spannableString.setSpan(span, start, end, 0)
        tv.text = spannableString
    }

    private fun settv1() {

        setTextWithSpan(tv1, ForegroundColorSpan(Color.RED), 0, 3)
    }

    private fun settv2() {

        setTextWithSpan(tv2, BackgroundColorSpan(Color.RED), 0, 3)
    }

    private fun settv3() {

        setTextWithSpan(tv3, UnderlineSpan(), 4, 6)
    }

    private fun settv4() {

        setTextWithSpan(tv4, URLSpan("https://naver.com"), 0, 3)
    }

    private fun settv5() {

        val spannableString = SpannableString(tv5.text)
        val clickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://naver.com"))
                startActivity(intent)
            }
        }
        spannableString.setSpan(clickableSpan, 4, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tv5.text = spannableString

        //클릭 가능하게 설정
        tv5.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun settv6() {

        val drawable = resources.getDrawable(R.drawable.number1, null)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)
        setTextWithSpan(tv6, ImageSpan(drawable), 0, 3)
    }

    private fun settv7() {

        //문장에서 찾을 문자열
        val sFind = "볼드"
        val nStart = tv7.text.toString().indexOf(sFind)
        val nEnd = nStart + sFind.length

        setTextWithSpan(tv7, ForegroundColorSpan(Color.WHITE), nStart, nEnd)
        setTextWithSpan(tv7, BackgroundColorSpan(Color.RED), nStart, nEnd)
        setTextWithSpan(tv7, StyleSpan(Typeface.BOLD), nStart, nEnd)
    }
}