package com.daeseong.mpandroidchart_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.ScatterChart

class Chart9Activity : AppCompatActivity() {

    private lateinit var scatterChart: ScatterChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart9)

        scatterChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

    }
}