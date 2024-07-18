package com.daeseong.mpandroidchart_test

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class Chart10Activity : AppCompatActivity() {

    private lateinit var lineChart: LineChart
    private var arrayList1: ArrayList<Entry> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart10)

        lineChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1.clear()
        for (i in 0 until 7) {
            arrayList1.add(Entry(i.toFloat(), (Math.random() * 10).toFloat()))
        }

        // 데이터셋 생성
        val dataSet1 = LineDataSet(arrayList1, "arrayList1")
        dataSet1.color = Color.BLACK
        dataSet1.setCircleColor(Color.RED)
        dataSet1.setDrawFilled(true)
        dataSet1.fillColor = Color.GREEN

        // 부드러운 커브를 사용하려면
        // dataSet1.mode = LineDataSet.Mode.CUBIC_BEZIER

        val scatterData = LineData(dataSet1)
        lineChart.data = scatterData

        // 애니메이션
        lineChart.animateX(1500)

        // 마커 설정
        //val mv = MarkerView(this, R.layout.layout_markview)
        val mv = MarkerViewEx(this, R.layout.layout_markview)
        mv.chartView = lineChart
        lineChart.marker = mv

        // X축 설정
        val xAxis = lineChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.valueFormatter = IndexAxisValueFormatter(arrayOf("월", "화", "수", "목", "금", "토", "일"))

        // Y축 설정
        val leftAxis = lineChart.axisLeft
        leftAxis.axisMinimum = 0f
        leftAxis.axisMaximum = 12f

        // 차트 설명 숨김
        lineChart.description.isEnabled = false

        // 차트 갱신
        lineChart.invalidate()
    }
}