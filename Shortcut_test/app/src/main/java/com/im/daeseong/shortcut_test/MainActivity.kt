package com.im.daeseong.shortcut_test

import android.content.Intent
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            checkShortCut()
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {

            ShortCutUtil.checkShortCut(this)
        }
    }

    private fun checkShortCut() {
        if (ShortcutManagerCompat.isRequestPinShortcutSupported(this)) {
            Log.e(tag, "홈 화면에 바로가기 지원")
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

        // 실행할 인텐트 생성
        val intent = Intent(this, SplashActivity::class.java).apply {
            action = Intent.ACTION_MAIN
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("pkg", sPackageName)
            putExtra("userId", sID)
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
}