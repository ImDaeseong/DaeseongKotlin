package com.daeseong.gameinfo_test

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            Handler(Looper.getMainLooper()).postDelayed({
                val gameItems = GameInfo().getGameApp(this)
                gameItems.forEach {
                    Log.e(tag, "packageName:${it.packageName}")
                }
            }, 100)
        }

        btn2 = findViewById(R.id.btn2)
        btn2.setOnClickListener {

            startService(Intent(this, GameCheckService::class.java))
        }

        btn3 = findViewById(R.id.btn3)
        btn3.setOnClickListener {

            stopService(Intent(this, GameCheckService::class.java))
        }
    }
}
