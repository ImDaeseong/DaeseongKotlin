package com.daeseong.spannable_test

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.java.simpleName

    private var et1: EditText? = null
    private var tv1: TextView? = null
    private var button1: Button? = null
    private var button2: Button? = null

    private var sEdit: String? = null

    private val sData = """서울
구름많음
온도 1.6°
미세 좋음
초미세 좋음
[https://weather.naver.com/today/01110580?cpName=KMA]

 춘천
<!--구름많음-->
[https://weather.naver.com/today/01150101?cpName=KMA]

강릉
<!--흐림-->
  온도 5.6° [https://weather.naver.com/today/16113114?cpName=KMA]

청주
초미세 좋음 [https://weather.naver.com/today/07170630?cpName=KMA] <!--흐림-->

대전
미세 보통
초미세 보통
[https://weather.naver.com/today/06110517?cpName=KMA]

"""

/*
    private val sData = """서울
구름많음
온도 1.6°
미세 좋음
초미세 좋음
"https://weather.naver.com/today/01110580?cpName=KMA"

 춘천
<!--구름많음-->
"https://weather.naver.com/today/01150101?cpName=KMA"

강릉
<!--흐림-->
  온도 5.6° "https://weather.naver.com/today/16113114?cpName=KMA"

청주
초미세 좋음 "https://weather.naver.com/today/07170630?cpName=KMA" <!--흐림-->

대전
미세 보통
초미세 보통
"https://weather.naver.com/today/06110517?cpName=KMA"

"""
*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        et1 = findViewById<View>(R.id.et1) as EditText
        tv1 = findViewById<View>(R.id.tv1) as TextView

        //textview scroll 추가
        tv1!!.movementMethod = ScrollingMovementMethod()

        et1!!.setText(sData)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener(View.OnClickListener {

            sEdit = CheckReturn(et1!!.text.toString())
            tv1!!.text = sEdit
        })

        button2 = findViewById<View>(R.id.button2) as Button
        button2!!.setOnClickListener(View.OnClickListener {

            CheckLink(et1!!.text.toString())
        })
    }

    private fun CheckLink(sInput: String) {

        //[] 감싼 부분이 링크
        val spannableString = SpannableString(sInput)
        val matcher = Pattern.compile("\\[[^\\]]+\\]").matcher(sInput)
        var sFind: String
        var sUrl: String
        var nLength: Int

        while (matcher.find()) {
            sFind = matcher.group()
            nLength = sFind.length
            sUrl = sFind.substring(1, nLength - 1)

            //Log.e(tag, sUrl)
            spannableString.setSpan(ClickableSpanEx(this, sUrl), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tv1!!.text = spannableString
        tv1!!.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun CheckReturn(sInput: String): String? {

        //\n(줄바꿈) 기준으로 문자열 정리
        val spannableString = SpannableString(sInput)
        val pattern = Pattern.compile("\n")
        val matcher = pattern.matcher(spannableString)
        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            spannableString.setSpan(AbsoluteSizeSpan(15, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        //Log.e(tag, spannableString.toString())

        return spannableString.toString()
    }
}