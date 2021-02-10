package com.daeseong.splash_test

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class Splash6Activity : AppCompatActivity() {

    private val tag: String = Splash6Activity::class.java.simpleName

    private var timer: Timer? = null

    private var banner: ImageView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash6)

        banner = findViewById<ImageView>(R.id.banner)

        val random = Random()
        when (random.nextInt(4)) {
            0 -> banner!!.setImageResource(R.drawable.number1)
            1 -> banner!!.setImageResource(R.drawable.number2)
            2 -> banner!!.setImageResource(R.drawable.number3)
            3 -> banner!!.setImageResource(R.drawable.number4)
        }


        timer = Timer()
        timer!!.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val intent = Intent(this@Splash6Activity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }, 5000)

    }
}
