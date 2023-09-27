package com.daeseong.timer_test

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var tv1: TextView
    private lateinit var btn1: Button

    private var timer: Timer? = null
    private var nTimeCount = 0
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1 = findViewById(R.id.tv1)
        btn1 = findViewById(R.id.btn1)

        btn1.setOnClickListener {
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

    private fun startTimer() {
        closeTimer()

        timer = Timer()
        timer?.scheduleAtFixedRate(object : TimerTask() {
            override fun run() {
                nTimeCount++
                handler.post {
                    tv1.text = nTimeCount.toString()
                }

                if (nTimeCount > 180) {
                    closeTimer()
                    handler.post {
                        Toast.makeText(this@MainActivity, "Toast test", Toast.LENGTH_SHORT).show()
                        tv1.text = "완료"
                    }
                }
            }
        }, 0, 1000)
    }

    private fun closeTimer() {
        timer?.cancel()
        timer = null
    }
}
