package com.daeseong.toast_test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import android.widget.Toast

class ToastLayout1(context: Context, message: String, duration: Int = Toast.LENGTH_SHORT) {

    private val toast: Toast
    private val vlayout: View
    private val tv1: TextView

    init {
        vlayout = LayoutInflater.from(context).inflate(R.layout.toast_layout1, null)
        tv1 = vlayout.findViewById(R.id.tvtoast)
        tv1.text = message

        toast = Toast(context)
        toast.duration = duration
        toast.view = vlayout
    }

    fun show() {
        toast.show()
    }
}
