package com.daeseong.db_test

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.daeseong.db_test.SQLiteOpenHelper.Alarm
import com.daeseong.db_test.SQLiteOpenHelper.DbHelperAlarm
import java.text.SimpleDateFormat
import java.util.*


class MainActivity : AppCompatActivity() , View.OnClickListener {

    private val tag: String = MainActivity::class.java.simpleName

    var editTitle: EditText? = null
    var editContent:EditText? = null

    var btnAdd: Button? = null
    var btnRead:Button? = null
    var btnUpdate:Button? = null
    var btnDelete:Button? = null

    var textResult: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        editTitle = findViewById<EditText>(R.id.editTitle)
        editContent = findViewById<EditText>(R.id.editContent)

        btnAdd = findViewById<Button>(R.id.btnAdd)
        btnRead = findViewById<Button>(R.id.btnRead)
        btnUpdate = findViewById<Button>(R.id.btnUpdate)
        btnDelete = findViewById<Button>(R.id.btnDelete)

        textResult = findViewById<TextView>(R.id.textResult)

        btnAdd!!.setOnClickListener(this)
        btnRead!!.setOnClickListener(this)
        btnUpdate!!.setOnClickListener(this)
        btnDelete!!.setOnClickListener(this)

        try {

            val dbHelperAlarm = DbHelperAlarm(this, null, null, 1)
            val nCount = dbHelperAlarm.getRowCount()
            val sCount = "개수:$nCount"
            textResult!!.text = sCount

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onClick(v: View?) {

        if (v != null) {

            when (v.id) {

                R.id.btnAdd -> {
                    Add()
                }

                R.id.btnRead -> {
                    read()
                }

                R.id.btnUpdate -> {
                    update()
                }

                R.id.btnDelete -> {
                    delete()
                }
            }
        }
    }

    private fun Add() {

        try {

            val title = editTitle!!.text.toString()
            val content = editContent!!.text.toString()
            val alarm = Alarm(title, content)
            val dbHelperAlarm = DbHelperAlarm(this, null, null, 1)
            dbHelperAlarm.addAlarm(alarm)


            //입력 데이타가 10개가 넘으면 가장 처음 입력한 데이타 삭제
            val nCount = dbHelperAlarm.getRowCount()
            if (nCount > 10) {
                dbHelperAlarm.deleteMaxData()
            }

            editTitle!!.setText("")
            editContent!!.setText("")
            editTitle!!.requestFocus()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun read() {

        try {

            val dbHelperAlarm = DbHelperAlarm(this, null, null, 1)
            val alarmList = dbHelperAlarm.getAlarmList()

            var sMsg = ""
            for (alarm in alarmList) {
                sMsg += """id : ${alarm.getId()} / title : ${alarm.getTitle()} / content : ${alarm.getContent()} / WriteDate : ${alarm.getWriteDate()}"""
            }
            textResult!!.text = sMsg

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
    }

    private fun update() {

        try {

            val title = editTitle!!.text.toString()
            val content = editContent!!.text.toString()
            val dbHelperAlarm = DbHelperAlarm(this, null, null, 1)

            if (dbHelperAlarm.findAlarm(title)) {

                val alarm = dbHelperAlarm.getAlarm(title)
                val utitle = alarm.getTitle()
                //val ucontent = alarm.getContent()
                //val uwriteDate = alarm.getWriteDate()

                /*
                //현재 시간
                val lNow = System.currentTimeMillis()
                val date = Date(lNow)
                val sdf = SimpleDateFormat("yyyy-MM-dd")
                val currentTime: String = sdf.format(date)
                */

                dbHelperAlarm.updateAlarm(Alarm(utitle, content))
                editTitle!!.setText("")
                editContent!!.setText("")
                editTitle!!.requestFocus()

                //update 후 데이타 확인
                read()

            } else {
                textResult!!.text = "데이타 미존재"
            }

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

    }

    private fun delete() {

        try {

            val title = editTitle!!.text.toString()
            val dbHelperAlarm = DbHelperAlarm(this, null, null, 1)
            dbHelperAlarm.deleteAlarm(title)
            editTitle!!.setText("")
            editTitle!!.requestFocus()

            //delete 후 데이타 확인
            read()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }

        /*
        //가장 처음 입력한 데이타 조회
        val dbHelperAlarm = DbHelperAlarm(this, null, null, 1)
        val alarm = dbHelperAlarm.getMaxData()
        var sMsg = ""
        sMsg += """id : ${alarm.getId()} / title : ${alarm.getTitle()} / content : ${alarm.getContent()} / WriteDate : ${alarm.getWriteDate()}"""
        textResult!!.text = sMsg
        */

        /*
        //전체 데이타 삭제
        try {

            val dbHelperAlarm = DbHelperAlarm(this, null, null, 1)
            dbHelperAlarm.clearAlarm()
            editTitle!!.setText("")
            editTitle!!.requestFocus()

            //delete 후 데이타 확인
            read()

        } catch (e: java.lang.Exception) {
            e.printStackTrace()
        }
        */

    }

}
