package com.daeseong.marquee_test

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var tv1: TextView? = null
    private var marqueeTask: MarqueeTask? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1  = findViewById(R.id.tv1 )

        marqueeTask = MarqueeTask(tv1!!)
    }

    override fun onResume() {
        super.onResume()

        marqueeTask?.run()
    }

    override fun onPause() {
        super.onPause()

        marqueeTask?.stop()
    }

    override fun onDestroy() {
        super.onDestroy()

        marqueeTask?.stop()
    }
}