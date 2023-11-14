package com.daeseong.alarm_test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main5Activity : AppCompatActivity() {

    private val tag: String = Main5Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        val alarmUtil = AlarmUtil.getInstance()
        alarmUtil.init(this)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            alarmUtil.addAlarm(1, 18, 59)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            alarmUtil.deleteAlarm(1)
        }
    }
}
