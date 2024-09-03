package com.daeseong.sensormanager_test

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs
import kotlin.math.atan2

class Main3Activity : AppCompatActivity() {

    private val tag: String = Main3Activity::class.java.simpleName

    private lateinit var tvOrientation: TextView
    private lateinit var tvLight: TextView

    private lateinit var sensorManager: SensorManager
    private lateinit var accelerometerSensor: Sensor
    private lateinit var lightSensor: Sensor

    private val sensorEventListener: SensorEventListener = object : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent) {

            when (event.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> handleAccelerometerSensor(event)
                Sensor.TYPE_LIGHT -> handleLightSensor(event)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Accuracy change handling, if needed
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tvOrientation  = findViewById(R.id.tv1)
        tvLight  = findViewById(R.id.tv2)

        initSensor()
    }

    override fun onDestroy() {
        super.onDestroy()
        destroySensors()
    }

    private fun initSensor() {

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)!!
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL)

        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT)!!
        sensorManager.registerListener(sensorEventListener, lightSensor, SensorManager.SENSOR_DELAY_NORMAL)
    }

    private fun destroySensors() {
        sensorManager.unregisterListener(sensorEventListener)
    }

    private fun handleAccelerometerSensor(event: SensorEvent) {
        val angleX = Math.toDegrees(atan2(event.values[0].toDouble(), event.values[2].toDouble()))
        val angleY = Math.toDegrees(atan2(event.values[1].toDouble(), event.values[2].toDouble()))

        runOnUiThread {
            if (abs(angleX) > 170 && abs(angleY) > 170) {
                startActivity(Intent(this@Main3Activity, LockActivity::class.java).apply {
                    addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                })
                tvOrientation.text = "뒤면"
            } else {
                tvOrientation.text = "앞면"
            }
        }
    }

    private fun handleLightSensor(event: SensorEvent) {
        val lightValue = abs(event.values[0])

        runOnUiThread {
            tvLight.text = if (lightValue == 0f) "뒤면" else "앞면"
        }
    }
}
