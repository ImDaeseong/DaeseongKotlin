package com.daeseong.calendar_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import com.prolificinteractive.materialcalendarview.CalendarMode
import java.util.Calendar

class Main4Activity : AppCompatActivity() {

    private val tag: String = Main4Activity::class.java.simpleName

    private var materialCalendarView: MaterialCalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        materialCalendarView = findViewById(R.id.materialCalendarView1)

        materialCalendarView?.setOnDateChangedListener { widget, date, selected ->
            val calendar: Calendar = materialCalendarView?.selectedDate?.calendar ?: Calendar.getInstance()
            when (calendar.get(Calendar.DAY_OF_WEEK)) {
                Calendar.SUNDAY -> Log.e(tag, "일요일")
                Calendar.MONDAY -> Log.e(tag, "월요일")
                Calendar.TUESDAY -> Log.e(tag, "화요일")
                Calendar.WEDNESDAY -> Log.e(tag, "수요일")
                Calendar.THURSDAY -> Log.e(tag, "목요일")
                Calendar.FRIDAY -> Log.e(tag, "금요일")
                Calendar.SATURDAY -> Log.e(tag, "토요일")
            }
        }

        //시작시 설정
        materialCalendarView?.state()?.edit()
            ?.setFirstDayOfWeek(Calendar.SUNDAY)
            ?.setMinimumDate(CalendarDay.from(2023, 0, 1))
            ?.setMaximumDate(CalendarDay.from(2023, 11, 31))
            ?.setCalendarDisplayMode(CalendarMode.MONTHS)
            ?.commit()
    }
}
