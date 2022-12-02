package com.daeseong.spannable_test

import android.content.Intent
import android.graphics.Color
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

    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null
    private var tv4: TextView? = null
    private var tv5: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tv1 = findViewById<View>(R.id.tv1) as TextView
        tv1!!.text = "텍스트 색상 변경"

        tv2 = findViewById<View>(R.id.tv2) as TextView
        tv2!!.text = "텍스트 색상 변경"

        tv3 = findViewById<View>(R.id.tv3) as TextView
        tv3!!.text = "텍스트 색상 변경"

        tv4 = findViewById<View>(R.id.tv4) as TextView
        tv4!!.text = "텍스트 색상 변경"

        tv5 = findViewById<View>(R.id.tv5) as TextView
        tv5!!.text = "텍스트 색상 변경"

        settv1()
        settv2()
        settv3()
        settv4()
        settv5()
    }

    private fun settv1() {

        val spannableString = SpannableString(tv1!!.text)
        spannableString.setSpan(ForegroundColorSpan(Color.RED), 0, 3, 0)
        tv1!!.text = spannableString
    }

    private fun settv2() {

        val spannableString = SpannableString(tv2!!.text)
        spannableString.setSpan(BackgroundColorSpan(Color.RED), 0, 3, 0)
        tv2!!.text = spannableString
    }

    private fun settv3() {

        val spannableString = SpannableString(tv3!!.text)
        spannableString.setSpan(UnderlineSpan(), 4, 6, 0)
        tv3!!.text = spannableString
    }

    private fun settv4() {

        val spannableString = SpannableString(tv4!!.text)
        spannableString.setSpan(URLSpan("https://naver.com"),0,3, Spanned.SPAN_INCLUSIVE_INCLUSIVE)
        tv4!!.text = spannableString
    }

    private fun settv5() {

        val spannableString = SpannableString(tv5!!.text)

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(widget: View) {

                val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://naver.com"))
                startActivity(intent)
            }
        }

        spannableString.setSpan(clickableSpan, 4, 9, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)

        tv5!!.text = spannableString

        //클릭 가능하게 설정
        tv5!!.movementMethod = LinkMovementMethod.getInstance()
    }
}