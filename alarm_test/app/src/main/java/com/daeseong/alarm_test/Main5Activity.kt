package com.daeseong.alarm_test

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main5Activity : AppCompatActivity() {

    private val tag: String = Main5Activity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        Alarm_util.getInstance().init(this)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            Alarm_util.getInstance().AddAlaram(1, 18, 59)
        }

        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            Alarm_util.getInstance().DeleteAlarm(1)
        }
    }
}
