package com.daeseong.timepicker_test

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity

class Main2Activity : AppCompatActivity() {

    private val tag: String = Main2Activity::class.java.simpleName

    private var timepicker1: TimePicker? = null
    private var nhour : Int = 0
    private var nMinute : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        timepicker1 = findViewById<TimePicker>(R.id.timepicker1)
        timepicker1!!.setIs24HourView(false);
        timepicker1!!.setOnTimeChangedListener { view, hourOfDay, minute ->

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                nhour = timepicker1!!.hour
                nMinute = timepicker1!!.minute
            } else {
                nhour = timepicker1!!.currentHour
                nMinute = timepicker1!!.currentMinute
            }

            Log.e(tag, "nhour:$nhour nMinute:$nMinute")
            Log.e(tag, "hourOfDay:$hourOfDay minute:$minute")
        }
    }
}
