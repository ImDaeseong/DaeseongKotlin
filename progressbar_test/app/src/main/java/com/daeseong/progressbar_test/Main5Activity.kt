package com.daeseong.progressbar_test

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity

class Main5Activity : AppCompatActivity() {

    private val tag: String = Main5Activity::class.java.simpleName

    private lateinit var pb1: ProgressBar
    private var progress: Long = 0
    private val max: Int = 10000
    private val pos: Int = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        pb1 = findViewById(R.id.pb1)

        loadProgress()
    }

    private fun loadProgress() {
        pb1.max = max
        pb1.progress = 0

        val countDownTimer = object : CountDownTimer(max.toLong(), pos.toLong()) {
            override fun onTick(l: Long) {
                progress += pos
                pb1.progress = progress.toInt()
            }

            override fun onFinish() {
                progress += pos
                pb1.progress = progress.toInt()
                pb1.visibility = View.INVISIBLE
            }
        }
        countDownTimer.start()
    }

}
