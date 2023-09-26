package com.daeseong.calendar_test

import android.graphics.Color
import com.prolificinteractive.materialcalendarview.CalendarDay
import com.prolificinteractive.materialcalendarview.DayViewDecorator
import com.prolificinteractive.materialcalendarview.DayViewFacade
import com.prolificinteractive.materialcalendarview.spans.DotSpan

class DayViewDecoratorEx3(calendarDays: Collection<CalendarDay>) :  DayViewDecorator {

    private val tag = DayViewDecoratorEx3::class.java.simpleName

    private val calendarDays: HashSet<CalendarDay> = HashSet(calendarDays)

    override fun shouldDecorate(day: CalendarDay): Boolean {
        return calendarDays.contains(day)
    }

    override fun decorate(view: DayViewFacade) {
        view.addSpan(DotSpan(5F, Color.rgb(255, 0, 0)))
    }
}