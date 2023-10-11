package com.daeseong.sensormanager_test

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag: String = Main1Activity::class.java.simpleName

    private lateinit var tv1: TextView
    private val stringBuffer = StringBuilder()
    private var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        sensorManager?.let {
            appendSensorInfo(it, Sensor.TYPE_ACCELEROMETER, "TYPE_ACCELEROMETER 가속도 감지,외부충격량과 방향 감지 센서")
            appendSensorInfo(it, Sensor.TYPE_GYROSCOPE, "TYPE_GYROSCOPE 자이로스코프")
            appendSensorInfo(it, Sensor.TYPE_LIGHT, "TYPE_LIGHT 빛의 세기 감지 센서")
            appendSensorInfo(it, Sensor.TYPE_STEP_COUNTER, "TYPE_STEP_COUNTER 발걸음 횟수 센서")
            appendSensorInfo(it, Sensor.TYPE_STEP_DETECTOR, "TYPE_STEP_DETECTOR 발걸음 감지 센서")
        }

        tv1 = findViewById(R.id.tv1)
        tv1.text = stringBuffer.toString()
    }

    private fun appendSensorInfo(sensorManager: SensorManager, sensorType: Int, sensorDesc: String) {
        if (sensorManager.getDefaultSensor(sensorType) != null) {
            stringBuffer.append("$sensorDesc\n")
        }
    }
}
