package com.daeseong.singleton_test.Db

import android.content.Context

class DbHandler {

    private val tag: String = DbHandler::class.java.simpleName

    private var dbHelperAlarm: DbHelperAlarm? = null

    companion object {
        private var instance: DbHandler? = null
        fun getInstance(): DbHandler {
            if (instance == null) {
                instance = DbHandler()
            }
            return instance as DbHandler
        }
    }

    fun init(context: Context) {
        dbHelperAlarm = DbHelperAlarm(context)
    }

    fun addAlarm(alarm: Alarm) {
        dbHelperAlarm!!.addAlarm(alarm)
    }

    fun updateAlarm(alarm: Alarm) {
        dbHelperAlarm!!.updateAlarm(alarm)
    }

    fun deleteAlarm(title: String) {
        dbHelperAlarm!!.deleteAlarm(title)
    }

    fun deleteMaxData() {
        dbHelperAlarm!!.deleteMaxData()
    }

    fun getMaxData(): Alarm {
        return dbHelperAlarm!!.getMaxData()
    }

    fun clearAlarm() {
        dbHelperAlarm!!.clearAlarm()
    }

    fun getAlarmList(): ArrayList<Alarm> {
        return dbHelperAlarm!!.getAlarmList()
    }

    fun getRowCount(): Int {
        return dbHelperAlarm!!.getRowCount()
    }

    fun findAlarm(title: String): Boolean {
        return dbHelperAlarm!!.findAlarm(title)
    }

    fun getAlarm(title: String): Alarm {
        return dbHelperAlarm!!.getAlarm(title)
    }
}