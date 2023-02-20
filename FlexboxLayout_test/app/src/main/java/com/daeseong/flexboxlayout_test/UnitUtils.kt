package com.daeseong.flexboxlayout_test

import android.content.Context

object UnitUtils {
    fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    fun px2dip(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue / scale + 0.5f).toInt()
    }
}