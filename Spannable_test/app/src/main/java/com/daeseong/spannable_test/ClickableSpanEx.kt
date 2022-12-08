package com.daeseong.spannable_test

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.text.TextPaint
import android.text.style.ClickableSpan
import android.view.View

class ClickableSpanEx(private val context: Context, private val sUrl: String) : ClickableSpan() {

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
