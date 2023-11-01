package com.daeseong.mpandroidchart_test

import android.graphics.Color
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.charts.CandleStickChart
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry

class Chart3Activity : AppCompatActivity() {

    private lateinit var candleStickChart: CandleStickChart
    private lateinit var arrayList1: ArrayList<CandleEntry>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart3)

        candleStickChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {

        arrayList1 = ArrayList()

        arrayList1.add(CandleEntry(0f, 82200f, 83900f, 82000f, 83500f))
        arrayList1.add(CandleEntry(1f, 83500f, 84600f, 82900f, 84200f))
        arrayList1.add(CandleEntry(2f, 83900f, 85400f, 83800f, 85100f))
        arrayList1.add(CandleEntry(3f, 84800f, 86500f, 84600f, 86100f))
        arrayList1.add(CandleEntry(4f, 86000f, 87200f, 85700f, 87000f))
        arrayList1.add(CandleEntry(5f, 87500f, 88800f, 87000f, 88200f))
        arrayList1.add(CandleEntry(6f, 88000f, 89500f, 87800f, 89200f))
        arrayList1.add(CandleEntry(7f, 89000f, 90400f, 88600f, 89800f))
        arrayList1.add(CandleEntry(8f, 89700f, 91200f, 89500f, 91000f))
        arrayList1.add(CandleEntry(9f, 91000f, 92500f, 90800f, 92200f))

        val dataSet = CandleDataSet(arrayList1, "arrayList1")
        dataSet.setDrawIcons(false)
        dataSet.shadowColor = Color.DKGRAY
        dataSet.decreasingColor = Color.RED // 하락 캔들 색상
        dataSet.increasingColor = Color.GREEN // 상승 캔들 색상
        dataSet.neutralColor = Color.BLUE // 중립 캔들 색상
        dataSet.decreasingPaintStyle = Paint.Style.FILL // 하락 캔들 스타일
        dataSet.increasingPaintStyle = Paint.Style.FILL // 상승 캔들 스타일

        val candleData = CandleData(dataSet)
        candleStickChart.data = candleData

        // 차트 설정
        candleStickChart.description.isEnabled = false
        candleStickChart.xAxis.setDrawGridLines(false)
        candleStickChart.axisLeft.setDrawGridLines(false)
        candleStickChart.axisRight.setDrawGridLines(false)
        candleStickChart.axisRight.isEnabled = false
        candleStickChart.xAxis.position = XAxis.XAxisPosition.BOTTOM
        candleStickChart.xAxis.textSize = 10f

        // 차트 업데이트
        candleStickChart.description.text = "주식차트"
        candleStickChart.invalidate()
    }
}