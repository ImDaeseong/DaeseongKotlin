package com.daeseong.sensormanager_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Main4Activity : AppCompatActivity() {

    private val tag: String = Main4Activity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        SensorInfo.getInstance().initSensor(this)
    }

    override fun onDestroy() {
        super.onDestroy()

        SensorInfo.getInstance().DestorySeonsor()
    }
}
