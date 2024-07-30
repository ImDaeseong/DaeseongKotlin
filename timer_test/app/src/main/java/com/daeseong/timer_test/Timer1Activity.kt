package com.daeseong.timer_test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.Timer
import java.util.TimerTask

class Timer1Activity : AppCompatActivity() {

    private lateinit var tv1: TextView
    private lateinit var btn1: Button

    private var timer: Timer? = null

    private fun closeTimer() {
        try {
            timer?.let {
                it.cancel()
                timer = null
            }
        } catch (e: Exception) {
            // Handle exception if needed
        }
    }

    private fun startTimer() {
        closeTimer()

        timer = Timer().apply {
            schedule(object : TimerTask() {
                var nTimeCount = 0

                override fun run() {
                    try {
                        nTimeCount++

                        runOnUiThread {
                            val sMsg = String.format("%d", nTimeCount)
                            tv1.text = sMsg
                        }

                        if (nTimeCount > 180) { //3분까지만 체크
                            closeTimer()

                            runOnUiThread {
                                Toast.makeText(this@Timer1Activity, "Toast test", Toast.LENGTH_SHORT).show()
                                tv1.text = "완료"
                            }
                        }
                    } catch (e: Exception) {
                        // Handle exception if needed
                    }
                }
            }, 0, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timer1)

        tv1 = findViewById(R.id.tv1)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {
            startTimer()
        }

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