package com.daeseong.mpandroidchart_test

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.ScatterChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.ScatterData
import com.github.mikephil.charting.data.ScatterDataSet

class Chart9Activity : AppCompatActivity() {

    private lateinit var scatterChart: ScatterChart

    private lateinit var arrayList1: ArrayList<Entry>
    private lateinit var arrayList2: ArrayList<Entry>
    private lateinit var arrayList3: ArrayList<Entry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart9)

        scatterChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1 = ArrayList()
        arrayList2 = ArrayList()
        arrayList3 = ArrayList()

        arrayList1.add(Entry(1f, 11f))
        arrayList1.add(Entry(2f, 12f))
        arrayList1.add(Entry(3f, 13f))
        arrayList1.add(Entry(4f, 14f))
        arrayList1.add(Entry(5f, 15f))

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

        val dataSet1 = ScatterDataSet(arrayList1, "arrayList1")
        dataSet1.color = Color.RED
        dataSet1.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val dataSet2 = ScatterDataSet(arrayList2, "arrayList2")
        dataSet2.color = Color.BLUE
        dataSet2.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val dataSet3 = ScatterDataSet(arrayList3, "arrayList3")
        dataSet3.color = Color.GREEN
        dataSet3.setScatterShape(ScatterChart.ScatterShape.CIRCLE)

        val scatterData = ScatterData(dataSet1, dataSet2, dataSet3)
        scatterChart.data = scatterData

        //차트 업데이트
        scatterChart.invalidate()
    }
}