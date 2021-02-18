package com.daeseong.mpandroidchart_test


import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry


class Main2Activity : AppCompatActivity() {

    private val tag = Main2Activity::class.java.simpleName

    private var button1: Button? = null

    private var pieChart: PieChart? = null

    private val entries = ArrayList<PieEntry>()
    private var pieData: PieData? = null
    private var pieDataSet: PieDataSet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

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

        pieChart = findViewById<PieChart>(R.id.piechart)
        pieChart!!.isClickable = false //클릭가능 여부

        //내부 텍스트 숨기기
        pieChart!!.description = null

        //legend 영역 숨기기
        val legend = pieChart!!.legend
        legend.isEnabled = false
    }

    private fun initData() {

        entries.clear()

        pieChart!!.invalidate()
        pieChart!!.clear()

        entries.add(PieEntry(50F, 1))
        entries.add(PieEntry(20F, 2))
        entries.add(PieEntry(30F, 3))

        pieDataSet = PieDataSet(entries, "입력 데이터")
        pieDataSet!!.color = Color.RED
        pieData = PieData(pieDataSet)
        pieChart!!.data = pieData

        //pieChart!!.animateXY(5000, 5000)
    }

}
