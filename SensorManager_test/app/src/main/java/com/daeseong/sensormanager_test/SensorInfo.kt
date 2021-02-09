package com.daeseong.sensormanager_test

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log


class SensorInfo {

    private val tag: String = SensorInfo::class.java.simpleName

    companion object {

        private var instance: SensorInfo? = null
        fun getInstance(): SensorInfo {
            if (instance == null) {
                instance = SensorInfo()
            }
            return instance as SensorInfo
        }
    }

    private var sensorManager: SensorManager? = null
    private var Accelsensor: Sensor? = null
    private var Lightsensor: Sensor? = null
    private var mContext: Context? = null

    private var sensorEventListener: SensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {

            //Log.e(tag, "onSensorChanged")

            if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {

                val dX = event.values[0].toDouble()
                val dY = event.values[1].toDouble()
                val dZ = event.values[2].toDouble()
                val angleX = Math.atan2(dX, dZ) * 180 / Math.PI
                val angleY = Math.atan2(dY, dZ) * 180 / Math.PI
                val fFrentX = Math.abs(angleX)
                val fFrentY = Math.abs(angleY)

                if (fFrentX > 170 && fFrentY > 170) {
                    val item = Intent("com.daeseong.sensormanager_test.Front")
                    item.putExtra("front", "뒤면")
                    mContext!!.sendBroadcast(item)
                } else {
                    val item = Intent("com.daeseong.sensormanager_test.Front")
                    item.putExtra("front", "앞면")
                    mContext!!.sendBroadcast(item)
                }

            } else if (event.sensor.type == Sensor.TYPE_LIGHT) {

                val fFrent = Math.abs(event.values[0])

                if (fFrent == 0f) {
                    val item = Intent("com.daeseong.sensormanager_test.Front")
                    item.putExtra("front", "뒤면")
                    mContext!!.sendBroadcast(item)
                } else {
                    val item = Intent("com.daeseong.sensormanager_test.Front")
                    item.putExtra("front", "앞면")
                    mContext!!.sendBroadcast(item)
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {

            //Log.e(tag, "onAccuracyChanged")
        }
    }

    fun initSensor(context: Context) {

        //Log.e(tag, "initSensor")

        mContext = context
        sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if (sensorManager != null) {
            Accelsensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
            Lightsensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT)

            sensorManager!!.registerListener(
                sensorEventListener,
                Accelsensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )

            sensorManager!!.registerListener(
                sensorEventListener,
                Lightsensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
        }
    }

    fun DestorySeonsor() {

        //Log.e(tag, "DestorySeonsor")

        if (sensorManager != null) {
            sensorManager!!.unregisterListener(sensorEventListener)
        }
    }
}