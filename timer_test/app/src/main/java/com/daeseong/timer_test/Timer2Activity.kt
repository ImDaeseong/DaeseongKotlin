package com.daeseong.timer_test

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class Timer2Activity : AppCompatActivity() {

    private lateinit var tv1: TextView
    private lateinit var btn1: Button

    private var timer: CountDownTimer? = null
    private val TIMER_DURATION: Long = 60000 // 1분

    private fun startTimer() {
        timer?.cancel()

        timer = object : CountDownTimer(TIMER_DURATION, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsRemaining = millisUntilFinished / 1000
                tv1.text = "타이머: $secondsRemaining 초 남음"
            }

            override fun onFinish() {
                tv1.text = "타이머 완료"
            }
        }.start()
    }

    private fun stopTimer() {
        timer?.cancel()
        timer = null
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_timer2)

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