package com.daeseong.calendar_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.HashSet


class Main5Activity : AppCompatActivity() {

    private val tag: String = Main5Activity::class.java.simpleName

    private var materialCalendarView1: MaterialCalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        materialCalendarView1 = findViewById<MaterialCalendarView>(R.id.materialCalendarView1)

        materialCalendarView1!!.setOnDateChangedListener { widget, date, selected ->

            Log.e(tag, date.toString())
        }


        //시작시 설정
        materialCalendarView1!!.state().edit()
            .setFirstDayOfWeek(Calendar.SUNDAY)
            .setMinimumDate(CalendarDay.from(2021, 0, 1))
            .setMaximumDate(CalendarDay.from(2021, 11, 31))
            .setCalendarDisplayMode(CalendarMode.MONTHS)
            .commit()


        //데코 초기화
        materialCalendarView1!!.removeDecorators()


        //토요일,일요일 색상 변경
        materialCalendarView1!!.addDecorators( DayViewDecoratorEx(), DayViewDecoratorEx1()  )

        materialCalendarView1!!.clearSelection()

        //2020.8.5 밑줄, 사이즈, 색상 변경
        val day1 = DayViewDecoratorEx2(CalendarDay.from(2021, 2, 1))
        materialCalendarView1!!.addDecorators(day1)


        //오늘 날짜 밑줄, 사이즈, 색상 변경
        val today = CalendarDay.today()
        val day2 = DayViewDecoratorEx2(CalendarDay.from(today.year, today.month, today.day))
        materialCalendarView1!!.addDecorators(day2)


        //2020.8.10, 2020.8.11 밑줄, 사이즈, 색상 변경 ArrayList
        val arrayList: ArrayList<CalendarDay> = ArrayList()
        val calendar1 = CalendarDay.from(2021, 2, 22)
        val calendar2 = CalendarDay.from(2021, 2, 23)
        arrayList.add(calendar1)
        arrayList.add(calendar2)
        materialCalendarView1!!.addDecorators(DayViewDecoratorEx3(arrayList))


        //2020.8.12 밑줄, 사이즈, 색상 변경 HashSet(중복 데이타 자동 처리됨)
        val hashSet: HashSet<CalendarDay> = HashSet()
        hashSet.add(CalendarDay.from(2021, 2, 22))
        hashSet.add(CalendarDay.from(2021, 2, 23))
        materialCalendarView1!!.addDecorators(DayViewDecoratorEx3(hashSet))
    }
}
