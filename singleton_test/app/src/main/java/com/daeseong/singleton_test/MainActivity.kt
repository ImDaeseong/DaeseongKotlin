package com.daeseong.singleton_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.im.daeseong.singleton_test.Db.Alarm
import com.im.daeseong.singleton_test.MyApplication
import com.im.daeseong.singleton_test.util.Screenutil
import com.im.daeseong.singleton_test.util.SharedPreferencesUtil

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Screenutil
        val screenWidth = Screenutil.getInstance().getScreenWidthPx()
        val screenHeight = Screenutil.getInstance().getScreenHeightPx()
        Log.e(tag, "ScreenWidthPx:$screenWidth ScreenHeightPx:$screenHeight")

        // MyApplication
        val sdk = MyApplication.getInstance().getVersionSDK()
        val verRelease = MyApplication.getInstance().getVersionRelease()
        val device = MyApplication.getInstance().getDevice()
        Log.e(tag, "sdk:$sdk verRelease:$verRelease device:$device")

        // SharedPreferencesutil
        val sID: String = SharedPreferencesUtil.getInstance().getValue("ID", "") as String
        Log.e(tag, "ID:$sID")

        val sMemNum: Int = SharedPreferencesUtil.getInstance().getValue("MEM_NUM", 0) as Int
        Log.e(tag, "sMemNum:$sMemNum")

        val bFlag: Boolean = SharedPreferencesUtil.getInstance().getValue("FLAG", false) as Boolean
        Log.e(tag, "bFlag:$bFlag")

        if (sID.isEmpty()) {
            SharedPreferencesUtil.getInstance().setValue("ID", "userid")
        } else {
            SharedPreferencesUtil.getInstance().remove("ID")
        }

        if (sMemNum == 0) {
            SharedPreferencesUtil.getInstance().setValue("MEM_NUM", 5000)
        } else {
            SharedPreferencesUtil.getInstance().remove("MEM_NUM")
        }

        if (!bFlag) {
            SharedPreferencesUtil.getInstance().setValue("FLAG", true)
        } else {
            SharedPreferencesUtil.getInstance().remove("FLAG")
        }

        // Uncomment the method calls if needed
        // AddAll()
        // Add()
        // delete()
        // clearAlarm()
        // findAlarm()
    }

    private fun AddAll() {
        try {
            for (i in 1..20) {
                val alarm = Alarm("title$i", "content$i")
                DbHandler.getInstance().addAlarm(alarm)
            }

            val nCount = DbHandler.getInstance().getRowCount()
            if (nCount > 10) {
                DbHandler.getInstance().deleteMaxData()
            }
            read()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun Add() {
        try {
            val alarm = Alarm("title", "content")
            DbHandler.getInstance().addAlarm(alarm)

            val nCount = DbHandler.getInstance().getRowCount()
            if (nCount > 10) {
                DbHandler.getInstance().deleteMaxData()
            }
            read()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun read() {
        try {
            val alarmList = DbHandler.getInstance().getAlarmList()
            var sMsg = ""
            alarmList?.forEach { alarm ->
                sMsg += "id : ${alarm.id} / title : ${alarm.title} / content : ${alarm.content} / WriteDate : ${alarm.writeDate}\n"
            }
            Log.e(tag, "DbHandler read:$sMsg")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun delete() {
        try {
            DbHandler.getInstance().deleteAlarm("title")
            read()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun clearAlarm() {
        try {
            DbHandler.getInstance().clearAlarm()
            read()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun findAlarm() {
        DbHandler.getInstance().apply {
            addAlarm(Alarm("title1", "content1"))
            addAlarm(Alarm("title2", "content2"))
            addAlarm(Alarm("title3", "content3"))

            getAlarm("title4")?.let { maxAlarm ->
                Log.e(tag, "id:${maxAlarm.id}  title:${maxAlarm.title} content:${maxAlarm.content} WriteDate:${maxAlarm.writeDate}")
            }

            val bAlarm = findAlarm("title4")
            Log.e(tag, "bAlarm:$bAlarm")
        }
    }
}
