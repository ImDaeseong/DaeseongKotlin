package com.daeseong.spannable_test

import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.style.*
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null
    private var tv4: TextView? = null
    private var tv5: TextView? = null
    private var tv6: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        tv1 = findViewById<View>(R.id.tv1) as TextView
        tv1!!.text = "텍스트 색상 변경"

        tv2 = findViewById<View>(R.id.tv2) as TextView
        tv2!!.text = "텍스트 색상 변경"

        tv3 = findViewById<View>(R.id.tv3) as TextView
        tv3!!.text = "텍스트 색상 변경"

        tv4 = findViewById<View>(R.id.tv4) as TextView
        tv4!!.text = "텍스트 색상 변경"

        tv5 = findViewById<View>(R.id.tv5) as TextView
        tv5!!.text = "텍스트 이미지 추가"

        tv6 = findViewById<View>(R.id.tv6) as TextView
        tv6!!.text = "텍스트 색상 볼드 처리"

        settv1()
        settv2()
        settv3()
        settv4()
        settv5()
        settv6()
    }

    //글자색
    private fun settv1() {

        val spannable = tv1!!.text as Spannable
        spannable.setSpan(ForegroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    //배경색
    private fun settv2() {

        val spannable = tv2!!.text as Spannable
        spannable.setSpan(BackgroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    //밑줄
    private fun settv3() {

        val spannable = tv3!!.text as Spannable
        spannable.setSpan(UnderlineSpan(), 4, 6, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    private fun settv4() {

        val spannable = tv4!!.text as Spannable

        //글자색
        spannable.setSpan(ForegroundColorSpan(Color.WHITE), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        //배경색
        spannable.setSpan(BackgroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        //밑줄
        spannable.setSpan(UnderlineSpan(), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    private fun settv5() {

        val drawable = resources.getDrawable(R.drawable.number1, null)
        drawable.setBounds(0, 0, drawable.intrinsicWidth, drawable.intrinsicHeight)

        val spannable = tv5!!.text as Spannable
        spannable.setSpan(ImageSpan(drawable), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }

    private fun settv6() {

        //문장에서 찾을 문자열
        val sFind = "볼드"
        val nStart = tv6!!.text.toString().indexOf(sFind)
        val nEnd = nStart + sFind.length

        val spannable = tv6!!.text as Spannable
        spannable.setSpan(ForegroundColorSpan(Color.WHITE), nStart, nEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannable.setSpan(BackgroundColorSpan(Color.RED), nStart, nEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannable.setSpan(StyleSpan(Typeface.BOLD), nStart, nEnd, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
    }
}