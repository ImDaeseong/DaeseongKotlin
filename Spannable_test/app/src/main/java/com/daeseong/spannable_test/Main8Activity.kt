package com.daeseong.spannable_test

import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.SpannedString
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main8Activity : AppCompatActivity() {

    private val tag = Main8Activity::class.java.simpleName

    private lateinit var et1: EditText
    private lateinit var tv1: TextView
    private lateinit var button1: Button

    private var sEdit: String? = null

    private val stringArray = mutableListOf<SpannedString>()

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

    private val bHttp = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main8)

        et1 = findViewById(R.id.et1)
        tv1 = findViewById(R.id.tv1)

        // Text view scroll 추가
        tv1.movementMethod = ScrollingMovementMethod()

        // 데이터 검색 타입
        et1.setText(if (bHttp) sData1 else sData)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            stringArray.clear()
            tv1.text = ""

            sEdit = et1.text.toString()

            // 데이터 검색 타입
            if (bHttp) {
                checkLinkhttps(sEdit!!)
            } else {
                checkLink(sEdit!!)
            }

            var finalString = SpannedString("")
            for (item in stringArray) {
                finalString = TextUtils.concat(finalString, item) as SpannedString
            }
            tv1.text = finalString
            tv1.movementMethod = LinkMovementMethod.getInstance()
        }
    }

    private fun checkLinkhttps(sInput: String) {

        var slink1: String
        var slink2 : String = ""
        var slink2_Sub: String?
        var slink3: String
        var slink4: String?
        var slast: String
        var nEnd : Int = 0

        val sRead: Array<String?> = sInput.split("\n".toRegex()).toTypedArray()
        for (i in sRead.indices) {

            if (sRead[i] != null) {

                val sCheck = sRead[i]

                if (sCheck!!.indexOf("https") >= 0) {

                    //링크 아닌부분
                    slink1 = sCheck.substring(0, sCheck.indexOf("https"))

                    //링크 부분
                    slast = sCheck.substring(sCheck.indexOf("https"))

                    nEnd = slast.indexOf(" ")
                    slink2 = if (nEnd != -1) {
                        slast.substring(0, nEnd)
                    } else {
                        slast
                    }

                    //링크 끝나는 부분
                    slink3 = if (nEnd == -1) {
                        ""
                    } else {
                        slast.substring(nEnd + 1)
                    }

                    val s1 = SpannableString(slink1)

                    //링크 보여주는 부분
                    slink2_Sub = getNameURL(slink2)
                    val s2 = SpannableString("$slink2_Sub ") //여기에 공백을 하나 넎어야만 전체 라인 클릭이 않된다.
                    s2.setSpan(
                        ClickableSpanEx(this, slink2),
                        0,
                        slink2_Sub!!.length,
                        Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                    )

                    val s3 = SpannableString(slink3)

                    stringArray.add((TextUtils.concat(s1, s2, s3, "\n") as SpannedString))
                } else {

                    //링크 없는 라인
                    slink4 = sCheck
                    val s4 = SpannableString(slink4)

                    stringArray.add((TextUtils.concat("", s4, "\n") as SpannedString))
                }
            }
        }
    }

    private fun checkLink(sInput: String) {

        var slink1: String
        var slink2: String
        var slink2_Sub: String?
        var slink3: String?
        var slink4: String

        val sRead: Array<String?> = sInput.split("\n".toRegex()).toTypedArray()
        for (i in sRead.indices) {

            if (sRead[i] != null) {

                val sCheck = sRead[i]

                if (sCheck!!.indexOf("[") >= 0 && sCheck.indexOf("]") >= 0) {

                    //링크 아닌부분
                    slink1 = sCheck.substring(0, sCheck.indexOf("["))

                    //링크 부분
                    slink2 = sCheck.substring(sCheck.indexOf("[") + 1, sCheck.indexOf("]"))

                    //링크 끝나는 부분
                    slink3 = sCheck.substring(sCheck.indexOf("]") + 1)

                    val s1 = SpannableString(slink1)

                    //링크 보여주는 부분
                    slink2_Sub = getNameURL(slink2)
                    val s2 = SpannableString("$slink2_Sub ")//여기에 공백을 하나 넎어야만 전체 라인 클릭이 않된다.
                    if (slink2_Sub != null) {
                        s2.setSpan(ClickableSpanEx(this, slink2),0, slink2_Sub.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                    }

                    val s3 = SpannableString(slink3)

                    stringArray.add((TextUtils.concat(s1, s2, s3, "\n") as SpannedString))
                } else {

                    //링크 없는 라인
                    slink4 = sCheck
                    val s4 = SpannableString(slink4)

                    stringArray.add((TextUtils.concat("", s4, "\n") as SpannedString))
                }
            }
        }
    }

    private fun getNameURL(sInput: String): String? {

        var sReturn = ""

        val nStart = sInput.lastIndexOf("/") + 1
        var nEnd = sInput.lastIndexOf("?")

        if (nEnd < 0) {
            nEnd = sInput.length
        }

        sReturn = sInput.substring(nStart, nEnd)
        return sReturn
    }
}