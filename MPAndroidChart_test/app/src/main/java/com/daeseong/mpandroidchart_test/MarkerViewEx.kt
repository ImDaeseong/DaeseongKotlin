package com.daeseong.mpandroidchart_test

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF

class MarkerViewEx(context: Context, layoutResource: Int) : MarkerView(context, layoutResource) {

    private var tv1: TextView = findViewById(R.id.tv1)

    override fun refreshContent(e: Entry?, highlight: Highlight?) {
        tv1.text = "값: ${e?.y}"
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        return MPPointF(-(width / 2).toFloat(), -height.toFloat())
    }
}
