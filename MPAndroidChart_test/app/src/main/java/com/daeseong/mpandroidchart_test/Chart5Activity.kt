package com.daeseong.mpandroidchart_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.utils.ColorTemplate

class Chart5Activity : AppCompatActivity() {

    private lateinit var horizontalBarChart: HorizontalBarChart
    private var arrayList1: ArrayList<BarEntry> = ArrayList()
    private var xlabels: ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart5)

        horizontalBarChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1.add(BarEntry(0f, 24f))
        arrayList1.add(BarEntry(1f, 20f))
        arrayList1.add(BarEntry(2f, 18f))
        arrayList1.add(BarEntry(3f, 11f))
        arrayList1.add(BarEntry(4f, 10f))
        arrayList1.add(BarEntry(5f, 8f))

        val barDataSet = BarDataSet(arrayList1, "arrayList1")
        barDataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()
        barDataSet.valueTextSize = 14f

        val barData = BarData(barDataSet)
        horizontalBarChart.data = barData
        horizontalBarChart.animateY(2000)
        horizontalBarChart.setFitBars(true)

        // 범례 설정
        val legend = horizontalBarChart.legend
        legend.isEnabled = true

        // 그래프 설명
        val description = Description()
        description.text = "HorizontalBarChart 설명"
        horizontalBarChart.description = description

        horizontalBarChart.xAxis.labelCount = 6
        horizontalBarChart.extraRightOffset = 5f

        // X축 설정
        for (i in 0 until arrayList1.size) {
            xlabels.add("label" + (i + 1))
        }

        val xAxis = horizontalBarChart.xAxis
        xAxis.setDrawGridLines(true)
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.isGranularityEnabled = true
        xAxis.granularity = 1f
        xAxis.setDrawLabels(true)
        xAxis.xOffset = 10f
        xAxis.setDrawAxisLine(true)

        // X축 레이블 설정
        horizontalBarChart.xAxis.valueFormatter = IndexAxisValueFormatter(xlabels)
        horizontalBarChart.xAxis.granularity = 0.2f
        horizontalBarChart.xAxis.isGranularityEnabled = true
        horizontalBarChart.setVisibleXRangeMaximum(6f)

        // 차트 업데이트
        horizontalBarChart.invalidate()
    }
}