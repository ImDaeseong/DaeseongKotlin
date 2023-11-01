package com.daeseong.mpandroidchart_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.utils.ColorTemplate

class Chart7Activity : AppCompatActivity() {

    private lateinit var pieChart: PieChart
    private var arrayList1: ArrayList<PieEntry> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart7)

        pieChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1.add(PieEntry(60f, "데이터1"))
        arrayList1.add(PieEntry(90f, "데이터2"))
        arrayList1.add(PieEntry(75f, "데이터3"))
        arrayList1.add(PieEntry(55f, "데이터4"))

        val dataSet = PieDataSet(arrayList1, "제목")
        dataSet.colors = ColorTemplate.COLORFUL_COLORS.toList()

        val pieData = PieData(dataSet)

        pieChart.data = pieData
        pieChart.description.isEnabled = false
        pieChart.centerText = "데이터 항목"
        pieChart.holeRadius = 20f
        pieChart.transparentCircleRadius = 25f
        pieChart.animateY(1000)
    }
}