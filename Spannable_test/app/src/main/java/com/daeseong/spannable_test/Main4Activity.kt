package com.daeseong.spannable_test

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private var lv: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        lv = findViewById(R.id.lv)

        settv1()
        settv2()
        settv3()
        settv4()
        settv5()
        settv6()
        settv7()
        settv8()
    }

    private fun settv1() {

        //글자색
        val spannableString1 = SpannableString("텍스트 링크클릭 효과1")
        spannableString1.setSpan(ForegroundColorSpan(Color.RED),0,3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        //TextView 생성
        val tv1 = TextView(this)
        tv1.textSize = 20f
        tv1.textAlignment = View.TEXT_ALIGNMENT_CENTER
        //tv1.setBackgroundColor(Color.RED)
        tv1.text = spannableString1

        //추가
        lv!!.addView(tv1)
    }

    private fun settv2() {

        //배경색
        val spannableString2 = SpannableString("텍스트 링크클릭 효과2")
        spannableString2.setSpan(BackgroundColorSpan(Color.RED), 0, 3, 0)

        //TextView 생성
        val tv2 = TextView(this)
        tv2.textSize = 20f
        tv2.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv2.text = spannableString2

        //추가
        lv!!.addView(tv2)
    }

    private fun settv3() {

        //밑줄 효과
        val spannableString3 = SpannableString("텍스트 링크클릭 효과3")
        spannableString3.setSpan(UnderlineSpan(), 4, 6, 0)

        //TextView 생성
        val tv3 = TextView(this)
        tv3.textSize = 20f
        tv3.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv3.text = spannableString3

        //추가
        lv!!.addView(tv3)
    }

    private fun settv4() {

        //링크 효과
        val spannableString4 = SpannableString("텍스트 링크클릭 효과4")
        spannableString4.setSpan(URLSpan("https://naver.com"),0,3, Spanned.SPAN_INCLUSIVE_INCLUSIVE)

        //TextView 생성
        val tv4 = TextView(this)
        tv4.textSize = 20f
        tv4.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv4.text = spannableString4

        //추가
        lv!!.addView(tv4)
    }

    private fun settv5() {

        //링크 클릭 효과
        val spannableStringBuilder = SpannableStringBuilder("텍스트 링크클릭 효과5")
        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://naver.com"))
                startActivity(intent)
            }
        }
        spannableStringBuilder.setSpan(clickableSpan, 4, 9, Spanned.SPAN_EXCLUSIVE_INCLUSIVE)

        //TextView 생성
        val tv5 = TextView(this)
        tv5.textSize = 20f
        tv5.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv5.text = spannableStringBuilder

        //클릭 가능하게 설정
        tv5.movementMethod = LinkMovementMethod.getInstance()

        //추가
        lv!!.addView(tv5)
    }

    private fun settv6() {

        //html 효과
        val sHtml = "<b>" + "html 테스트1" + "</b>"

        //TextView 생성
        val tv6 = TextView(this)
        tv6.textSize = 20f
        tv6.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv6.text = Html.fromHtml(sHtml)

        //추가
        lv!!.addView(tv6)
    }

    private fun settv7() {
        val sMsg = "html 테스트2"

        //html 효과
        val sHtml = "<font color='#66ccff'>$sMsg</font>"

        //TextView 생성
        val tv7 = TextView(this)
        tv7.textSize = 20f
        tv7.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv7.text = Html.fromHtml(sHtml)

        //추가
        lv!!.addView(tv7)
    }

    private fun settv8() {

        val sMsg = "html 테스트3"

        //html 효과
        val sHtml = "<a href='https://naver.com'><font color='#66ccff'>$sMsg</font></a>"

        //TextView 생성
        val tv8 = TextView(this)
        tv8.textSize = 20f
        tv8.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv8.text = Html.fromHtml(sHtml)

        //클릭 가능하게 설정
        tv8.movementMethod = LinkMovementMethod.getInstance()

        //추가
        lv!!.addView(tv8)
    }
}