package com.daeseong.keepscreenon_test

import android.app.Activity
import android.view.WindowManager

class ScreenLockUtil {

    private val tag = ScreenLockUtil::class.java.simpleName

    //화면이 꺼지지 않게 고정
    fun lock(activity: Activity) {
        activity.window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }

    //해제
    fun unlock(activity: Activity) {
        activity.window.clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
    }
}
