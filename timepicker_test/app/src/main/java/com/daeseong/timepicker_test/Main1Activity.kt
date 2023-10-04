package com.daeseong.timepicker_test

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity

class Main1Activity : AppCompatActivity() {

    private val tag: String = Main1Activity::class.java.simpleName

    private lateinit var timepicker1: TimePicker
    private var nhour: Int = 0
    private var nMinute: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        timepicker1 = findViewById(R.id.timepicker1)

        timepicker1.setOnTimeChangedListener { _, hourOfDay, minute ->
            nhour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timepicker1.hour
            } else {
                timepicker1.currentHour
            }

            nMinute = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timepicker1.minute
            } else {
                timepicker1.currentMinute
            }

            Log.e(tag, "nhour:$nhour nMinute:$nMinute")
            Log.e(tag, "hourOfDay:$hourOfDay minute:$minute")
        }
    }
}
