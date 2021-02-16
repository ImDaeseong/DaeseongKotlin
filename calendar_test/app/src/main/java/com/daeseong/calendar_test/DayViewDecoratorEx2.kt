package com.daeseong.calendar_test

import android.graphics.Color
import android.graphics.Typeface
import android.text.style.AbsoluteSizeSpan
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.util.Log
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade

class DayViewDecoratorEx2 : DayViewDecorator {

    private val tag = DayViewDecoratorEx2::class.java.simpleName

    private var calendarDay: CalendarDay? = null

    constructor() {}

    constructor(calendarDay: CalendarDay?) {
        this.calendarDay = calendarDay
    }

    override fun shouldDecorate(day: CalendarDay): Boolean {

        var sDay : String = String.format("%02d/%02d/%04d", day.month, day.day, day.year);
        Log.e(tag, "sDay:$sDay")

        return calendarDay != null && day == calendarDay
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(StyleSpan(Typeface.BOLD))
        view.addSpan(AbsoluteSizeSpan(50))
        view.addSpan(ForegroundColorSpan(Color.BLUE))
        view.addSpan(UnderlineSpan())
    }

    fun setCalendarDay(calendarDay: CalendarDay) {
        this.calendarDay = calendarDay
    }
}