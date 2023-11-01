package com.daeseong.mpandroidchart_test

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class Chart1Activity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private val arrayList1 = ArrayList<BarEntry>()
    private val barColors = ArrayList<Int>()
    private val xlabels = ArrayList<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart1)

        barChart = findViewById(R.id.chart)
        barChart.axisRight.isEnabled = false // 오른쪽 Y축 숨김

        init()
    }

    private fun init() {

        arrayList1.add(BarEntry(0f, 41f))
        arrayList1.add(BarEntry(1f, 82f))
        arrayList1.add(BarEntry(2f, 63f))
        arrayList1.add(BarEntry(3f, 44f))

        //막대 색상
        barColors.add(Color.BLUE)
        barColors.add(Color.RED)
        barColors.add(Color.GREEN)
        barColors.add(Color.YELLOW)

        val barDataSet = BarDataSet(arrayList1, "arrayList1")
        barDataSet.colors = barColors //막대 개별 색상 설정
        //barDataSet.color = Color.BLUE // 막대의 색상 설정 전부 동일하게
        barDataSet.valueTextColor = Color.BLACK // 막대에 표시되는 값의 텍스트 색상 설정

        val barData = BarData(barDataSet)
        barChart.data = barData

        // 왼쪽 Y축 설정
        val yAxis = barChart.axisLeft
        yAxis.axisMaximum = 100f
        yAxis.axisLineWidth = 2f
        yAxis.axisLineColor = Color.BLACK
        yAxis.labelCount = 10

        // X축 설정
        for (i in arrayList1.indices) {
            xlabels.add("label" + (i + 1))
        }

        barChart.xAxis.valueFormatter = IndexAxisValueFormatter(xlabels)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.xAxis.granularity = 1f
        barChart.xAxis.isGranularityEnabled = true

        // 차트 업데이트
        barChart.description.isEnabled = false // 설명 텍스트 숨김
        barChart.invalidate()
    }
}
