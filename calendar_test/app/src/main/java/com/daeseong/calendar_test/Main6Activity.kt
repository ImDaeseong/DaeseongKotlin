package com.daeseong.calendar_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Main6Activity : AppCompatActivity() {

    private val tag: String = Main6Activity::class.java.simpleName

    private var calendarviewex1: CalendarViewEx? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        calendarviewex1 = findViewById<CalendarViewEx>(R.id.calendarviewex1)

        val calender = Calendar.getInstance(Locale.getDefault())
        val nMaxDay = calender.getActualMaximum(Calendar.DAY_OF_MONTH)
        Log.e(tag, "nMaxDay:$nMaxDay");

    }
}
