package com.daeseong.calendar_test

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import java.util.*

class Calendar1Adapter(val context: Context, private val arrayList: ArrayList<Date>) : BaseAdapter() {

    private val tag = Calendar1Adapter::class.java.simpleName

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val view: View = LayoutInflater.from(context).inflate(R.layout.calendar_item1, parent, false)
        val tvDay1 = view.findViewById<TextView>(R.id.tv_day1)
        val tvDay2 = view.findViewById<TextView>(R.id.tv_day2)

        //날짜
        val date = arrayList[position]
        val nDay = date.date
        val sDay = String.format("%02d", nDay)


        //일요일 날짜 색상 빨간색
        val calendar = getCalendar(date)
        val nWeek = calendar[Calendar.DAY_OF_WEEK]
        tvDay1.setTextColor(if (nWeek == Calendar.SUNDAY) Color.RED else Color.parseColor("#000000"))

        // 날짜 설정
        tvDay1.text = sDay

        return view
    }

    override fun getItem(position: Int): Any {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    private fun getCalendar(date: Date): Calendar {
        val calendar = Calendar.getInstance()
        calendar.time = date
        return calendar
    }

    private fun getCalendarDate(calendar: Calendar): Date {
        return calendar.time
    }
}