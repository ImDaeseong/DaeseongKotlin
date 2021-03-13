package com.daeseong.countdownlabel

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var tv1: TextViewEx? = null
    private var button1: Button? = null
    private var button2: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tv1 = findViewById(R.id.tv1)
        tv1!!.setCount(10)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {
            tv1!!.stopTimer()
            tv1!!.startTimer()
        }

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener {
            tv1!!.stopTimer()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        tv1!!.stopTimer()
    }
}
