package com.daeseong.animation_test

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Animate8Activity : AppCompatActivity() {

    private val tag: String = Animate8Activity::class.java.simpleName

    private var button1 : Button? = null

    private var image1: ImageView? = null

    private var spriteTask: spriteTask? = null
    private var timer: Timer? = null

    private fun closeTimer() {
        if (timer != null) {
            timer!!.cancel()
            timer = null
        }
    }

    private fun startTimer() {
        closeTimer()
        spriteTask = spriteTask(this, image1!!)
        timer = Timer()
        timer!!.schedule(spriteTask, 0, 100)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate8)

        image1 = findViewById<ImageView>(R.id.image1)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            startTimer()
        }
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
