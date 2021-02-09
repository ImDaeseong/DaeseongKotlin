package com.daeseong.animateslide_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var btn1 : Button? = null
    private var btn2 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn1 = findViewById<Button>(R.id.btn1)
        btn1!!.setOnClickListener {

            startActivity(Intent(this, FirstActivity::class.java))
            overridePendingTransition(R.anim.slide_from_right, R.anim.slide_to_left)
        }

        btn2 = findViewById<Button>(R.id.btn2)
        btn2!!.setOnClickListener {

            startActivity(Intent(this, SecondActivity::class.java))
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_top)
        }

    }
}
