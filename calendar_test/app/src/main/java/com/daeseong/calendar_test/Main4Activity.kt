package com.daeseong.calendar_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*


class Main4Activity : AppCompatActivity() {

    private val tag: String = Main4Activity::class.java.simpleName

    private var materialCalendarView1: MaterialCalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        materialCalendarView1 = findViewById<MaterialCalendarView>(R.id.materialCalendarView1)

        materialCalendarView1!!.setOnDateChangedListener { widget, date, selected ->
            val calendar: Calendar = materialCalendarView1!!.selectedDate.calendar
            when (calendar.get(Calendar.DAY_OF_WEEK)) {
                1 -> Log.e(tag, "일요일")
                2 -> Log.e(tag, "월요일")
                3 -> Log.e(tag, "화요일")
                4 -> Log.e(tag, "수요일")
                5 -> Log.e(tag, "목요일")
                6 -> Log.e(tag, "금요일")
                7 -> Log.e(tag, "토요일")
            }
        }

        //시작시 설정
        materialCalendarView1!!.state().edit()
            .setFirstDayOfWeek(Calendar.SUNDAY)
            .setMinimumDate(CalendarDay.from(2020, 0, 1))
            .setMaximumDate(CalendarDay.from(2020, 11, 31))
            .setCalendarDisplayMode(CalendarMode.MONTHS)
            .commit()
    }
}
