package com.daeseong.splash_test

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Window
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class Splash5Activity : AppCompatActivity() {

    private val tag: String = Splash5Activity::class.java.simpleName

    private var imageView1: ImageView? = null
    private var fadeInScale: Animation? = null
    private var handler: Handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        InitTitleBar()

        setContentView(R.layout.activity_splash5)

        handler.postDelayed({
            startActivity(Intent(this@Splash5Activity, MainActivity::class.java))
            finish()
        }, 3000)

        startAnimation()
    }

    override fun onDestroy() {
        super.onDestroy()

        handler.removeCallbacksAndMessages(null)
    }

    private fun startAnimation() {

        imageView1 = findViewById(R.id.imageView1)
        fadeInScale = AnimationUtils.loadAnimation(this, R.anim.fade_in_scale).apply {
            fillAfter = true
        }

        imageView1?.startAnimation(fadeInScale)
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
}
