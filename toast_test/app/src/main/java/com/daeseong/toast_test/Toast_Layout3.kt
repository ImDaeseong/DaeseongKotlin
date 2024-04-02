package com.daeseong.toast_test

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast

class ToastLayout3(private val context: Context, private val message: String, private val duration: Int = Toast.LENGTH_SHORT) {

    fun show() {

        val inflater = LayoutInflater.from(context)
        val layout = inflater.inflate(R.layout.toast_layout1, null)

        val tv1: TextView = layout.findViewById(R.id.tvtoast)
        tv1.text = message

        val toast = Toast(context)
        toast.view = layout
        toast.setGravity(Gravity.BOTTOM or Gravity.FILL_HORIZONTAL, 0, 0)//전체 넓이 반영
        toast.duration = duration
        toast.show()
    }
}
