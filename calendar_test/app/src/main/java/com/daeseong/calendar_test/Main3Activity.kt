package com.daeseong.calendar_test

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.MaterialCalendarView

class Main3Activity : AppCompatActivity() {

    private val tag: String = Main3Activity::class.java.simpleName

    private var materialCalendarView: MaterialCalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        materialCalendarView = findViewById(R.id.materialCalendarView1)

        materialCalendarView?.setOnDateChangedListener { widget, date, selected ->
            val sDay = String.format("%02d/%02d/%04d", date.month + 1, date.day, date.year)
            Log.e(tag, "sDay:$sDay")
        }

        materialCalendarView?.setOnMonthChangedListener { widget, date ->
            Log.e(tag, "month:" + (date.month + 1))
        }

        //선택된 요일 색상
        materialCalendarView?.selectionColor = Color.GRAY

        //달력 시작일 설정
        materialCalendarView?.state()?.edit()?.setMinimumDate(CalendarDay.from(2020, 8, 1))?.commit()

        //일요일 색상만 red
        materialCalendarView?.addDecorators(DayViewDecoratorEx())
    }
}
