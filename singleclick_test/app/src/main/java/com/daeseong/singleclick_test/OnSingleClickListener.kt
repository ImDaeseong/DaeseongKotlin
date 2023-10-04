package com.daeseong.singleclick_test

import android.view.View

abstract class OnSingleClickListener(private val delayTime: Long = 500) : View.OnClickListener {

    private var lastTime: Long = 0

    override fun onClick(view: View) {
        if (System.currentTimeMillis() - lastTime < delayTime) {
            return
        }
        onSingleClick(view)
        lastTime = System.currentTimeMillis()
    }

    abstract fun onSingleClick(view: View)
}
