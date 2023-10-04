package com.daeseong.switch_test

import android.os.Bundle
import android.util.Log
import android.widget.Switch
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var switch1: Switch
    private lateinit var switch2: Switch

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        switch1 = findViewById(R.id.switch1)
        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.e(tag, "isChecked")
            } else {
                Log.e(tag, "isUnChecked")
            }
        }

        switch2 = findViewById(R.id.switch2)
        switch2.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                Log.e(tag, "isChecked")
            } else {
                Log.e(tag, "isUnChecked")
            }
        }
    }
}
