package com.daeseong.spannable_test

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main5Activity : AppCompatActivity() {

    private val tag = Main5Activity::class.java.simpleName

    var sUrl1 = "https://naver.com"
    var sUrl2 = "https://google.com"

    private var tv1: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        tv1 = findViewById<View>(R.id.tv1) as TextView
        tv1!!.text = "텍스트 링크클릭 효과 \r\n텍스트 링크클릭 효과"

        settv1()
    }

    private fun settv1() {

        val spannableString = SpannableString(tv1!!.text)

        spannableString.setSpan(ClickableSpanEx(this, sUrl1),4,8, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        spannableString.setSpan(ClickableSpanEx(this, sUrl2),17,22, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
        tv1!!.text = spannableString
        tv1!!.movementMethod = LinkMovementMethod.getInstance()
    }

    private class ClickableSpanEx(private val context: Context, private val sUrl: String) : ClickableSpan() {

        override fun updateDrawState(ds: TextPaint) {
            super.updateDrawState(ds)

            ds.color = Color.RED
            ds.isUnderlineText = false
        }

        override fun onClick(widget: View) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(sUrl))
            context.startActivity(intent)
        }
    }
}