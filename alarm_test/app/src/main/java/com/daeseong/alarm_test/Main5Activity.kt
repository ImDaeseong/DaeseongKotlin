package com.daeseong.alarm_test

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Main5Activity : AppCompatActivity() {

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var alarmUtil: AlarmUtil

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        // AlarmUtil 초기화
        alarmUtil = AlarmUtil.getInstance().apply {
            init(this@Main5Activity)
        }

        // 버튼 설정
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)

        button1.setOnClickListener {
            alarmUtil.addAlarm(1, 18, 59)
        }

        button2.setOnClickListener {
            alarmUtil.deleteAlarm(1)
        }
    }
}
