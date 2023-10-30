package com.daeseong.mpandroidchart_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BubbleChart
import com.github.mikephil.charting.data.BubbleData
import com.github.mikephil.charting.data.BubbleDataSet
import com.github.mikephil.charting.data.BubbleEntry

class Chart2Activity : AppCompatActivity() {

    private lateinit var bubbleChart: BubbleChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart2)

        bubbleChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {
        val bubbleList = mutableListOf<BubbleEntry>()
        bubbleList.add(BubbleEntry(0f, 1f, 10f))
        bubbleList.add(BubbleEntry(1f, 3f, 20f))
        bubbleList.add(BubbleEntry(2f, 2f, 15f))
        bubbleList.add(BubbleEntry(3f, 4f, 30f))

        val dataSet = BubbleDataSet(bubbleList, "BubbleChart")
        dataSet.color = Color.GRAY
        dataSet.valueTextSize = 10f
        dataSet.valueTextColor = Color.WHITE

        val data = BubbleData(dataSet)
        bubbleChart.data = data

        //차트 업데이트
        bubbleChart.invalidate()
    }
}