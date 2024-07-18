package com.daeseong.mpandroidchart_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.Description
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class Chart6Activity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private val arrayList1 = ArrayList<Entry>()
    private val arrayList2 = ArrayList<Entry>()
    private val arrayList3 = ArrayList<Entry>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart6)

        lineChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1.add(Entry(1f, 10f))
        arrayList1.add(Entry(2f, 20f))
        arrayList1.add(Entry(3f, 30f))
        arrayList1.add(Entry(4f, 24f))
        arrayList1.add(Entry(5f, 35f))

        arrayList2.add(Entry(1f, 21f))
        arrayList2.add(Entry(2f, 22f))
        arrayList2.add(Entry(3f, 23f))
        arrayList2.add(Entry(4f, 24f))
        arrayList2.add(Entry(5f, 25f))

        arrayList3.add(Entry(1f, 31f))
        arrayList3.add(Entry(2f, 32f))
        arrayList3.add(Entry(3f, 33f))
        arrayList3.add(Entry(4f, 34f))
        arrayList3.add(Entry(5f, 35f))

        val dataSet1 = LineDataSet(arrayList1, "arrayList1").apply {
            color = Color.RED
            valueTextColor = Color.BLACK
        }

        val dataSet2 = LineDataSet(arrayList2, "arrayList2").apply {
            color = Color.BLUE
            valueTextColor = Color.BLACK
        }

        val dataSet3 = LineDataSet(arrayList3, "arrayList3").apply {
            color = Color.GREEN
            valueTextColor = Color.BLACK
        }

        val scatterData = LineData(dataSet1, dataSet2, dataSet3)
        lineChart.data = scatterData

        val description = Description().apply {
            text = "lineChart 그래프 설명"
        }
        lineChart.description = description

        lineChart.invalidate()
    }
}