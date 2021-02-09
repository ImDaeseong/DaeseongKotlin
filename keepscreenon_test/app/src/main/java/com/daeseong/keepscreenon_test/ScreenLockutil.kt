package com.daeseong.keepscreenon_test

import android.app.Activity
import android.view.WindowManager


class ScreenLockutil {

    private val tag = ScreenLockutil::class.java.simpleName

    //화면이 꺼지지 않게 고정
    fun Lock(activity: Activity) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    //해제
    fun Unlock(activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}