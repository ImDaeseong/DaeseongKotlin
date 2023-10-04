package com.daeseong.spannable_test

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.LinkMovementMethod
import android.text.style.*
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat

class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private lateinit var lv: LinearLayout

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

    private fun addTextView(text: CharSequence) : TextView {
        val tv = TextView(this)
        tv.textSize = 20f
        tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
        tv.text = text
        lv.addView(tv)
        return tv
    }

    private fun settv1() {

        //글자색
        val spannableString1 = SpannableString("텍스트 링크클릭 효과1")
        spannableString1.setSpan(ForegroundColorSpan(Color.RED), 0, 3, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        addTextView(spannableString1)
    }

    private fun settv2() {

        //배경색
        val spannableString2 = SpannableString("텍스트 링크클릭 효과2")
        spannableString2.setSpan(BackgroundColorSpan(Color.RED), 0, 3, 0)
        addTextView(spannableString2)
    }

    private fun settv3() {

        //밑줄 효과
        val spannableString3 = SpannableString("텍스트 링크클릭 효과3")
        spannableString3.setSpan(UnderlineSpan(), 4, 6, 0)
        addTextView(spannableString3)
    }

    private fun settv4() {

        //링크 효과
        val spannableString4 = SpannableString("텍스트 링크클릭 효과4")
        spannableString4.setSpan(URLSpan("https://naver.com"), 0, 3, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        addTextView(spannableString4)
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
        val tv5 = addTextView(spannableStringBuilder)
        tv5.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun settv6() {

        //html 효과
        val sHtml = "<b>" + "html 테스트1" + "</b>"
        val tv6 = addTextView(HtmlCompat.fromHtml(sHtml, HtmlCompat.FROM_HTML_MODE_LEGACY))
    }

    private fun settv7() {

        val sMsg = "html 테스트2"
        val sHtml = "<font color='#66ccff'>$sMsg</font>"
        val tv7 = addTextView(HtmlCompat.fromHtml(sHtml, HtmlCompat.FROM_HTML_MODE_LEGACY))
    }

    private fun settv8() {

        val sMsg = "html 테스트3"
        val sHtml = "<a href='https://naver.com'><font color='#66ccff'>$sMsg</font></a>"
        val tv8 = addTextView(HtmlCompat.fromHtml(sHtml, HtmlCompat.FROM_HTML_MODE_LEGACY))
        tv8.movementMethod = LinkMovementMethod.getInstance()
    }
}