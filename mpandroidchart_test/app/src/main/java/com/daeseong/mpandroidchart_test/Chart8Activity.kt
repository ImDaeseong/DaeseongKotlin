package com.daeseong.mpandroidchart_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.RadarChart
import com.github.mikephil.charting.data.RadarData
import com.github.mikephil.charting.data.RadarDataSet
import com.github.mikephil.charting.data.RadarEntry

class Chart8Activity : AppCompatActivity() {

    private lateinit var radarChart: RadarChart
    private lateinit var arrayList1: ArrayList<RadarEntry>
    private lateinit var arrayList2: ArrayList<RadarEntry>
    private lateinit var arrayList3: ArrayList<RadarEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart8)

        radarChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1 = ArrayList()
        arrayList2 = ArrayList()
        arrayList3 = ArrayList()

        arrayList1.add(RadarEntry(50f))
        arrayList1.add(RadarEntry(90f))
        arrayList1.add(RadarEntry(30f))
        arrayList1.add(RadarEntry(60f))
        arrayList1.add(RadarEntry(80f))

        arrayList2.add(RadarEntry(70f))
        arrayList2.add(RadarEntry(75f))
        arrayList2.add(RadarEntry(89f))
        arrayList2.add(RadarEntry(79f))
        arrayList2.add(RadarEntry(95f))

        arrayList3.add(RadarEntry(80f))
        arrayList3.add(RadarEntry(77f))
        arrayList3.add(RadarEntry(78f))
        arrayList3.add(RadarEntry(99f))
        arrayList3.add(RadarEntry(87f))

        // 데이터셋 생성
        val dataSet1 = RadarDataSet(arrayList1, "arrayList1")
        dataSet1.color = Color.RED
        dataSet1.fillColor = Color.WHITE
        dataSet1.fillAlpha = 180
        dataSet1.lineWidth = 4f
        dataSet1.isDrawHighlightCircleEnabled = true

        val dataSet2 = RadarDataSet(arrayList2, "arrayList2")
        dataSet2.color = Color.BLUE
        dataSet2.fillColor = Color.WHITE
        dataSet2.fillAlpha = 180
        dataSet2.lineWidth = 4f
        dataSet2.isDrawHighlightCircleEnabled = true

        val dataSet3 = RadarDataSet(arrayList3, "arrayList3")
        dataSet3.color = Color.GREEN
        dataSet3.fillColor = Color.WHITE
        dataSet3.fillAlpha = 180
        dataSet3.lineWidth = 4f
        dataSet3.isDrawHighlightCircleEnabled = true

        val radarData = RadarData(dataSet1, dataSet2, dataSet3)
        radarChart.data = radarData

        // 차트 업데이트
        radarChart.invalidate()
    }
}