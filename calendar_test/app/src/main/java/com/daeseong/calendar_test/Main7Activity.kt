package com.daeseong.calendar_test

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class Main7Activity : AppCompatActivity() {

    private val tag: String = Main7Activity::class.java.simpleName

    private var calendarviewex1: CalendarViewEx1? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main7)

        calendarviewex1 = findViewById<CalendarViewEx1>(R.id.calendarviewex1)

    }
}
