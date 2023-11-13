package com.daeseong.screenbrightness_test

import android.os.Bundle
import android.util.Log
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import android.view.WindowManager

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var layoutParams: WindowManager.LayoutParams
    private lateinit var seekBar: SeekBar
    private lateinit var brightnessTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar = findViewById(R.id.seekBar1)
        brightnessTextView = findViewById(R.id.tv1)

        layoutParams = window.attributes

        seekBar.progress = 100
        updateBrightness(1f)

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                val brightness = progress / 100.0f
                updateBrightness(brightness)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                Log.e(tag, "onStopTrackingTouch : ${seekBar?.progress}")
            }
        })
    }

    private fun updateBrightness(brightness: Float) {
        layoutParams.screenBrightness = brightness
        window.attributes = layoutParams
        setPercentText(brightness)
    }

    private fun setPercentText(brightness: Float) {
        val percentText = "밝기: ${brightness * 100}%"
        brightnessTextView.text = percentText
    }
}
