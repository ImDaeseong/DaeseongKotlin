package com.daeseong.mpandroidchart_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.HorizontalBarChart

class Chart5Activity : AppCompatActivity() {

    private lateinit var horizontalBarChart: HorizontalBarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart5)

        horizontalBarChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

    }
}