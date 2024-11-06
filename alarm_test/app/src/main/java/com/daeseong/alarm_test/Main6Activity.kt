package com.daeseong.alarm_test

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.AdapterView
import android.widget.Button
import android.widget.ListView
import android.widget.TimePicker
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Main6Activity : AppCompatActivity() {

    private val tag = Main6Activity::class.java.simpleName

    private lateinit var buttonAdd: Button
    private lateinit var buttonUpdate: Button
    private lateinit var buttonDelete: Button

    private lateinit var listView: ListView
    private lateinit var alarmAdapter: AlarmAdapter

    private lateinit var alarmDbHelper: AlarmdbHelper

    private lateinit var timePicker: TimePicker
    private var selectedHour: Int = 0
    private var selectedMinute: Int = 0

    private var selectedAlarmId: Int = 0

    private lateinit var broadcastReceiver: BroadcastReceiver
    private lateinit var intentFilter: IntentFilter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main6)

        initBroadcastReceiver()

        // 알람 설정 초기화
        AlarmUtil.getInstance().init(this)

        // DB 초기화
        alarmDbHelper = AlarmdbHelper(this)

        // 어댑터 초기화
        alarmAdapter = AlarmAdapter()

        // 알람 서비스 설정
        setupAlarmData()

        // 리스트뷰 설정
        listView = findViewById(R.id.listview1)
        listView.adapter = alarmAdapter
        listView.onItemClickListener = AdapterView.OnItemClickListener { parent, _, position, _ ->

            val item = parent.getItemAtPosition(position) as Alarm
            selectedAlarmId = item.ID

            Log.e(tag, "선택된 알람 ID: ${item.ID}, 시간: ${item.nHour}, 분: ${item.nMinute}")
        }

        // TimePicker 설정
        timePicker = findViewById(R.id.timepicker)
        timePicker.setIs24HourView(true) // 24시간 형식 설정 (API 23 이상)

        timePicker.setOnTimeChangedListener { _, hourOfDay, minute ->
            selectedHour = hourOfDay
            selectedMinute = minute
            Log.e(tag, "선택된 시간: $selectedHour 시 $selectedMinute 분")
        }

        // 추가 버튼 설정
        buttonAdd = findViewById(R.id.button1)
        buttonAdd.setOnClickListener {

            if (selectedHour >= 0) {

                // DB에 알람 추가
                val alarm = Alarm(0, selectedHour, selectedMinute)
                val id = alarmDbHelper.addAlarm(alarm)

                // 알람 서비스 등록
                AlarmUtil.getInstance().addAlarm(id.toInt(), selectedHour, selectedMinute)

                // 리스트뷰 업데이트
                alarmAdapter.addItem(id.toInt(), selectedHour, selectedMinute)
                alarmAdapter.notifyDataSetChanged()

                val message = String.format("알람 서비스 등록: %d시 %d분 호출 예정", selectedHour, selectedMinute)
                Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
            }
        }

        // 수정 버튼 설정
        buttonUpdate = findViewById(R.id.button2)
        buttonUpdate.setOnClickListener {

            if (selectedAlarmId > 0) {

                val alarm = Alarm(selectedAlarmId, selectedHour, selectedMinute)
                val updatedId = alarmDbHelper.updateAlarm(alarm)
                if (updatedId.toInt() == selectedAlarmId) {

                    // 기존 알람 서비스 삭제 후 재등록
                    AlarmUtil.getInstance().deleteAlarm(selectedAlarmId)
                    AlarmUtil.getInstance().addAlarm(selectedAlarmId, selectedHour, selectedMinute)

                    // 리스트뷰 업데이트
                    alarmAdapter.updateItem(selectedAlarmId, selectedHour, selectedMinute)
                    alarmAdapter.notifyDataSetChanged()
                    selectedAlarmId = 0

                    val message = String.format("수정된 알람 서비스 등록: %d시 %d분 호출 예정", selectedHour, selectedMinute)
                    Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
                }
            }
        }

        // 삭제 버튼 설정
        buttonDelete = findViewById(R.id.button3)
        buttonDelete.setOnClickListener {

            if (selectedAlarmId > 0) {

                // DB에서 알람 삭제
                alarmDbHelper.deleteAlarm(selectedAlarmId)

                // 알람 서비스 삭제
                AlarmUtil.getInstance().deleteAlarm(selectedAlarmId)

                // 리스트뷰 업데이트
                alarmAdapter.removeItem(selectedAlarmId)
                alarmAdapter.notifyDataSetChanged()

                selectedAlarmId = 0
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        unregisterReceiverSafely(broadcastReceiver)
    }

    private fun initBroadcastReceiver() {
        broadcastReceiver = object : BroadcastReceiver() {
            override fun onReceive(context: Context?, intent: Intent) {

                if (intent.action == "com.daeseong.alarm_test.ID") {

                    val receiveID = intent.extras?.getInt("alarmID") ?: return
                    Log.e(tag, "받은 알람 ID: $receiveID")

                    // DB에서 알람 삭제
                    alarmDbHelper.deleteAlarm(receiveID)

                    // 알람 서비스 삭제
                    AlarmUtil.getInstance().deleteAlarm(receiveID)

                    // 리스트뷰에서 삭제
                    alarmAdapter.removeItem(receiveID)
                    alarmAdapter.notifyDataSetChanged()
                }
            }
        }

        intentFilter = IntentFilter().apply {
            addAction("com.daeseong.alarm_test.ID")
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            registerReceiver(broadcastReceiver, intentFilter, Context.RECEIVER_EXPORTED)
        } else {
            registerReceiver(broadcastReceiver, intentFilter)
        }
    }

    private fun unregisterReceiverSafely(receiver: BroadcastReceiver) {
        try {
            unregisterReceiver(receiver)
        } catch (e: IllegalArgumentException) {
        }
    }

    private fun setupAlarmData() {

        val alarms = alarmDbHelper.getAllAlarms()
        alarms.forEach { alarm ->
            alarmAdapter.addItem(alarm.ID, alarm.nHour, alarm.nMinute)
            AlarmUtil.getInstance().apply {
                deleteAlarm(alarm.ID)
                addAlarm(alarm.ID, alarm.nHour, alarm.nMinute)
            }
        }
    }
}
