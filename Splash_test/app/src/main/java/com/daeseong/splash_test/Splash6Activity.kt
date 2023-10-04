package com.daeseong.splash_test

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Splash6Activity : AppCompatActivity() {

    private val tag: String = Splash6Activity::class.java.simpleName

    private var timer: Timer? = null
    private lateinit var banner: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash6)

        banner = findViewById(R.id.banner)

        val random = Random()
        val drawableResource = when (random.nextInt(4)) {
            0 -> R.drawable.number1
            1 -> R.drawable.number2
            2 -> R.drawable.number3
            3 -> R.drawable.number4
            else -> R.drawable.number1
        }
        banner.setImageResource(drawableResource)


        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                runOnUiThread {
                    val intent = Intent(this@Splash6Activity, MainActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }, 5000)
    }

    override fun onDestroy() {
        super.onDestroy()

        timer?.cancel()
        timer = null
    }
}
