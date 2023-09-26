package com.daeseong.calendar_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import java.util.Calendar
import java.util.Locale

class Main6Activity : AppCompatActivity() {

    private val tag: String = Main6Activity::class.java.simpleName

    private var calendarViewEx1: CalendarViewEx? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        calendarViewEx1 = findViewById(R.id.calendarviewex1)

        val calendar = Calendar.getInstance(Locale.getDefault())
        val maxDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        Log.e(tag, "maxDay:$maxDay")
    }
}
