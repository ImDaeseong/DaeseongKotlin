package com.daeseong.alarm_test

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class AlarmdbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME,null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "alarms_db"
        private const val TABLE_NAME = "alarms"
        private const val COLUMN_ID = "id"
        private const val COLUMN_HOUR = "hour"
        private const val COLUMN_MINUTE = "minute"
        private const val CREATE_TABLE = ("CREATE TABLE " + TABLE_NAME
                + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COLUMN_HOUR + " INTEGER,"
                + COLUMN_MINUTE + " INTEGER"
                + ")")
    }

    override fun onCreate(db: SQLiteDatabase) {

        //테이블 생성
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {

        // 테이블 존재시 제거
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")

        //테이블 재생성
        onCreate(db)
    }

    fun addAlarm(alarm: Alarm): Long {

        var id : Long = 0

        try {

            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COLUMN_HOUR, alarm.getHour())
            values.put(COLUMN_MINUTE, alarm.getMinute())

            //추가
            id = db.insert(TABLE_NAME, null, values)
            db.close()

        }catch (e: Exception) {
            e.printStackTrace()
        }

        return id
    }

    fun updateAlarm(alarm: Alarm): Long {

        var id : Long = 0

        try {

            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COLUMN_HOUR, alarm.getHour())
            values.put(COLUMN_MINUTE, alarm.getMinute())

            //수정
            db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(alarm.getID().toString()))
            db.close()

            id = alarm.getID().toLong()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        return id
    }

    fun deleteAlarm(id: Int) {

        try {

            val db = this.writableDatabase

            //삭제
            db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
            db.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun deleteAlarmAll() {

        try {

            val db = this.writableDatabase

            //전체 삭제
            db.execSQL("delete from alarms")
            db.close()

        }catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun getAlarm(id: Long): Alarm {

        var alarm = Alarm()

        try {

            val db = this.readableDatabase
            val cursor = db.query(TABLE_NAME, arrayOf(COLUMN_ID, COLUMN_HOUR, COLUMN_MINUTE),"$COLUMN_ID = ?", arrayOf(id.toString()),null,null, null,null)

            if (cursor != null && cursor.moveToFirst() && cursor.count > 0) {
                cursor.moveToFirst()
                alarm = Alarm( cursor.getInt(cursor.getColumnIndex(COLUMN_ID)), cursor.getInt(cursor.getColumnIndex(COLUMN_HOUR)), cursor.getInt(cursor.getColumnIndex(COLUMN_MINUTE)) )
                cursor.close()
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        return alarm
    }

    fun getAllAlarms(): List<Alarm> {

        val alarmList: MutableList<Alarm> = ArrayList()

        try {
            val db = this.writableDatabase
            val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            if (cursor.moveToFirst()) {
                do {
                    val alarm = Alarm()
                    alarm.setID(cursor.getInt(cursor.getColumnIndex(COLUMN_ID)))
                    alarm.setHour(cursor.getInt(cursor.getColumnIndex(COLUMN_HOUR)))
                    alarm.setMinute(cursor.getInt(cursor.getColumnIndex(COLUMN_MINUTE)))
                    alarmList.add(alarm)
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return alarmList
    }

}