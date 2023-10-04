package com.daeseong.progressbar_test

import android.animation.ValueAnimator
import android.animation.ValueAnimator.AnimatorUpdateListener
import android.os.Bundle
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    private val tag: String = Main2Activity::class.java.simpleName

    private lateinit var pb1: ProgressBar
    private var animator: ValueAnimator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        pb1 = findViewById(R.id.pb1)

        Thread {
            runOnUiThread {
                animator = ValueAnimator.ofInt(0, 100).apply {
                    addUpdateListener(AnimatorUpdateListener { animation ->
                        val progress = animation.animatedValue as Int
                        pb1.progress = progress
                    })
                    repeatCount = ValueAnimator.INFINITE
                    duration = 5000
                    start()
                }
            }
        }.start()

    }
}
