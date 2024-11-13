package com.im.daeseong.shortcut_test

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat

object ShortCutUtil {

    private val tag = ShortCutUtil::class.java.simpleName

    fun checkShortCut(context: Context) {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
            Log.e(tag, "홈 화면에 바로가기 지원")
            createShortCut(context)
        } else {
            Log.e(tag, "홈 화면에 바로가기 미지원")
        }
    }

    private fun createShortCut(context: Context) {
        val sPackageName = "com.im.daeseong.Shortcut_test"
        val sID = "testID"
        val sLabel = "나의앱 바로가기"
        val sShortcutId = "${sPackageName}_$sID"

        // 실행할 인텐트 생성
        val intent = Intent(context, SplashActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("pkg", sPackageName)
            putExtra("userId", sID)
        }

        // 바로가기 정보 생성
        val shortcutInfo = ShortcutInfoCompat.Builder(context, sShortcutId)
            .setIntent(intent)
            .setShortLabel(sLabel)
            .setLongLabel(sLabel)
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground))
            .build()

        // 바로가기 생성 요청
        ShortcutManagerCompat.requestPinShortcut(context, shortcutInfo, null)
    }
}