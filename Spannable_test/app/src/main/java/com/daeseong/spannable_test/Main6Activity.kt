package com.daeseong.spannable_test

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.text.style.AbsoluteSizeSpan
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.regex.Pattern

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.java.simpleName

    private lateinit var et1: EditText
    private lateinit var tv1: TextView
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button

    private var sEdit: String? = null

    private val sData = """서울          서울         서울
구름많음
온도 1.6°
미세 좋음
초미세 좋음
[https://weather.naver.com/today/01110580?cpName=KMA] 링크 처리 1 

 춘천         춘천 
<!--구름많음-->
   링크 처리 2 [https://weather.naver.com/today/01150101?cpName=KMA]

강릉__강릉
<!--흐림-->
  온도 5.6° [https://weather.naver.com/today/16113114?cpName=KMA]   링크 처리 3

!청주       청주!
초미세 좋음 [https://weather.naver.com/today/07170630?cpName=KMA] 링크 처리 4  <!--흐림-->

@대전                            대전#
미세 보통
초미세 보통
링크 처리 5 [https://weather.naver.com/today/06110517?cpName=KMA]   링크 처리 5
"""

    private val sData1 = """서울          서울         서울
구름많음
온도 1.6°
미세 좋음
초미세 좋음
https://weather.naver.com/today/01110580?cpName=KMA 링크 처리 1 

 춘천         춘천 
<!--구름많음-->
   링크 처리 2 https://weather.naver.com/today/01150101?cpName=KMA

강릉__강릉
<!--흐림-->
  온도 5.6° https://weather.naver.com/today/16113114?cpName=KMA   링크 처리 3

!청주       청주!
초미세 좋음 https://weather.naver.com/today/07170630?cpName=KMA 링크 처리 4  <!--흐림-->

@대전                            대전#
미세 보통
초미세 보통
링크 처리 5 https://weather.naver.com/today/06110517?cpName=KMA   링크 처리 5
"""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        et1 = findViewById(R.id.et1)
        tv1 = findViewById(R.id.tv1)

        // textview scroll 추가
        tv1.movementMethod = ScrollingMovementMethod()

        et1.setText(sData)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            sEdit = checkReturn(et1.text.toString())
            tv1.text = sEdit
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            checkLink(et1.text.toString())
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            et1.setText(sData1)
            checkHttp(et1.text.toString())
        }
    }

    private fun checkLink(sInput: String) {
        // [] 감싼 부분이 링크
        val spannableString = SpannableString(sInput)
        val matcher = Pattern.compile("\\[[^\\]]+\\]").matcher(sInput)
        var sFind: String
        var sUrl: String
        var nLength: Int

        while (matcher.find()) {
            sFind = matcher.group()
            nLength = sFind.length
            sUrl = sFind.substring(1, nLength - 1)
            spannableString.setSpan(ClickableSpanEx(this, sUrl), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        tv1.text = spannableString
        tv1.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun checkHttp(sInput: String) {
        val sRegex = "(http|ftp|https):\\/\\/[\\w\\-_]+(\\.[\\w\\-_]+)+([\\w\\-\\.,@?^=%&amp;:/~\\+#]*[\\w\\-\\@?^=%&amp;/~\\+#])?"

        val spannableString = SpannableString(sInput)
        val matcher = Pattern.compile(sRegex).matcher(sInput)
        var sFind: String
        var sUrl: String
        var nLength: Int

        while (matcher.find()) {
            sFind = matcher.group()
            nLength = sFind.length
            sUrl = sFind.substring(0, nLength)
            spannableString.setSpan(ClickableSpanEx(this, sUrl), matcher.start(), matcher.end(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }

        tv1.text = spannableString
        tv1.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun checkReturn(sInput: String): String? {
        // \n(줄바꿈) 기준으로 문자열 정리
        val spannableString = SpannableString(sInput)
        val pattern = Pattern.compile("\n")
        val matcher = pattern.matcher(spannableString)

        while (matcher.find()) {
            val start = matcher.start()
            val end = matcher.end()
            spannableString.setSpan(AbsoluteSizeSpan(15, true), start, end, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        }
        return spannableString.toString()
    }
}