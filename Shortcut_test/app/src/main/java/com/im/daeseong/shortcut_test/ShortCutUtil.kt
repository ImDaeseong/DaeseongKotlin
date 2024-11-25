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

    fun checkShortCut(context: Context, sLabel: String, sID:String, sData:String) {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(context)) {
            Log.e(tag, "홈 화면에 바로가기 지원")
            createShortCut(context, sLabel, sID, sData)
        } else {
            Log.e(tag, "홈 화면에 바로가기 미지원")
        }
    }

    private fun createShortCut(context: Context, sLabel: String, sID:String, sData:String) {

        val sShortcutId = "${context.packageName}_$sID"

        //이미 존재하는지 여부 체크
        var bUpdate = false
        var existingShortcut: ShortcutInfo? = null

        // 이미 고정된 바로가기를 확인
        val pinnedShortcuts = getPinnedShortcuts(context)
        for (shortcut in pinnedShortcuts) {
            if (shortcut.id == sShortcutId) {
                bUpdate = true
                existingShortcut = shortcut
                break
            }
        }

        if (bUpdate) {
            Log.e(tag, "바로가기 이미 존재: $sShortcutId")

            val shortcutManager = context.getSystemService(ShortcutManager::class.java)
            if (existingShortcut?.isEnabled == false) {
                try {
                    Log.e(tag, "바로가기가 비활성화된 상태라면 활성화: $sShortcutId")
                    shortcutManager?.enableShortcuts(listOf(sShortcutId))
                } catch (ex: Exception) {
                }
            }
        }

        // 실행할 인텐트 생성
        val intent = Intent(context, SplashActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("pkg", context.packageName)
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

    //바로가기 존재 여부
    fun isPinnedShortcuts(context: Context, sID: String): Boolean {
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)
        val pinnedShortcuts = shortcutManager?.pinnedShortcuts ?: emptyList()
        return pinnedShortcuts.any { it.id == "${context.packageName}_$sID" }
    }

    //바로가기 비활성화 -보안상의 이유로 앱에서 바탕화면의 바로가기를 직접 삭제하는 것은 제한
    fun removePinnedShortcut(context: Context, sID: String) {

        val shortcutId = "${context.packageName}_$sID"

        val shortcutManager = context.getSystemService(ShortcutManager::class.java)

        if (isPinnedShortcuts(context, sID)) {

            val shortcutsToRemove = listOf(shortcutId)

            try {

                // 바로가기 비활성화
                shortcutManager?.apply {
                    removeDynamicShortcuts(shortcutsToRemove)
                    disableShortcuts(shortcutsToRemove)
                }

            } catch (e: Exception) {
            }
        }
    }
}