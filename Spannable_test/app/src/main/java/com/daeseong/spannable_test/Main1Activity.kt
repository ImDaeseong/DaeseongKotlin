package com.daeseong.spannable_test

import android.graphics.Color
import android.os.Bundle
import android.text.Spannable
import android.text.Spanned
import android.text.style.BackgroundColorSpan
import android.text.style.ForegroundColorSpan
import android.text.style.UnderlineSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null
    private var tv4: TextView? = null

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

        settv1()
        settv2()
        settv3()
        settv4()
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
}