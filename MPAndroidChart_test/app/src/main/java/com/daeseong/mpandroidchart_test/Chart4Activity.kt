package com.daeseong.mpandroidchart_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.data.*

class Chart4Activity : AppCompatActivity() {

    private lateinit var combinedChart: CombinedChart
    private var arrayList1 = ArrayList<Entry>()
    private var arrayList2 = ArrayList<BarEntry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart4)

        combinedChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1.add(Entry(1f, 10f))
        arrayList1.add(Entry(2f, 20f))
        arrayList1.add(Entry(3f, 30f))
        arrayList1.add(Entry(4f, 24f))
        arrayList1.add(Entry(5f, 35f))

        // 라인 차트 데이터셋 생성
        val lineDataSet = LineDataSet(arrayList1, "arrayList1")
        lineDataSet.color = Color.RED

        arrayList2.add(BarEntry(1f, 41f))
        arrayList2.add(BarEntry(2f, 82f))
        arrayList2.add(BarEntry(3f, 63f))
        arrayList2.add(BarEntry(4f, 44f))
        arrayList2.add(BarEntry(5f, 54f))

        // 바 차트 데이터셋 생성
        val barDataSet = BarDataSet(arrayList2, "arrayList1")
        barDataSet.color = Color.GRAY

        // 차트에 데이터 설정
        val combinedData = CombinedData()
        combinedData.setData(LineData(lineDataSet)) // 라인 차트 데이터
        combinedData.setData(BarData(barDataSet)) // 바 차트 데이터
        combinedChart.data = combinedData

        // 차트 업데이트
        combinedChart.description.isEnabled = false // 설명 비활성화
        combinedChart.invalidate()
    }
}