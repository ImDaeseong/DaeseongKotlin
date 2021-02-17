package com.daeseong.db_test.SQLiteOpenHelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.DatabaseUtils
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteDatabase.CursorFactory
import android.database.sqlite.SQLiteOpenHelper


class DbHelperAlarm(context: Context?, name: String?, factory: CursorFactory?, version: Int) :  SQLiteOpenHelper(
    context,
    DATABASE_NAME,
    factory,
    DATABASE_VERSION
) {

    companion object {
        private const val DATABASE_VERSION = 2
        private const val DATABASE_NAME = "AlarmDB.db"
        const val TABLE_ALARM = "alarms"
        const val COLUMN_ID = "_id"
        const val COLUMN_TITLE = "title"
        const val COLUMN_CONTENT = "content"
        const val COLUMN_WRITEDATE = "writeDate"
    }

    override fun onCreate(db: SQLiteDatabase) {
        try {

            db.execSQL("DROP TABLE IF EXISTS $TABLE_ALARM")

            val createTable = ("CREATE TABLE " +
                    TABLE_ALARM + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_TITLE + " TEXT NOT NULL, "
                    + COLUMN_CONTENT + " TEXT NOT NULL, "
                    + COLUMN_WRITEDATE + " DATETIME DEFAULT CURRENT_DATE);")
            db.execSQL(createTable)

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        try {

            if (oldVersion == 1 && newVersion == 2) {

                db.execSQL("DROP TABLE IF EXISTS $TABLE_ALARM")

                val createTable = ("CREATE TABLE " +
                        TABLE_ALARM + "("
                        + COLUMN_ID + " INTEGER PRIMARY KEY,"
                        + COLUMN_TITLE + " TEXT NOT NULL, "
                        + COLUMN_CONTENT + " TEXT NOT NULL, "
                        + COLUMN_WRITEDATE + " DATETIME DEFAULT CURRENT_DATE);")
                db.execSQL(createTable)

            } else if (oldVersion == 2 && newVersion == 3) {
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun addAlarm(alarm: Alarm) {

        try {

            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COLUMN_TITLE, alarm.getTitle())
            values.put(COLUMN_CONTENT, alarm.getContent())

            db.insert(TABLE_ALARM, null, values)
            db.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun updateAlarm(alarm: Alarm) {

        try {

            val db = this.writableDatabase
            val values = ContentValues()
            values.put(COLUMN_CONTENT, alarm.getContent())

            val filter = COLUMN_TITLE + " = \"" + alarm.getTitle() + "\""
            db.update(TABLE_ALARM, values, filter, null)
            db.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteAlarm(title: String) {

        try {

            val db = this.writableDatabase

            val filter = "$COLUMN_TITLE = \"$title\""
            db.delete(TABLE_ALARM, filter, null)
            db.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun deleteMaxData() {

        try {

            val db = this.writableDatabase

            val query = "DELETE from alarms where _id = (select min(_id) from alarms);"
            val cursor = db.rawQuery(query, null)
            cursor.moveToFirst()
            db.close()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getMaxData(): Alarm {

        var alarm = Alarm()

        try {

            val db = this.readableDatabase
            val query = "select * from alarms where _id = (select min(_id) from alarms)"
            val cursor: Cursor = db.rawQuery(query, null)

            if (cursor != null && cursor.moveToFirst() && cursor.count > 0) {
                cursor.moveToFirst()
                alarm = Alarm(
                    cursor.getString(0).toInt(),
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getString(
                        3
                    )
                )
                cursor.close()
            }
            db.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        return alarm
    }

    fun clearAlarm() {

        try {
            val db = this.writableDatabase

            db.delete(TABLE_ALARM, "", null)
            db.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    fun getAlarmList(): List<Alarm> {

        val alarmList: MutableList<Alarm> = ArrayList()

        try {

            val db = this.readableDatabase
            val cursor = db.rawQuery("Select _id, title, content, writeDate from $TABLE_ALARM",null)

            while (cursor.moveToNext()) {
                val id = cursor.getInt(0)
                val title = cursor.getString(1)
                val content = cursor.getString(2)
                val writeDate = cursor.getString(3)
                val alarm = Alarm(id, title, content, writeDate)
                alarmList.add(alarm)
            }
            cursor.close()
            db.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return alarmList
    }

    fun getRowCount(): Int {

        var count: Long = 0

        try {

            val db = this.readableDatabase
            count = DatabaseUtils.queryNumEntries(db, TABLE_ALARM)
            db.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return count.toInt()
    }

    fun findAlarm(title: String): Boolean {

        var bFind = false

        try {

            val db = this.readableDatabase
            val query = "SELECT * FROM $TABLE_ALARM WHERE $COLUMN_TITLE = \"$title\""
            val cursor = db.rawQuery(query, null)
            var idValue = 0

            if (cursor != null) {
                cursor.moveToFirst()
                idValue = cursor.getInt(0)
                cursor.close()
                bFind = true
            }
            db.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return bFind
    }

    fun getAlarm(title: String): Alarm {

        var alarm = Alarm()

        try {

            val db = this.readableDatabase
            val query = "SELECT * FROM $TABLE_ALARM WHERE $COLUMN_TITLE = \"$title\""
            val cursor = db.rawQuery(query, null)

            if (cursor.moveToFirst()) {
                cursor.moveToFirst()
                alarm!!.setId(cursor.getString(0).toInt())
                alarm.setTitle(cursor.getString(1))
                alarm.setContent(cursor.getString(2))
                alarm.setWriteDate(cursor.getString(3))
                cursor.close()
            }
            db.close()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        return alarm
    }

}