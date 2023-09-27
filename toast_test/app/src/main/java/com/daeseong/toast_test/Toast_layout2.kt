package com.daeseong.toast_test

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast

class ToastLayout2(context: Context, sMsg: String) : Toast(context) {

    init {
        val vlayout = LayoutInflater.from(context).inflate(R.layout.toast_layout1, null)
        val tv1 = vlayout.findViewById<TextView>(R.id.tvtoast)
        tv1.text = sMsg

        view = vlayout
        duration = LENGTH_LONG
        show()
    }
}
