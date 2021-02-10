package com.daeseong.splash_test

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    Log.e(tag, "run")
                    sleep(5000)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                } finally {
                    startActivity(Intent(this@Splash3Activity, MainActivity::class.java))
                }
                finish()
            }
        }
        thread.start()
    }

    private fun StartAnimation() {

        //Log.e(tag, "StartAnimation")

        var anim = AnimationUtils.loadAnimation(this, R.anim.alpha)
        anim.reset()

        val l = findViewById<ConstraintLayout>(R.id.lin)
        l.clearAnimation()
        l.startAnimation(anim)

        anim = AnimationUtils.loadAnimation(this, R.anim.translate)
        anim.reset()

        val imageView2: ImageView = findViewById<ImageView>(R.id.imageView2)
        imageView2.clearAnimation()
        imageView2.startAnimation(anim)
    }
}
