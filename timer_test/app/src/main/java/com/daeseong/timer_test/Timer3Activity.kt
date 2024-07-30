package com.daeseong.timer_test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class Timer3Activity : AppCompatActivity() {

    private lateinit var tv1: TextView
    private lateinit var btn1: Button

    private var job: Job? = null
    private var isRunning = false

    private val updateInterval: Long = 1000 // 1초 (밀리초 단위)

    private fun startTimer() {
        if (isRunning) return

        isRunning = true
        val startTime = System.currentTimeMillis()

        job = CoroutineScope(Dispatchers.Main).launch {
            while (isRunning) {
                val elapsedMillis = System.currentTimeMillis() - startTime
                val secondsElapsed = elapsedMillis / 1000
                tv1.text = "타이머: $secondsElapsed 초 경과"
                delay(updateInterval)
            }
        }
    }

    private fun stopTimer() {
        if (isRunning) {
            job?.cancel()
            isRunning = false
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer3)

        tv1 = findViewById(R.id.tv1)
        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            startTimer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        stopTimer()
    }

}