package com.daeseong.sensormanager_test

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Main2Activity : AppCompatActivity() , SensorEventListener {

    private val tag: String = Main2Activity::class.java.simpleName

    private var tv1: TextView? = null
    private var tv2: TextView? = null

    private var sensorManager: SensorManager? = null
    private var Accelsensor: Sensor? = null
    private var Lightsensor: Sensor? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        tv1 = findViewById<TextView>(R.id.tv1)
        tv2 = findViewById<TextView>(R.id.tv2)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager

        if(sensorManager != null){
            Accelsensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            Lightsensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_LIGHT);
            sensorManager!!.registerListener(this, Accelsensor, SensorManager.SENSOR_DELAY_NORMAL);
            sensorManager!!.registerListener(this, Lightsensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    override fun onDestroy() {
        super.onDestroy()

        if(sensorManager != null) {
            sensorManager!!.unregisterListener(this);
        }
    }

    override fun onSensorChanged(event: SensorEvent?) {

        if (event!!.sensor.type === Sensor.TYPE_ACCELEROMETER) {

            val dX = event!!.values[0].toDouble()
            val dY = event!!.values[1].toDouble()
            val dZ = event!!.values[2].toDouble()
            val angleX = Math.atan2(dX, dZ) * 180 / Math.PI
            val angleY = Math.atan2(dY, dZ) * 180 / Math.PI
            val sValue = String.format("X:%.4f Y:%.4f", angleX, angleY)
            //Log.e(tag, sValue)

            //final String sValue = String.format("X:%s Y:%s Z:%s", Float.toString(event.values[0]), Float.toString(event.values[1]), Float.toString(event.values[2]));
            runOnUiThread {
                tv1!!.text = sValue
            }

        } else if (event!!.sensor.type === Sensor.TYPE_LIGHT) {

            //Log.e(tag, java.lang.String.valueOf(event!!.values[0]))
            val sValue = java.lang.String.format("TYPE_LIGHT:%.4f", event!!.values[0])
            runOnUiThread {
                tv2!!.text = sValue
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {
        //Log.e(tag, "onAccuracyChanged")
    }
}
