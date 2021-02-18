package com.daeseong.mpandroidchart_test


import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter


class Main4Activity : AppCompatActivity() {

    private val tag = Main4Activity::class.java.simpleName

    private var button1: Button? = null

    private var barChart: BarChart? = null
    private var xAxis: XAxis? = null
    private var yAxis: YAxis? = null

    private val entries = ArrayList<BarEntry>()
    private var barData: BarData? = null
    private var barDataSet: BarDataSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

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
        barChart!!.isClickable = false //클릭가능 여부
        barChart!!.isDragEnabled = true //드래그 여부
        barChart!!.setScaleEnabled(false) //크기 조절 여부
        barChart!!.setPinchZoom(false) //핀치 가능 여부

        //x축
        xAxis = barChart!!.xAxis
        xAxis!!.position = XAxis.XAxisPosition.BOTTOM
        xAxis!!.textSize = 10f
        xAxis!!.textColor = Color.BLUE
        xAxis!!.setDrawAxisLine(true)
        xAxis!!.setDrawGridLines(false)
        xAxis!!.axisMinimum = 0f
        //xAxis!!.axisMaximum = 5F

        //y축
        yAxis = barChart!!.axisLeft
        yAxis!!.setDrawLabels(true)
        yAxis!!.setDrawAxisLine(true)
        yAxis!!.setDrawGridLines(true)
        yAxis!!.setDrawZeroLine(true)
        barChart!!.axisRight.isEnabled = false
        yAxis!!.axisMinimum = 0f
        //yAxis!!.axisMaximum = 5F


        //내부 텍스트 숨기기
        barChart!!.description = null

        //legend 영역
        val legend = barChart!!.legend
        legend.isWordWrapEnabled = true
        legend.textSize = 10f
        legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
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

        val xAxisLables = arrayOf("", "12/01", "12/02", "12/03", "12/04", "12/05")
        val formatter = IndexAxisValueFormatter(xAxisLables)
        xAxis!!.granularity = 1f
        xAxis!!.valueFormatter = formatter
        barDataSet = BarDataSet(entries, "입력 데이터")
        barDataSet!!.color = Color.RED
        barDataSet!!.valueTextSize = 14f
        barData = BarData(barDataSet)
        barChart!!.data = barData
        barChart!!.setFitBars(true)
    }
}
