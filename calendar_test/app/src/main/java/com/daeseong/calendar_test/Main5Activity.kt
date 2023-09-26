package com.daeseong.calendar_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.CalendarMode
import com.prolificinteractive.materialcalendarview.MaterialCalendarView
import java.util.*

class Main5Activity : AppCompatActivity() {

    private val tag: String = Main5Activity::class.java.simpleName

    private var materialCalendarView: MaterialCalendarView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main5)

        materialCalendarView = findViewById(R.id.materialCalendarView1)

        materialCalendarView?.setOnDateChangedListener { widget, date, selected ->
            Log.e(tag, date.toString())
        }


        //시작시 설정
        materialCalendarView?.state()?.edit()
            ?.setFirstDayOfWeek(Calendar.SUNDAY)
            ?.setMinimumDate(CalendarDay.from(2023, 0, 1))
            ?.setMaximumDate(CalendarDay.from(2023, 11, 31))
            ?.setCalendarDisplayMode(CalendarMode.MONTHS)
            ?.commit()


        //데코 초기화
        materialCalendarView?.removeDecorators()


        //토요일,일요일 색상 변경
        materialCalendarView?.addDecorators(DayViewDecoratorEx(), DayViewDecoratorEx1())
        materialCalendarView?.clearSelection()


        //2020.8.5 밑줄, 사이즈, 색상 변경
        val day1 = DayViewDecoratorEx2(CalendarDay.from(2023, 9, 1))
        materialCalendarView?.addDecorators(day1)


        //오늘 날짜 밑줄, 사이즈, 색상 변경
        val today = CalendarDay.today()
        val day2 = DayViewDecoratorEx2(CalendarDay.from(today.year, today.month, today.day))
        materialCalendarView?.addDecorators(day2)


        //2020.8.10, 2020.8.11 밑줄, 사이즈, 색상 변경 ArrayList
        val arrayList: ArrayList<CalendarDay> = ArrayList()
        val calendar1 = CalendarDay.from(2023, 9, 22)
        val calendar2 = CalendarDay.from(2023, 9, 26)
        arrayList.add(calendar1)
        arrayList.add(calendar2)
        val day3 = DayViewDecoratorEx3(arrayList)
        materialCalendarView?.addDecorators(day3)


        //2020.8.12 밑줄, 사이즈, 색상 변경 HashSet(중복 데이타 자동 처리됨)
        val hashSet: HashSet<CalendarDay> = HashSet()
        hashSet.add(CalendarDay.from(2023, 9, 22))
        hashSet.add(CalendarDay.from(2023, 9, 26))
        val day4 = DayViewDecoratorEx3(hashSet)
        materialCalendarView?.addDecorators(day4)
    }
}
