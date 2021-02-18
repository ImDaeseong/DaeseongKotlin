package com.daeseong.mpandroidchart_test


import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class Main3Activity : AppCompatActivity() {

    private val tag = Main3Activity::class.java.simpleName

    private var button1: Button? = null

    private var barChart: BarChart? = null
    private var xAxis: XAxis? = null
    private var yAxis: YAxis? = null

    private val entries = ArrayList<BarEntry>()
    private var barData: BarData? = null
    private var barDataSet: BarDataSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {

            initData()
        }

        //차트 초기화
        initChart()


        //데이타 입력
        initData()
    }

    private fun initChart() {

        barChart = findViewById<BarChart>(R.id.barchart)
        barChart!!.isDoubleTapToZoomEnabled = false //더블탭으로 확대 여부
        barChart!!.setClickable(false) //클릭가능 여부
        barChart!!.setDragEnabled(true) //드래그 여부
        barChart!!.setScaleEnabled(false) //크기 조절 여부
        barChart!!.setPinchZoom(false) //핀치 가능 여부

        //x축
        xAxis = barChart!!.getXAxis()
        xAxis!!.position = XAxis.XAxisPosition.BOTTOM
        xAxis!!.textSize = 10f
        xAxis!!.textColor = Color.BLUE
        xAxis!!.setDrawAxisLine(true)
        xAxis!!.setDrawGridLines(false)


        //y축
        yAxis = barChart!!.axisLeft
        yAxis!!.setDrawLabels(true)
        yAxis!!.setDrawAxisLine(true)
        yAxis!!.setDrawGridLines(true)
        yAxis!!.setDrawZeroLine(true)
        barChart!!.axisRight.isEnabled = false


        //내부 텍스트 숨기기
        barChart!!.description = null

        //legend 영역 숨기기
        val legend = barChart!!.legend
        legend.isEnabled = false
    }

    private fun initData() {

        entries.clear()
        barChart!!.invalidate()
        barChart!!.clear()

        entries.add(BarEntry(1F, 1F))
        entries.add(BarEntry(2F, 2F))
        entries.add(BarEntry(3F, 3F))
        entries.add(BarEntry(4F, 4F))
        entries.add(BarEntry(5F, 5F))

        barDataSet = BarDataSet(entries, "입력 데이터")
        barDataSet!!.color = Color.RED
        barDataSet!!.valueTextSize = 14f
        barData = BarData(barDataSet)
        barChart!!.data = barData
    }
}
