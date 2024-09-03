package com.daeseong.sensormanager_test

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager

class SensorInfo private constructor() {

    private val tag: String = SensorInfo::class.java.simpleName

    companion object {
        @JvmStatic
        private var instance: SensorInfo? = null

        @JvmStatic
        fun getInstance(): SensorInfo {
            return instance ?: synchronized(this) {
                instance ?: SensorInfo().also { instance = it }
            }
        }
    }

    private var sensorManager: SensorManager? = null
    private var accelSensor: Sensor? = null
    private var lightSensor: Sensor? = null
    private var context: Context? = null

    private val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            when (event.sensor.type) {
                Sensor.TYPE_ACCELEROMETER -> handleAccelerometerSensor(event)
                Sensor.TYPE_LIGHT -> handleLightSensor(event)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Optionally handle accuracy changes
        }
    }

    private fun handleAccelerometerSensor(event: SensorEvent) {
        val (dX, dY, dZ) = event.values.map { it.toDouble() }
        val angleX = Math.atan2(dX, dZ) * 180 / Math.PI
        val angleY = Math.atan2(dY, dZ) * 180 / Math.PI
        val fFrentX = Math.abs(angleX)
        val fFrentY = Math.abs(angleY)

        val broadcastIntent = Intent("com.daeseong.sensormanager_test.Front").apply {
            putExtra("front", if (fFrentX > 170 && fFrentY > 170) "뒤면" else "앞면")
        }
        context?.sendBroadcast(broadcastIntent)
    }

    private fun handleLightSensor(event: SensorEvent) {
        val fFrent = Math.abs(event.values[0])

        val broadcastIntent = Intent("com.daeseong.sensormanager_test.Front").apply {
            putExtra("front", if (fFrent == 0f) "뒤면" else "앞면")
        }
        context?.sendBroadcast(broadcastIntent)
    }

    fun initSensor(context: Context) {
        this.context = context
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as? SensorManager

        accelSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        lightSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        accelSensor?.let {
            sensorManager?.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL)
        }

        lightSensor?.let {
            sensorManager?.registerListener(sensorEventListener, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    fun destroySensor() {
        sensorManager?.unregisterListener(sensorEventListener)
        context = null
        sensorManager = null
        accelSensor = null
        lightSensor = null
    }
}
