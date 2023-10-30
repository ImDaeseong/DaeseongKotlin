package com.daeseong.mpandroidchart_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.ValueFormatter

class Chart1Activity : AppCompatActivity() {

    private lateinit var barChart: BarChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart1)

        barChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        // 데이터 포인트 생성
        val entries = arrayListOf(
            BarEntry(1f, 30f),
            BarEntry(2f, 10f),
            BarEntry(3f, 60f),
            BarEntry(4f, 20f),
            BarEntry(5f, 40f)
        )

        val dataSet = BarDataSet(entries, "barChart")
        dataSet.color = Color.GRAY

        val barData = BarData(dataSet)
        barChart.data = barData

        //하단 레이블
        val labels = arrayOf("Label1", "Label2", "Label3", "Label4", "Label5")
        barChart.xAxis.valueFormatter = MyXAxisValueFormatter(labels)
        barChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        barChart.axisLeft.axisMinimum = 0f
        barChart.axisRight.axisMinimum = 0f

        //차트 내부에 설명 텍스트
        val description = Description()
        description.text = ""
        barChart.description = description

        //차트 업데이트
        barChart.invalidate()
    }
}

class MyXAxisValueFormatter(private val labels: Array<String>) : ValueFormatter() {
    override fun getFormattedValue(value: Float): String {
        val index = value.toInt()
        return if (index >= 0 && index < labels.size) {
            labels[index]
        } else {
            ""
        }
    }
}