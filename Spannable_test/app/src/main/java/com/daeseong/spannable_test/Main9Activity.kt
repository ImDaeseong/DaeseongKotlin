package com.daeseong.spannable_test

import android.os.Bundle
import android.text.SpannableString
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main9Activity : AppCompatActivity() {

    private val tag = Main8Activity::class.java.simpleName

    private lateinit var et1: EditText
    private lateinit var tv1: TextView
    private lateinit var button1: Button

    private var sEdit: String? = null
    private val list = ArrayList<String>()

    private val sData = """서울          서울         서울
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
        setContentView(R.layout.activity_main9)

        et1 = findViewById(R.id.et1)
        tv1 = findViewById(R.id.tv1)

        // textview scroll 추가
        tv1.movementMethod = ScrollingMovementMethod()

        // 데이터 검색 타입
        et1.setText(sData)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            tv1.text = ""
            sEdit = et1.text.toString()
            tv1.text = sEdit

            setLink(tv1.text.toString())
        }

    }

    private fun setLink(sValue: String) {
        val spannableString = SpannableString(sValue)

        list.clear()
        getLinkList(sValue)

        for (sLink in list) {
            val start = sValue.indexOf(sLink)
            val end = start + sLink.length
            spannableString.setSpan(ClickableSpanEx(this, sLink), start, end, 0)
        }

        tv1.text = spannableString
        tv1.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun getLinkList(sInput: String) {
        val sRead: List<String?> = sInput.split("\n")
        for (sCheck in sRead) {
            if (sCheck != null && sCheck.contains("https")) {
                val slast = sCheck.substring(sCheck.indexOf("https"))
                val nEnd = slast.indexOf(" ")
                val slink = if (nEnd != -1) slast.substring(0, nEnd) else slast
                list.add(slink)
            }
        }
    }
}