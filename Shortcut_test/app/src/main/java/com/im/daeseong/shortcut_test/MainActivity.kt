package com.im.daeseong.shortcut_test

import android.content.Context
import android.content.Intent
import android.content.pm.ShortcutInfo
import android.content.pm.ShortcutManager
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat

class MainActivity : AppCompatActivity() {

    companion object {
        private val tag = MainActivity::class.java.simpleName
    }

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            checkShortCut()
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            updateShortCut()
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {

            val ids = getPinnedShortcuts(this)
            for (id in ids) {
                Log.e(tag, id.toString())
                //Log.e(tag, id.id)
            }
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {

            ShortCutUtil.checkShortCut(
                context = this,
                sPackageName = packageName,
                sLabel = "바로가기",
                sID = "testID123",
                sData = "전달할 추가 데이터"
            )

            ShortCutUtil.checkShortCut(
                context = this,
                sPackageName = packageName,
                sLabel = "바로가기 업데이트",
                sID = "testID123",
                sData = "전달할 추가 데이터 업데이트"
            )

        }
    }

    private fun checkShortCut() {

        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            createShortCut()
        } else {
            Log.e(tag, "홈 화면에 바로가기 미지원")
        }
    }

    private fun createShortCut() {

        val sPackageName = "com.im.daeseong.Shortcut_test"
        val sID = "testID"
        val sLabel = "나의앱 바로가기"
        val sShortcutId = "${sPackageName}_${sID}"

        // 이미 고정된 바로가기를 확인
        val pinnedShortcuts = getPinnedShortcuts(this)
        val existingShortcut = pinnedShortcuts.find { it.id == sShortcutId }

        if (existingShortcut != null) {
            Log.d(tag, "바로가기 이미 존재: $sShortcutId")
            return
        }

        // 실행할 인텐트 생성
        val intent = Intent(this, SplashActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("pkg", sPackageName)
            putExtra("userId", sID)
            putExtra("userData", "바로가기 정보 생성")
        }

        // 바로가기 정보 생성
        val shortcutInfo = ShortcutInfoCompat.Builder(this, sShortcutId)
            .setIntent(intent)
            .setShortLabel(sLabel)
            .setLongLabel(sLabel)
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground))
            .build()

        // 바로가기 생성 요청
        ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
    }

    private fun updateShortCut() {

        val sPackageName = "com.im.daeseong.Shortcut_test"
        val sID = "testID"
        val sLabel = "나의앱 바로가기 (업데이트)"
        val sShortcutId = "${sPackageName}_${sID}"

        val shortcutManager = getSystemService(ShortcutManager::class.java)

        //바로가기 있는지 확인해서 활성화
        val pinnedShortcuts = getPinnedShortcuts(this)
        val disabledShortcut = pinnedShortcuts.find { it.id == sShortcutId && it.isEnabled.not() }
        if (disabledShortcut != null) {
            shortcutManager?.enableShortcuts(listOf(sShortcutId))
        }

        // 실행할 인텐트 생성
        val intent = Intent(this, SplashActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("pkg", sPackageName + "업데이트")
            putExtra("userId", sID + "업데이트")
            putExtra("userData", "바로가기 정보 업데이트")
        }

        // 바로가기 정보 생성
        val updatedShortcutInfo = ShortcutInfoCompat.Builder(this, sShortcutId)
            .setIntent(intent)
            .setShortLabel(sLabel)
            .setLongLabel(sLabel)
            .setIcon(IconCompat.createWithResource(this, R.drawable.ic_launcher_foreground))
            .build()

        // 바로가기 업데이트 요청
        ShortcutManagerCompat.updateShortcuts(this, listOf(updatedShortcutInfo))
    }

    //바로가기 생성 목록
    private fun getPinnedShortcuts(context: Context): List<ShortcutInfo> {
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)
        return shortcutManager?.pinnedShortcuts ?: emptyList()
    }

    //바로가기 비활성화
    private fun disableShortcuts(context: Context, shortcutId: String) {
        val shortcutManager = context.getSystemService(ShortcutManager::class.java)
        shortcutManager?.disableShortcuts(listOf(shortcutId))
    }
}