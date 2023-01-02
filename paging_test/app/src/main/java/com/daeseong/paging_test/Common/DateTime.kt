package com.daeseong.paging_test.Common

import java.text.SimpleDateFormat
import java.util.*

object DateTime {

    //오늘
    fun getToday(): String? {
        val sDateFormat = SimpleDateFormat("yyyy-MM-dd")
        return sDateFormat.format(Date())
    }

    //하루전
    fun getOneDayago(): String? {
        val sDateFormat = SimpleDateFormat("yyyy-MM-dd")
        val calendar = Calendar.getInstance()
        calendar.add(Calendar.DATE, -1)
        return sDateFormat.format(calendar.time)
    }
}