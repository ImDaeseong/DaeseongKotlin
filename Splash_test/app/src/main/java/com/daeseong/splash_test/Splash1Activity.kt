package com.daeseong.splash_test

import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.splash_test.util.SharedPreferences_util
import java.util.*

class Splash1Activity : AppCompatActivity() {

    private val tag: String = Splash1Activity::class.java.simpleName

    private var timer: Timer? = null

    private var handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_splash1)

        //첫번째 방법
        //call1()

        //두번재 방법
        call2()
    }

    override fun onDestroy() {
        super.onDestroy()

        timer?.cancel()
        timer = null

        handler.removeCallbacksAndMessages(null)
    }

    //타이틀바 숨기기/가로보기 고정/풀스크린
    private fun InitTitleBar() {

        try {
            //안드로이드 8.0 오레오 버전에서만 오류 발생
            supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        } catch (ex: Exception) {
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.decorView.windowInsetsController?.hide(WindowInsets.Type.statusBars())
        } else {
            @Suppress("DEPRECATION")
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
    }

    private fun call1() {

        val isFirst = SharedPreferences_util().getValue(this, "FIRST", false) as Boolean

        timer = Timer(true)
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    if (isFirst) {
                        Log.e(tag, "Second")
                    } else {
                        Log.e(tag, "First")
                        SharedPreferences_util().setValue(this@Splash1Activity, "FIRST", true)
                    }
                    finish()
                }
            }
        }, 1000)
    }

    private fun call2() {

        handler.postDelayed({
            val isFirst = SharedPreferences_util().getValue(this@Splash1Activity, "FIRST", false) as Boolean
            if (isFirst) {
                Log.e(tag, "Second")
            } else {
                Log.e(tag, "First")
                SharedPreferences_util().setValue(this@Splash1Activity, "FIRST", true)
            }
            finish()
        }, 1000)
    }
}