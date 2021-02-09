package com.daeseong.marquee_test

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var tv1: TextView? = null

    private var task: MarqueeTask? = null

    private var timer: Timer? = null


    private fun closeTimer() {

        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    private fun startTimer() {

        closeTimer()
        task = MarqueeTask(tv1!!)
        timer = Timer()
        timer!!.schedule(task, 0, 10000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1  = findViewById<TextView>(R.id.tv1 )

        startTimer()
    }

    override fun onPause() {
        super.onPause()

        closeTimer()
    }

    override fun onDestroy() {
        super.onDestroy()

        closeTimer()
    }
}
