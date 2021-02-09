package com.daeseong.singleton_test

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity
import com.daeseong.singleton_test.Db.Alarm
import com.daeseong.singleton_test.Db.DbHandler
import com.daeseong.singleton_test.util.Screenutil
import com.daeseong.singleton_test.util.SharedPreferencesutil


class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //Screenutil
        var nWidth  = Screenutil.getInstance().getScreenWidthPx();
        var nHeight  = Screenutil.getInstance().getScreenHeightPx();
        Log.e(tag, "ScreenWidthPx:$nWidth ScreenHeightPx:$nHeight");


        //MyApplication
        var sdk = MyApplication.getInstance().getVersionSDK()
        var verRelease = MyApplication.getInstance().getVersionRelease()
        var device = MyApplication.getInstance().getDevice()
        Log.e(tag, "sdk:$sdk verRelease:$verRelease device:$device");


        //SharedPreferencesutil
        val sID = SharedPreferencesutil.getInstance().getData("ID", "") as String
        Log.e(tag, "ID:$sID");

        val sMemNum = SharedPreferencesutil.getInstance().getData("MEM_NUM", 0) as Int
        Log.e(tag, "sMemNum:$sMemNum");

        val bFlag = SharedPreferencesutil.getInstance().getData("FLAG", false) as Boolean
        Log.e(tag, "bFlag:$bFlag");

        if (sID.isEmpty()) {
            SharedPreferencesutil.getInstance().setData("ID", "userid");
        } else {
            SharedPreferencesutil.getInstance().remove("ID");
        }

        if (sMemNum == 0) {
            SharedPreferencesutil.getInstance().setData("MEM_NUM", 5000);
        } else {
            SharedPreferencesutil.getInstance().remove("MEM_NUM");
        }

        if (!bFlag) {
            SharedPreferencesutil.getInstance().setData("FLAG", true);
        } else {
            SharedPreferencesutil.getInstance().remove("FLAG");
        }

        //SharedPreferencesutil.getInstance().clear()

        //AddAll()
        //Add()
        //delete()
        //clearAlarm()
        //findAlarm()
    }

    private fun AddAll() {

        try {

            for(i in 1..20){
                val alarm = Alarm("title$i", "content$i")
                DbHandler.getInstance().addAlarm(alarm)
            }

            //입력 데이타가 10개가 넘으면 가장 처음 입력한 데이타 삭제
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

            //입력 데이타가 10개가 넘으면 가장 처음 입력한 데이타 삭제
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
            for (alarm in alarmList) {
                sMsg += "id : " + alarm.id + " / title : " + alarm.title + " / content : " + alarm.content + " / WriteDate : " + alarm.writeDate + "\n"
            }
            Log.e(tag, "DbHandler read:$sMsg")
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun delete() {
        try {
            DbHandler.getInstance().deleteAlarm("title")
            read()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun clearAlarm() {
        try {
            DbHandler.getInstance().clearAlarm()
            read()
        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun findAlarm() {

        DbHandler.getInstance().addAlarm(Alarm("title1", "content1"))
        DbHandler.getInstance().addAlarm(Alarm("title2", "content2"))
        DbHandler.getInstance().addAlarm(Alarm("title3", "content3"))

        val maxAlarm = DbHandler.getInstance().getAlarm("title4");
        if (maxAlarm != null) {
            Log.e(tag,"id:${maxAlarm.id}  title:${maxAlarm.title} content:${maxAlarm.content} WriteDate:${maxAlarm.writeDate}");
        }

        val bAlarm = DbHandler.getInstance().findAlarm("title4");
        Log.e(tag, "bAlarm:$bAlarm");
    }
}
