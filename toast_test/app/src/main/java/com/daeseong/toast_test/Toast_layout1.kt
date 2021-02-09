package com.daeseong.toast_test

import android.content.Context
import android.view.View
import android.widget.TextView
import android.widget.Toast

class Toast_layout1 {

    private val tag: String = Toast_layout1::class.java.simpleName

    private lateinit var toast: Toast
    private lateinit var tv1: TextView
    private lateinit var vlayout : View

    constructor(context: Context, sMsg: String) : this(context, sMsg, Toast.LENGTH_SHORT)

    constructor(context: Context, sMsg: String, duration: Int) {

        vlayout = View.inflate(context, R.layout.toast_layout1, null)
        tv1 = vlayout.findViewById<TextView>(R.id.tvtoast)
        tv1!!.text = sMsg

        toast = Toast(context)
        toast.duration = duration
        toast.view = vlayout
    }

    fun show() {
        toast.show()
    }
}
