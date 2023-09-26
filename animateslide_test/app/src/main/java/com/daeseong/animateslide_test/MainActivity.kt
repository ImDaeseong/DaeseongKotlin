package com.daeseong.animateslide_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private val btn1: Button by lazy { findViewById<Button>(R.id.btn1) }
    private val btn2: Button by lazy { findViewById<Button>(R.id.btn2) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1.setOnClickListener {
            startActivity(Intent(this, FirstActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        btn2.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top)
        }
    }
}
