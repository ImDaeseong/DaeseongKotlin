package com.daeseong.mpandroidchart_test

import android.graphics.Color
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener

class Chart11Activity : AppCompatActivity() {

    private lateinit var barChart: BarChart
    private lateinit var arrayList1: ArrayList<BarEntry>
    private lateinit var arrayList2: ArrayList<BarEntry>

    private val groupSpace = 0.06f
    private val barSpace = 0.02f
    private val barWidth = 0.45f

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart11)

        barChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1 = ArrayList()
        arrayList2 = ArrayList()
        for (i in 0 until 5) {
            arrayList1.add(BarEntry(i.toFloat(), (Math.random() * 100).toFloat()))
            arrayList2.add(BarEntry(i.toFloat(), (Math.random() * 100).toFloat()))
        }

        val barDataSet1 = BarDataSet(arrayList1, "arrayList1")
        barDataSet1.color = Color.BLUE // 막대 개별 색상 설정

        val barDataSet2 = BarDataSet(arrayList2, "arrayList2")
        barDataSet2.color = Color.GREEN // 막대 개별 색상 설정

        val barData = BarData(barDataSet1, barDataSet2)

        // 막대 넓이
        barData.barWidth = barWidth

        barChart.data = barData

        // x축 시작 위치:0, 각 그룹 간의 간격, 막대 간의 간격
        barChart.groupBars(0f, groupSpace, groupSpace)

        // 범례 설정
        val legend = barChart.legend
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.VERTICAL

        // 터치 리스너
        barChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight) {
                Toast.makeText(applicationContext, "값: ${e.y}", Toast.LENGTH_SHORT).show()
            }

            override fun onNothingSelected() {}
        })

        // 차트 업데이트
        barChart.description.isEnabled = false // 설명 텍스트 숨김
        barChart.invalidate()
    }
}