package com.daeseong.splash_test

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class Splash5Activity : AppCompatActivity() {

    private val tag: String = Splash5Activity::class.java.simpleName

    private var imageView1: ImageView? = null
    private var FadeInScale: Animation? = null
    private var handler: Handler = Handler()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        setContentView(R.layout.activity_splash5)

        handler.postDelayed({
            startActivity(Intent(this@Splash5Activity, MainActivity::class.java)
        )}, 3000)
    }

    private fun StartAnimation() {

        //Log.e(tag, "StartAnimation")

        imageView1 = findViewById<ImageView>(R.id.imageView1)
        FadeInScale = AnimationUtils.loadAnimation(this, R.anim.fade_in_scale)
        FadeInScale!!.fillAfter = true
        imageView1!!.startAnimation(FadeInScale)
    }

}
