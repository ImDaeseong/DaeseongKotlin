package com.daeseong.calendar_test

import android.os.Bundle
import android.util.Log
import android.widget.CalendarView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class Main1Activity : AppCompatActivity() {

    private val tag: String = Main1Activity::class.java.simpleName

    private var calendarview1: CalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        calendarview1  = findViewById(R.id.calendarview1)
        calendarview1!!.firstDayOfWeek = Calendar.MONDAY//.SUNDAY);//달력의 시작일 설정(default 일요일)

        //달력 유효기간 설정
        setCalendarDay1()

        calendarview1!!.setOnDateChangeListener { _, year, month, dayOfMonth ->
            val sDay = String.format("%02d/%02d/%04d", month + 1, dayOfMonth, year)
            Log.e(tag, "sDay:$sDay")
        }
    }

    private fun setCalendarDay1() {

        //지금 시간부터
        val cal = Calendar.getInstance()
        cal[cal[Calendar.YEAR], cal[Calendar.MONTH], cal[Calendar.DAY_OF_MONTH], 0, 0] = 0
        calendarview1!!.minDate = cal.timeInMillis

        //한달 후까지
        cal.add(Calendar.MONTH, 1)
        calendarview1!!.maxDate = cal.timeInMillis
    }

    private fun setCalendarDay2() {

        //이번달 5일부터
        val calendar = Calendar.getInstance()
        calendar[Calendar.DAY_OF_MONTH] = 5
        calendarview1!!.minDate = calendar.timeInMillis

        //다음달 15일까지
        calendar.add(Calendar.MONTH, 1)
        calendar[Calendar.DAY_OF_MONTH] = 15
        calendarview1!!.maxDate = calendar.timeInMillis
    }

    private fun setCalendarDay3() {

        //07/01/2020
        val calendar1 = Calendar.getInstance()
        calendar1[2020, 6] = 1

        //09/30/2020
        val calendar2 = Calendar.getInstance()
        calendar2[2020, 9] = 30
        calendarview1!!.minDate = calendar1.timeInMillis
        calendarview1!!.maxDate = calendar2.timeInMillis
    }
}
