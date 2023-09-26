package com.daeseong.calendar_test

import android.graphics.Color
import android.text.style.ForegroundColorSpan
import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan
import java.util.Calendar

class DayViewDecoratorEx : DayViewDecorator {

    private val tag = DayViewDecoratorEx::class.java.simpleName

    private val calendar = Calendar.getInstance()

    constructor() : super()

    override fun shouldDecorate(day: CalendarDay): Boolean {

        day.copyTo(calendar)
        val nIndex = calendar[Calendar.DAY_OF_WEEK]

        val dayOfWeek = when (nIndex) {
            Calendar.SUNDAY -> "일요일"
            Calendar.MONDAY -> "월요일"
            Calendar.TUESDAY -> "화요일"
            Calendar.WEDNESDAY -> "수요일"
            Calendar.THURSDAY -> "목요일"
            Calendar.FRIDAY -> "금요일"
            Calendar.SATURDAY -> "토요일"
            else -> "알 수 없음"
        }
        Log.e(tag, dayOfWeek)

        return nIndex == Calendar.SUNDAY
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(ForegroundColorSpan(Color.rgb(255, 0, 0)))
        //view.addSpan( ForegroundColorSpan(Color.RED))
        //view.addSpan( DotSpan(5))
    }

}