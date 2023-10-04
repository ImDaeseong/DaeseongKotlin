package com.daeseong.splash_test

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Splash4Activity : AppCompatActivity() {

    private val tag: String = Splash4Activity::class.java.simpleName

    private lateinit var textView1: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash4)

        textView1 = findViewById(R.id.textView1)

        object : CountDownTimer(30000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                textView1.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                textView1.text = "끝"
                startActivity(Intent(this@Splash4Activity, MainActivity::class.java))
                finish()
            }
        }.start()
    }
}
