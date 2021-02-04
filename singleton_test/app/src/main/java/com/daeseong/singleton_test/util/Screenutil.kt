package com.daeseong.singleton_test.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager


class Screenutil {

    private val tag: String = Screenutil::class.java.simpleName;

    companion object {
        private var instance: Screenutil? = null
        fun getInstance(): Screenutil {
            if (instance == null) {
                instance = Screenutil()
            }
            return instance as Screenutil
        }
    }

    private var windowManager : WindowManager? = null;

    fun init(context: Context) {
        windowManager = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
    }

    fun getScreenWidthPx(): Int {
        val dm = DisplayMetrics()
        windowManager!!.defaultDisplay.getMetrics(dm)
        return dm.widthPixels
    }

    fun getScreenHeightPx(): Int {
        val dm = DisplayMetrics()
        windowManager!!.defaultDisplay.getMetrics(dm)
        return dm.heightPixels
    }
}