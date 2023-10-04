package com.daeseong.spannable_test

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.*
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private lateinit var tv1: TextView
    private lateinit var tv2: TextView
    private lateinit var tv3: TextView
    private lateinit var tv4: TextView
    private lateinit var tv5: TextView
    private lateinit var tv6: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        tv1 = findViewById(R.id.tv1)
        tv1.text = "텍스트 색상 변경"

        tv2 = findViewById(R.id.tv2)
        tv2.text = "텍스트 색상 변경"

        tv3 = findViewById(R.id.tv3)
        tv3.text = "텍스트 색상 변경"

        tv4 = findViewById(R.id.tv4)
        tv4.text = "텍스트 색상 변경"

        tv5 = findViewById(R.id.tv5)
        tv5.text = "텍스트 이미지 추가"

        tv6 = findViewById(R.id.tv6)
        tv6.text = "텍스트 색상 볼드 처리"

        settv1()
        settv2()
        settv3()
        settv4()
        settv5()
        settv6()
    }

    //글자색
    private fun settv1() {

        val spannable = SpannableString(tv1.text)
        spannable.setSpan(ForegroundColorSpan(Color.RED), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        tv1.text = spannable
    }

    //배경색
    private fun settv2() {

        val spannable = SpannableString(tv2.text)
        spannable.setSpan(BackgroundColorSpan(Color.RED), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        tv2.text = spannable
    }

    //밑줄
    private fun settv3() {

        val spannable = SpannableString(tv3.text)
        spannable.setSpan(UnderlineSpan(), 4, 6, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        tv3.text = spannable
    }

    private fun settv4() {

        val spannable = SpannableString(tv4.text)
        spannable.apply {
            //글자색
            setSpan(ForegroundColorSpan(Color.WHITE), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            //배경색
            setSpan(BackgroundColorSpan(Color.RED), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            //밑줄
            setSpan(UnderlineSpan(), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        tv4.text = spannable
    }

    private fun settv5() {

        val drawable = resources.getDrawable(R.drawable.number1, null)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)

        val spannable = SpannableString(tv5.text)
        spannable.setSpan(ImageSpan(drawable), 0, 3, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        tv5.text = spannable
    }

    private fun settv6() {

        //문장에서 찾을 문자열
        val sFind = "볼드"
        val nStart = tv6.text.toString().indexOf(sFind)
        val nEnd = nStart + sFind.length

        val spannable = SpannableString(tv6.text)
        spannable.apply {
            setSpan(ForegroundColorSpan(Color.WHITE), nStart, nEnd, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            setSpan(BackgroundColorSpan(Color.RED), nStart, nEnd, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
            setSpan(StyleSpan(Typeface.BOLD), nStart, nEnd, Spannable.SPAN_INCLUSIVE_EXCLUSIVE)
        }
        tv6.text = spannable
    }
}