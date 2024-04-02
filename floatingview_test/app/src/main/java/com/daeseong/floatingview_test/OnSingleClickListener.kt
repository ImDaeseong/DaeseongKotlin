package com.daeseong.floatingview_test

import android.view.View

abstract class OnSingleClickListener(private val delayTime: Long = 500) : View.OnClickListener {

    private var lastClickTime: Long = 0

    override fun onClick(view: View) {
        val currentTime = System.currentTimeMillis()
        if (currentTime - lastClickTime < delayTime) {
            return
        }

        onSingleClick(view)
        lastClickTime = currentTime
    }

    abstract fun onSingleClick(view: View)
}
