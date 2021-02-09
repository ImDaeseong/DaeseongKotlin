package com.daeseong.screenbrightness_test

import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var layoutParams: WindowManager.LayoutParams? = null
    private var seekBar1: SeekBar? = null
    private var tv1: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        seekBar1 = findViewById<SeekBar>(R.id.seekBar1)
        tv1 = findViewById<TextView>(R.id.tv1)

        layoutParams = window.attributes

        layoutParams!!.screenBrightness = 1f
        seekBar1!!.progress = 100
        //layoutParams!!.screenBrightness = 0 / 100.0f
        //seekBar1!!.progress = 0

        setPercentText();

        seekBar1!!.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {

            override fun onProgressChanged(seekBar: SeekBar, i: Int, b: Boolean) {

                layoutParams!!.screenBrightness = i / 100.0f
                window.attributes = layoutParams
                setPercentText()
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {

            }
            override fun onStopTrackingTouch(seekBar: SeekBar) {

                Log.e(tag, "onStopTrackingTouch : $seekBar!!.progress")
            }
        })
    }

    private fun setPercentText() {

        val sPercent : String = "밝기:" + layoutParams!!.screenBrightness.toString() + "%"
        tv1!!.text = sPercent
    }

    override fun onResume() {
        super.onResume()

    }

    override fun onPause() {
        super.onPause()

    }
}
