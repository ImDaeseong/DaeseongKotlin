package com.daeseong.alarm_test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class AlarmAdapter : BaseAdapter() {

    private val tag = AlarmAdapter::class.java.simpleName

    private val arrayList = ArrayList<Alarm>()
    private var tv1: TextView? = null
    private var tv2: TextView? = null
    private var tv3: TextView? = null

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        val context: Context = parent!!.context
        val view: View = LayoutInflater.from(context).inflate(R.layout.alarm_item, null)

        tv1 = view.findViewById(R.id.tv1)
        tv2 = view.findViewById(R.id.tv2)
        tv3 = view.findViewById(R.id.tv3)

        val alarm = getItem(position)
        android.util.Log.e(tag, "alarm : $alarm")

        tv1?.text = alarm.ID.toString()
        tv2?.text = alarm.nHour.toString()
        tv3?.text = alarm.nMinute.toString()

        return view
    }

    override fun getCount(): Int {
        return arrayList.size
    }

    override fun getItem(position: Int): Alarm {
        return arrayList[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    fun addItem(id: Int, nHour: Int, nMinute: Int) {
        val alarm = Alarm(id, nHour, nMinute)
        arrayList.add(alarm)
    }

    fun removeItem(id: Int) {
        for (i in 0 until arrayList.size) {
            if (arrayList[i].ID == id) {
                arrayList.removeAt(i)
                break
            }
        }
    }

    fun updateItem(id: Int, nHour: Int, nMinute: Int) {
        for (i in 0 until arrayList.size) {
            if (arrayList[i].ID == id) {
                val alarm = Alarm(id, nHour, nMinute)
                arrayList[i] = alarm
                break
            }
        }
    }
}
