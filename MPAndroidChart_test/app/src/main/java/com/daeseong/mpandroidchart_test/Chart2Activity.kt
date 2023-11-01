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
    private lateinit var arrayList1: ArrayList<BubbleEntry>
    private lateinit var arrayList2: ArrayList<BubbleEntry>
    private lateinit var arrayList3: ArrayList<BubbleEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart2)

        bubbleChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1 = ArrayList()
        arrayList2 = ArrayList()
        arrayList3 = ArrayList()

        arrayList1.add(BubbleEntry(4f, 10f, 5f)) // X, Y, Size
        arrayList1.add(BubbleEntry(5f, 12f, 20f))
        arrayList1.add(BubbleEntry(6f, 14f, 15f))

        arrayList2.add(BubbleEntry(4f, 18f, 25f))
        arrayList2.add(BubbleEntry(5f, 20f, 30f))
        arrayList2.add(BubbleEntry(6f, 22f, 35f))

        arrayList3.add(BubbleEntry(4f, 26f, 40f))
        arrayList3.add(BubbleEntry(5f, 28f, 45f))
        arrayList3.add(BubbleEntry(6f, 30f, 50f))

        // 데이터셋 생성
        val dataSet1 = BubbleDataSet(arrayList1, "arrayList1")
        dataSet1.color = Color.BLUE

        val dataSet2 = BubbleDataSet(arrayList2, "arrayList2")
        dataSet2.color = Color.RED

        val dataSet3 = BubbleDataSet(arrayList3, "arrayList3")
        dataSet3.color = Color.GREEN

        val bubbleData = BubbleData(dataSet1, dataSet2, dataSet3)
        bubbleChart.data = bubbleData

        // 차트 업데이트
        bubbleChart.description.isEnabled = false // 설명 비활성화
        bubbleChart.invalidate()
    }
}