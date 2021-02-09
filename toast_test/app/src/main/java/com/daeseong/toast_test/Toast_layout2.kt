package com.daeseong.toast_test

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast


class Toast_layout2(context: Context, sMsg: String) : Toast(context) {

    private val tag: String = Toast_layout2::class.java.simpleName

    private var tv1: TextView
    private var vlayout : View = View.inflate(context, R.layout.toast_layout1, null)

    init {

        tv1 = vlayout.findViewById<TextView>(R.id.tvtoast)
        tv1!!.text = sMsg

        view = vlayout
        duration = LENGTH_LONG
        show()
    }
}

