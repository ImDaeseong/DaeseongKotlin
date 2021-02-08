package com.daeseong.sensormanager_test

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Main1Activity : AppCompatActivity() {

    private val tag: String = Main1Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var stringBuffer = StringBuffer()
    private var sensorManager: SensorManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if(sensorManager != null){

            if(sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER) != null) {
                stringBuffer.append("TYPE_ACCELEROMETER 가속도 감지,외부충격량과 방향 감지 센서\n");
            }

            if(sensorManager!!.getDefaultSensor(Sensor.TYPE_GYROSCOPE) != null) {
                stringBuffer.append("TYPE_GYROSCOPE 자이로스코프\n");
            }

            if(sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT) != null) {
                stringBuffer.append("TYPE_LIGHT 빛의 세기 감지 센서\n");
            }

            if(sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_COUNTER) != null) {
                stringBuffer.append("TYPE_STEP_COUNTER 발걸음 횟수 센서\n");
            }

            if(sensorManager!!.getDefaultSensor(Sensor.TYPE_STEP_DETECTOR) != null) {
                stringBuffer.append("TYPE_STEP_DETECTOR 발걸음 감지 센서\n");
            }
        }

        tv1 = findViewById<TextView>(R.id.tv1)
        tv1!!.text = stringBuffer.toString()
    }
}
