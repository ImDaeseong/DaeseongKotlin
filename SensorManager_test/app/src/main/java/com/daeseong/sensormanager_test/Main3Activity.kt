package com.daeseong.sensormanager_test

import android.content.Context
import android.content.Intent
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Main3Activity : AppCompatActivity() {

    private val tag: String = Main3Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var tv2: TextView? = null

    private var sensorManager: SensorManager? = null
    private var Accelsensor: Sensor? = null
    private var Lightsensor: Sensor? = null

    private var sensorEventListener: SensorEventListener = object : SensorEventListener {

        override fun onSensorChanged(event: SensorEvent) {

            if (event.sensor.type === Sensor.TYPE_ACCELEROMETER) {
                val dX = event.values[0].toDouble()
                val dY = event.values[1].toDouble()
                val dZ = event.values[2].toDouble()
                val angleX = Math.atan2(dX, dZ) * 180 / Math.PI
                val angleY = Math.atan2(dY, dZ) * 180 / Math.PI
                val fFrentX = Math.abs(angleX)
                val fFrentY = Math.abs(angleY)
                runOnUiThread {
                    if (fFrentX > 170 && fFrentY > 170) {
                        val intent = Intent(this@Main3Activity, LockActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        //DestorySeonsor()
                        tv1!!.text = "뒤면"
                    } else {
                        tv1!!.text = "앞면"
                    }
                }
            } else if (event.sensor.type === Sensor.TYPE_LIGHT) {
                val fFrent = Math.abs(event.values[0])
                runOnUiThread {
                    if (fFrent == 0f) {
                        tv2!!.text = "뒤면"
                    } else {
                        tv2!!.text = "앞면"
                    }
                }
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {
            Log.e(tag, "onAccuracyChanged")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        tv1 = findViewById<TextView>(R.id.tv1)
        tv2 = findViewById<TextView>(R.id.tv2)

        initSensor()
    }

    override fun onDestroy() {
        super.onDestroy()

        DestorySeonsor()
    }

    private fun initSensor() {

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
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

    private fun DestorySeonsor() {
        if (sensorManager != null) {
            sensorManager!!.unregisterListener(sensorEventListener)
        }
    }
}
