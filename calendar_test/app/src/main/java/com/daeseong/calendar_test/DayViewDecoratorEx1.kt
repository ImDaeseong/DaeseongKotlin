package com.daeseong.calendar_test

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.util.*


class DayViewDecoratorEx1 : DayViewDecorator {

    private val tag = DayViewDecoratorEx1::class.java.simpleName

    private val calendar: Calendar = Calendar.getInstance()

    override fun shouldDecorate(day: CalendarDay): Boolean {
        day.copyTo(calendar)
        val nIndex = calendar[Calendar.DAY_OF_WEEK]

        when (nIndex) {
            1 -> Log.e(tag, "일요일")
            2 -> Log.e(tag, "월요일")
            3 -> Log.e(tag, "화요일")
            4 -> Log.e(tag, "수요일")
            5 -> Log.e(tag, "목요일")
            6 -> Log.e(tag, "금요일")
            7 -> Log.e(tag, "토요일")
        }

        return nIndex == Calendar.SATURDAY
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(Color.rgb(0, 255, 0)))
        //view.addSpan(ForegroundColorSpan(Color.RED))
        //view.addSpan(DotSpan(Color.rgb(255, 0, 0).toFloat(), 5))
    }
}