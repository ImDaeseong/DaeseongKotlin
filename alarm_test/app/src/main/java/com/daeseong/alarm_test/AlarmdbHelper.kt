package com.daeseong.alarm_test

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class AlarmdbHelper(context: Context?) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_VERSION = 1
        private const val DATABASE_NAME = "alarms_db"
        private const val TABLE_NAME = "alarms"
        private const val COLUMN_ID = "id"
        private const val COLUMN_HOUR = "hour"
        private const val COLUMN_MINUTE = "minute"
        private const val CREATE_TABLE =
            "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_HOUR INTEGER, $COLUMN_MINUTE INTEGER)"
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Create the table
        db.execSQL(CREATE_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Drop the table if it exists
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        // Recreate the table
        onCreate(db)
    }

    fun addAlarm(alarm: Alarm): Long {
        var id: Long = 0
        try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_HOUR, alarm.nHour)
                put(COLUMN_MINUTE, alarm.nMinute)
            }
            // Insert the values into the table
            id = db.insert(TABLE_NAME, null, values)
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return id
    }

    fun updateAlarm(alarm: Alarm): Long {
        var id: Long = 0
        try {
            val db = writableDatabase
            val values = ContentValues().apply {
                put(COLUMN_HOUR, alarm.nHour)
                put(COLUMN_MINUTE, alarm.nMinute)
            }
            // Update the values in the table where COLUMN_ID matches the alarm ID
            db.update(TABLE_NAME, values, "$COLUMN_ID = ?", arrayOf(alarm.ID.toString()))
            db.close()
            id = alarm.ID.toLong()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return id
    }

    fun deleteAlarm(id: Int) {
        try {
            val db = writableDatabase
            // Delete the row from the table where COLUMN_ID matches the provided ID
            db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteAlarmAll() {
        try {
            val db = writableDatabase
            // Delete all rows from the table
            db.execSQL("DELETE FROM $TABLE_NAME")
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getAlarm(id: Long): Alarm {
        var alarm = Alarm()
        try {
            val db = readableDatabase
            val cursor: Cursor = db.query(
                TABLE_NAME,
                arrayOf(COLUMN_ID, COLUMN_HOUR, COLUMN_MINUTE),
                "$COLUMN_ID = ?",
                arrayOf(id.toString()),
                null,
                null,
                null,
                null
            )
            if (cursor.moveToFirst()) {
                val columnIndexID = cursor.getColumnIndex(COLUMN_ID)
                val columnIndexHour = cursor.getColumnIndex(COLUMN_HOUR)
                val columnIndexMinute = cursor.getColumnIndex(COLUMN_MINUTE)

                // Check if column indices are valid
                if (columnIndexID >= 0 && columnIndexHour >= 0 && columnIndexMinute >= 0) {
                    alarm = Alarm(
                        cursor.getInt(columnIndexID),
                        cursor.getInt(columnIndexHour),
                        cursor.getInt(columnIndexMinute)
                    )
                }
            }
            cursor.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return alarm
    }

    fun getAllAlarms(): List<Alarm> {
        val alarmList: MutableList<Alarm> = ArrayList()
        try {
            val db = writableDatabase
            val cursor: Cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
            if (cursor.moveToFirst()) {
                do {
                    val columnIndexID = cursor.getColumnIndex(COLUMN_ID)
                    val columnIndexHour = cursor.getColumnIndex(COLUMN_HOUR)
                    val columnIndexMinute = cursor.getColumnIndex(COLUMN_MINUTE)

                    // Check if column indices are valid
                    if (columnIndexID >= 0 && columnIndexHour >= 0 && columnIndexMinute >= 0) {
                        val alarm = Alarm(
                            cursor.getInt(columnIndexID),
                            cursor.getInt(columnIndexHour),
                            cursor.getInt(columnIndexMinute)
                        )
                        alarmList.add(alarm)
                    }
                } while (cursor.moveToNext())
            }
            cursor.close()
            db.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return alarmList
    }

}
