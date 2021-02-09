package com.daeseong.progressbar_test

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class Main3Activity : AppCompatActivity() {

    private val tag: String = Main3Activity::class.java.simpleName

    private var pb1: ProgressBar? = null
    private var animator: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        pb1 = findViewById<ProgressBar>(R.id.pb1)

        object : Thread() {
            override fun run() {
                runOnUiThread {
                    animator = ValueAnimator.ofInt(0, 100)
                    animator!!.addUpdateListener(AnimatorUpdateListener { animation ->
                        val progress = animation.animatedValue as Int
                        pb1!!.progress = progress
                    })

                    animator!!.repeatCount = ValueAnimator.INFINITE
                    animator!!.duration = 5000
                    animator!!.start()
                }
            }
        }.start()
    }

}
