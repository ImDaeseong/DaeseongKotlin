package com.daeseong.alarm_test


import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView.OnItemClickListener
import android.widget.Button
import android.widget.ListView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class Main6Activity : AppCompatActivity() {

    private val tag = Main5Activity::class.java.simpleName

    private var button1 : Button? = null
    private var button2 : Button? = null
    private var button3 : Button? = null

    private var listview1: ListView? = null
    private var alarmAdapter: AlarmAdapter? = null

    private var alarmdbHelper: AlarmdbHelper? = null

    private var timePicker: TimePicker? = null
    private var nhour : Int = 0
    private var nMinute : Int = 0

    private var selectID : Int = 0

    private var broadcastReceiver: BroadcastReceiver? = null
    private var intentFilter: IntentFilter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        initFilter()

        //알람 설정 초기화
        Alarm_util.getInstance().init(this)

        //DB 초기화
        alarmdbHelper = AlarmdbHelper(this)

        //Adapter 선언
        alarmAdapter = AlarmAdapter()

        //db 데이터 전체 삭제
        //alarmdbHelper!!.deleteAlarmAll()


        //데이터 베이스 저장 항목 리스트 설정 - 앱 재시작시 데이터베이스 정보에 맞게 재등록 필요(재등록시 삭제후 재등록)
        val list = alarmdbHelper!!.getAllAlarms()
        for (i in list.indices) {

            //리스트뷰
            alarmAdapter!!.addItem(list[i].getID(), list[i].getHour(), list[i].getMinute())

            //알람 서비스 삭제
            Alarm_util.getInstance().DeleteAlarm(list[i].getID())

            //알람 서비스 등록
            Alarm_util.getInstance().AddAlaram( list[i].getID(), list[i].getHour(), list[i].getMinute() )
        }


        //listview 설정
        listview1 = findViewById<ListView>(R.id.listview1)
        listview1!!.adapter = alarmAdapter
        listview1!!.onItemClickListener = OnItemClickListener { parent, view, position, id ->

            val item = parent.getItemAtPosition(position) as Alarm
            selectID = item.getID()
            Log.e(tag,"id:" + item.getID() + " hour:" + item.getHour() + " minute:" + item.getMinute())
        }


        //시간 설정
        timePicker = findViewById<TimePicker>(R.id.timepicker)
        timePicker!!.setIs24HourView(false)
        timePicker!!.setOnTimeChangedListener { view, hourOfDay, minute ->

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                nhour = timePicker!!.hour
                nMinute = timePicker!!.minute
            } else {
                nhour = timePicker!!.getCurrentHour()
                nMinute = timePicker!!.getCurrentMinute()
            }

            Log.e(tag, "nhour:$nhour nMinute:$nMinute")
            Log.e(tag, "hourOfDay:$hourOfDay minute:$minute")
        }


        //추가
        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            if (nhour > 0) {

                //db 추가
                val alarm = Alarm(0, nhour, nMinute)
                val id = alarmdbHelper!!.addAlarm(alarm)

                //알람 서비스 등록
                Alarm_util.getInstance().AddAlaram(id.toInt(), nhour, nMinute)

                //리스트뷰 등록
                alarmAdapter!!.addItem(id.toInt(), nhour, nMinute)
                alarmAdapter!!.notifyDataSetChanged()
                val sMsg = String.format("알람 서비스 등록 %d시 %d분 호출 예정", nhour, nMinute)
                Toast.makeText(applicationContext, sMsg, Toast.LENGTH_SHORT).show()
            }
        }

        //수정
        button2 = findViewById<Button>(R.id.button2)
        button2!!.setOnClickListener {

            if (selectID > 0) {
                val alarm = Alarm(selectID, nhour, nMinute)
                val id = alarmdbHelper!!.updateAlarm(alarm)
                if (id.toInt() == selectID) {

                    //알람 서비스 삭제 후 재등록
                    Alarm_util.getInstance().DeleteAlarm(selectID)
                    Alarm_util.getInstance().AddAlaram(selectID, nhour, nMinute)

                    //리스트뷰 업데이트
                    alarmAdapter!!.updateItem(selectID, nhour, nMinute)
                    alarmAdapter!!.notifyDataSetChanged()
                    selectID = 0
                    val sMsg = String.format("수정된 알람 서비스 등록 %d시 %d분 호출 예정", nhour, nMinute)
                    Toast.makeText(applicationContext, sMsg, Toast.LENGTH_SHORT).show()
                }
            }
        }

        //삭제
        button3 = findViewById<Button>(R.id.button3)
        button3!!.setOnClickListener {

            if( selectID > 0 ){

                //db 삭제
                alarmdbHelper!!.deleteAlarm(selectID)

                //알람 서비스 삭제
                Alarm_util.getInstance().DeleteAlarm(selectID)

                //리스트뷰 삭제
                alarmAdapter!!.removeItem(selectID)
                alarmAdapter!!.notifyDataSetChanged()

                selectID = 0;
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        DestoryFilter()
    }

    private fun initFilter() {

        broadcastReceiver = object : BroadcastReceiver() {

            override fun onReceive(context: Context?, intent: Intent) {

                if (intent.action == "com.daeseong.alarm_test.ID") {

                    val receiveID = intent.extras!!.getInt("alarmID")
                    Log.e(tag, "receiveID:$receiveID")

                    //db 삭제
                    alarmdbHelper!!.deleteAlarm(receiveID)

                    //알람 서비스 삭제
                    Alarm_util.getInstance().DeleteAlarm(receiveID)

                    //리스트뷰 삭제
                    alarmAdapter!!.removeItem(receiveID)
                    alarmAdapter!!.notifyDataSetChanged()
                }
            }
        }

        intentFilter = IntentFilter()
        intentFilter!!.addAction("com.daeseong.alarm_test.ID")
        registerReceiver(broadcastReceiver, intentFilter)
    }

    private fun DestoryFilter() {

        if(broadcastReceiver != null) {
            unregisterReceiver(broadcastReceiver)
        }
    }

}
