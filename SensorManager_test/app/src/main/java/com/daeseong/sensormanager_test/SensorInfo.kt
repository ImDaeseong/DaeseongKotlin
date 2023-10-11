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
    private var mContext: Context? = null

    private val sensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {

            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
                handleAccelerometerSensor(event)
            } else if (event.sensor.type == Sensor.TYPE_LIGHT) {
                handleLightSensor(event)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            // Do nothing for now
        }
    }

    private fun handleAccelerometerSensor(event: SensorEvent) {
        val dX = event.values[0].toDouble()
        val dY = event.values[1].toDouble()
        val dZ = event.values[2].toDouble()
        val angleX = Math.atan2(dX, dZ) * 180 / Math.PI
        val angleY = Math.atan2(dY, dZ) * 180 / Math.PI
        val fFrentX = Math.abs(angleX)
        val fFrentY = Math.abs(angleY)

        val broadcastIntent = Intent("com.daeseong.sensormanager_test.Front")

        if (fFrentX > 170 && fFrentY > 170) {
            broadcastIntent.putExtra("front", "뒤면")
        } else {
            broadcastIntent.putExtra("front", "앞면")
        }

        mContext?.sendBroadcast(broadcastIntent)
    }

    private fun handleLightSensor(event: SensorEvent) {
        val fFrent = Math.abs(event.values[0])

        val broadcastIntent = Intent("com.daeseong.sensormanager_test.Front")

        if (fFrent == 0f) {
            broadcastIntent.putExtra("front", "뒤면")
        } else {
            broadcastIntent.putExtra("front", "앞면")
        }

        mContext?.sendBroadcast(broadcastIntent)
    }

    fun initSensor(context: Context) {
        mContext = context
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        accelSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        lightSensor = sensorManager?.getDefaultSensor(Sensor.TYPE_LIGHT)

        sensorManager?.registerListener(
            sensorEventListener,
            accelSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )

        sensorManager?.registerListener(
            sensorEventListener,
            lightSensor,
            SensorManager.SENSOR_DELAY_NORMAL
        )
    }

    fun destroySensor() {
        sensorManager?.unregisterListener(sensorEventListener)
    }

}
