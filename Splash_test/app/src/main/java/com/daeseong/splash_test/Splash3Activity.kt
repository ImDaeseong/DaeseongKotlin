package com.daeseong.splash_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout

class Splash3Activity : AppCompatActivity() {

    private val tag: String = Splash3Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash3)

        StartAnimation()

        val handler = Handler(Looper.getMainLooper())
        handler.postDelayed({
            startActivity(Intent(this@Splash3Activity, MainActivity::class.java))
            finish()
        }, 5000)
    }

    private fun StartAnimation() {

        //Log.e(tag, "StartAnimation")

        val animationAlpha = AnimationUtils.loadAnimation(this, R.anim.alpha)
        animationAlpha.reset()

        val constraintLayout = findViewById<ConstraintLayout>(R.id.lin)
        constraintLayout.clearAnimation()
        constraintLayout.startAnimation(animationAlpha)

        val animationTranslate = AnimationUtils.loadAnimation(this, R.anim.translate)
        animationTranslate.reset()

        val imageView2: ImageView = findViewById(R.id.imageView2)
        imageView2.clearAnimation()
        imageView2.startAnimation(animationTranslate)
    }
}
