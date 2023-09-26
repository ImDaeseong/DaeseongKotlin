package com.daeseong.calendar_test

import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    private val tag: String = Main2Activity::class.java.simpleName

    private var calendarView: CalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        calendarView = findViewById(R.id.calendarview1)
        calendarView?.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val sDay = String.format("%02d/%02d/%04d", month + 1, dayOfMonth, year)
            Log.e(tag, "sDay:$sDay")
        }
    }
}
