package com.daeseong.singleclick_test

import android.view.View

abstract class OnSingleClickListener : View.OnClickListener {

    private var lastTime: Long = 0
    private var delayTime: Long = 500

    constructor() {
    }

    constructor(delayTime: Long) {

        this.delayTime = delayTime
    }

    override fun onClick(view: View) {

        if (System.currentTimeMillis() - lastTime < delayTime) {
            return
        }
        onSingleClick(view)
        lastTime = System.currentTimeMillis()
    }

    abstract fun onSingleClick(view: View)
}