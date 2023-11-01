package com.daeseong.mpandroidchart_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.CandleStickChart

class Chart3Activity : AppCompatActivity() {

    private lateinit var candleStickChart: CandleStickChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart3)

        candleStickChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

    }
}