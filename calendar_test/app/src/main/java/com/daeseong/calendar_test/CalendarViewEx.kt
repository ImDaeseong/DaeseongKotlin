package com.daeseong.calendar_test

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.AdapterView
import android.widget.GridView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarViewEx : ConstraintLayout {

    private val tag = CalendarViewEx::class.java.simpleName

    private var tv_title: TextView? = null
    private var clleft_arrow: View? = null
    private var clright_arrow: View? = null
    private var gridview1: GridView? = null
    private val calendar = Calendar.getInstance()
    private val arrayList: ArrayList<Date> = ArrayList()

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {

        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view: View = inflater.inflate(R.layout.calendar_view1, this)
        tv_title = view.findViewById(R.id.tv_title)
        clleft_arrow = view.findViewById(R.id.clleft_arrow)
        clright_arrow = view.findViewById(R.id.clright_arrow)
        gridview1 = view.findViewById(R.id.gridview1)

        clleft_arrow!!.setOnClickListener {
            calendar.add(Calendar.MONTH, -1)
            showCalendarView()
        }

        clright_arrow!!.setOnClickListener {
            calendar.add(Calendar.MONTH, 1)
            showCalendarView()
        }

        showCalendarView()
    }

    private fun showCalendarView() {

        //제목
        val simpleDateFormat = SimpleDateFormat("yyyy년MM월")
        tv_title!!.text = simpleDateFormat.format(calendar.time)


        //Calendar.MONTH 값은 0 ~ 11  즉 현재 월을 구하기 위해서는 +1 읗 해야 한다
        //Calendar.DAY_OF_WEEK 값은 1 ~ 7 일 ~ 토
        val tempcalendar = calendar.clone() as Calendar
        tempcalendar[Calendar.DAY_OF_MONTH] = 1
        val nNonth = tempcalendar[Calendar.MONTH]
        val nDay = tempcalendar[Calendar.DAY_OF_WEEK] - 1
        tempcalendar.add(Calendar.DAY_OF_MONTH, -nDay)


        //요일 개수 (7) * 달력칸(6) = 42
        arrayList.clear()
        for (i in 0..41) {
            arrayList.add(tempcalendar.time)
            tempcalendar.add(Calendar.DAY_OF_MONTH, 1)
        }


        gridview1!!.adapter = Calendar1Adapter(context, arrayList)
        gridview1!!.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->

            val date = arrayList[position]
            val nDay = date.date
            val sDay = String.format("%02d", nDay)
            Log.e(tag, "sDay:$sDay")
        }
    }

}
