package com.daeseong.mpandroidchart_test


import android.R.attr
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet


class Main1Activity : AppCompatActivity() {

    private val tag = Main1Activity::class.java.simpleName

    private var button1: Button? = null

    private var lineChart: LineChart? = null
    private var xAxis: XAxis? = null
    private var yAxis: YAxis? = null

    private var arrayList: ArrayList<Entry>? = null

    private var lineData: LineData? = null
    private var lineDataSet: LineDataSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener {
            initData()
        }

        arrayList = ArrayList()

        //차트 초기화
        initChart()

        //데이타 입력
        initData()
    }

    private fun initChart() {
        lineChart = findViewById<LineChart>(R.id.linechart)
        lineChart!!.isDoubleTapToZoomEnabled = false //더블탭으로 확대 여부
        lineChart!!.isClickable = false //클릭가능 여부
        lineChart!!.isDragEnabled = true //드래그 여부
        lineChart!!.setScaleEnabled(false) //크기 조절 여부
        lineChart!!.setPinchZoom(false) //핀치 가능 여부

        //내부 텍스트 숨기기
        lineChart!!.description = null

        /*
        //내부 텍스트 보이기
        val description = Description()
        description.text = "내부 텍스트"
        lineChart!!.description = description
        */


        //x축
        xAxis = lineChart!!.getXAxis()
        xAxis!!.position = XAxis.XAxisPosition.BOTTOM
        xAxis!!.textSize = 10f
        xAxis!!.textColor = Color.BLUE
        xAxis!!.setDrawAxisLine(true)
        xAxis!!.setDrawGridLines(false)


        //y축
        yAxis = lineChart!!.axisLeft//yAxis = lineChart!!.axisRight
        yAxis!!.setDrawLabels(false)
        yAxis!!.setDrawAxisLine(false)
        yAxis!!.setDrawGridLines(false)
        yAxis!!.setDrawZeroLine(true)
        lineChart!!.axisRight.isEnabled = false//lineChart!!.axisLeft.isEnabled = false


        //legend 영역 미설정시 default

        /*
        //legend 영역 숨기기
        val legend = lineChart!!.legend
        legend.isEnabled = false
        */

        /*
        //legend 영역 설정
        val legend = lineChart!!.legend
        legend.isWordWrapEnabled = true
        legend.textSize = 10f
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
        legend.orientation = Legend.LegendOrientation.HORIZONTAL
        legend.setDrawInside(false)
        legend.form = Legend.LegendForm.CIRCLE
        */
    }

    private fun initData() {

        arrayList!!.clear()

        lineChart!!.invalidate()
        lineChart!!.clear()


        arrayList!!.add(Entry(1F, 1F))
        arrayList!!.add(Entry(2F, 2F))
        arrayList!!.add(Entry(3F, 3F))
        arrayList!!.add(Entry(4F, 4F))
        arrayList!!.add(Entry(5F, 5F))

        lineDataSet = LineDataSet(arrayList, "입력 데이터")
        lineDataSet!!.color = Color.RED
        lineDataSet!!.setDrawFilled(true)
        lineDataSet!!.fillColor = Color.RED
        lineDataSet!!.mode = LineDataSet.Mode.HORIZONTAL_BEZIER
        lineDataSet!!.cubicIntensity = 0.1f
        lineData = LineData(lineDataSet)
        lineChart!!.data = lineData
    }

}
