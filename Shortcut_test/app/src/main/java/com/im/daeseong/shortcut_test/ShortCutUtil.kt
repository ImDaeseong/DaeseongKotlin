package com.im.daeseong.shortcut_test

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.util.Log
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat

object ShortCutUtil {

    private val tag = ShortCutUtil::class.java.simpleName

    fun checkShortCut(context: Context, sPackageName: String, sLabel: String, sID:String, sData:String) {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
            Log.e(tag, "홈 화면에 바로가기 지원")
            createShortCut(context, sPackageName, sLabel, sID, sData)
        } else {
            Log.e(tag, "홈 화면에 바로가기 미지원")
        }
    }

    private fun createShortCut(context: Context, sPackageName: String, sLabel: String, sID:String, sData:String) {

        val sShortcutId = "${sPackageName}_$sID"

        // 이미 고정된 바로가기를 확인
        val pinnedShortcuts = getPinnedShortcuts(context)
        val existingShortcut = pinnedShortcuts.find {
            it.id == sShortcutId
        }

        //이미 존재하는지 여부 체크
        var bUpdate = false
        if (existingShortcut != null) {
            Log.e(tag, "바로가기 이미 존재: $sShortcutId")
            bUpdate = true
        }

        // 실행할 인텐트 생성
        val intent = Intent(context, SplashActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("pkg", sPackageName)
            putExtra("userId", sID)
            putExtra("userData", sData)
        }

        // 바로가기 정보 생성
        val shortcutInfo = ShortcutInfoCompat.Builder(context, sShortcutId)
            .setIntent(intent)
            .setShortLabel(sLabel)
            .setLongLabel(sLabel)
            .setIcon(IconCompat.createWithResource(context, R.drawable.ic_launcher_foreground))
            .build()

        if (bUpdate) {

            Log.e(tag, "바로가기 업데이트: $sShortcutId")

            ShortcutManagerCompat.updateShortcuts(context, listOf(shortcutInfo ))

        } else {

            Log.e(tag, "바로가기 생성: $sShortcutId")

            ShortcutManagerCompat.requestPinShortcut(context, shortcutInfo, null)
        }
    }

    //바로가기 생성 목록
    private fun getPinnedShortcuts(context: Context): List<ShortcutInfo> {
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)
        return shortcutManager?.pinnedShortcuts ?: emptyList()
    }
}