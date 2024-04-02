package com.daeseong.floatingview_test

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FloatingView1(viewGroup: ViewGroup) {

    private val floatingView: View
    private val tv1: TextView
    private val tv2: TextView
    private val cLClose: View

    init {
        floatingView = LayoutInflater.from(viewGroup.context).inflate(R.layout.floating_layout1, viewGroup, false)

        tv1 = floatingView.findViewById(R.id.tv1)
        tv2 = floatingView.findViewById(R.id.tv2)

        cLClose = floatingView.findViewById(R.id.cLClose)
        cLClose.setOnClickListener(object : OnSingleClickListener() {
            override fun onSingleClick(view: View) {
                floatingView.visibility = View.GONE
            }
        })
    }

    fun getFloatingView(): View {
        return floatingView
    }

    fun setText1(sText: String, color: Int) {
        tv1.text = sText
        tv1.setTextColor(color)
    }

    fun setText2(sText: String, color: Int) {
        tv2.text = sText
        tv2.setTextColor(color)
    }
}
