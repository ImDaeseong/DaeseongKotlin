package com.daeseong.timer_test

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName;

    private var tv1: TextView? = null
    private var btn1: Button? = null

    private var timer: Timer? = null

    private fun closeTimer() {
        try {
            if (timer != null) {
                timer!!.cancel()
                timer = null
            }
        } catch (e: Exception) {
        }
    }

    private fun startTimer() {

        closeTimer()

        timer = Timer()
        timer!!.schedule(object : TimerTask() {

            var nTimeCount = 0
            override fun run() {
                try {

                    nTimeCount++
                    runOnUiThread {
                        val sMsg = String.format("%d", nTimeCount)
                        tv1!!.text = sMsg
                    }

                    if (nTimeCount > 180) { //3분까지만 체크
                        closeTimer()
                        runOnUiThread {
                            Toast.makeText(this@MainActivity, "Toast test", Toast.LENGTH_SHORT).show()
                            tv1!!.text = "완료"
                        }
                    }
                } catch (e: java.lang.Exception) {
                }
            }

        }, 0, 1000)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tv1 = findViewById<TextView>(R.id.tv1)

        btn1 = findViewById<Button>(R.id.btn1)
        btn1!!.setOnClickListener(View.OnClickListener {
            startTimer();
        })

    }

    override fun onPause() {
        super.onPause()

        closeTimer();
    }

    override fun onDestroy() {
        super.onDestroy()

        closeTimer();
    }
}
