package com.daeseong.timepicker_test

import android.os.Build
import android.os.Bundle
import android.text.format.DateFormat
import android.util.Log
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class Main2Activity : AppCompatActivity() {

    private val tag: String = Main2Activity::class.java.simpleName

    private lateinit var timepicker1: TimePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        timepicker1 = findViewById(R.id.timepicker1)

        timepicker1.setOnTimeChangedListener { _, hourOfDay, minute ->
            val calendar = Calendar.getInstance()
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)

            val nhour = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timepicker1.hour
            } else {
                timepicker1.currentHour
            }

            val nMinute = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                timepicker1.minute
            } else {
                timepicker1.currentMinute
            }

            val formattedTime = if (DateFormat.is24HourFormat(this)) {
                SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
            } else {
                SimpleDateFormat("hh:mm a", Locale.getDefault()).format(calendar.time)
            }

            Log.e(tag, "Formatted Time: $formattedTime")
            Log.e(tag, "nhour:$nhour nMinute:$nMinute")
            Log.e(tag, "hourOfDay:$hourOfDay minute:$minute")
        }
    }
}
