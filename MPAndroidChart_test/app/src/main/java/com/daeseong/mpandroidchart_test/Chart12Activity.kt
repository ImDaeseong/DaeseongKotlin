package com.daeseong.mpandroidchart_test

import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.mikephil.charting.charts.CombinedChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.github.mikephil.charting.data.CandleData
import com.github.mikephil.charting.data.CandleDataSet
import com.github.mikephil.charting.data.CandleEntry
import com.github.mikephil.charting.data.CombinedData
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter

class Chart12Activity : AppCompatActivity() {

    private lateinit var combinedChart: CombinedChart
    private val nCount = 5

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart12)

        combinedChart = findViewById(R.id.chart)

        init()
    }

    private fun init() {
        // 차트 설명 비활성화
        combinedChart.description.isEnabled = false

        // 차트 배경색 설정
        combinedChart.setBackgroundColor(Color.WHITE)

        // 그리드 배경 비활성화
        combinedChart.setDrawGridBackground(false)

        // 바 그림자 비활성화
        combinedChart.setDrawBarShadow(false)

        // 전체 바 강조 비활성화
        combinedChart.isHighlightFullBarEnabled = false

        // 차트의 그리기 순서 설정
        combinedChart.drawOrder = arrayOf(CombinedChart.DrawOrder.BAR, CombinedChart.DrawOrder.CANDLE)

        // 범례 설정
        val legend = combinedChart.legend
        legend.isWordWrapEnabled = true
        legend.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
        legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
        legend.orientation = Legend.LegendOrientation.HORIZONTAL

        // 오른쪽 Y축 설정
        val rightAxis = combinedChart.axisRight
        rightAxis.setDrawGridLines(false)
        rightAxis.textColor = Color.BLACK

        // 왼쪽 Y축 설정
        val leftAxis = combinedChart.axisLeft
        leftAxis.setDrawGridLines(false)
        leftAxis.textColor = Color.BLACK

        // X축 설정
        val xAxis = combinedChart.xAxis
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.setDrawGridLines(false)
        xAxis.textColor = Color.BLACK
        val labels = getXAxisLabelData()
        xAxis.valueFormatter = IndexAxisValueFormatter(labels)

        // X축의 범위 설정
        xAxis.axisMinimum = 0f // 최소값 설정
        xAxis.axisMaximum = (labels.size - 1).toFloat() // 최대값 설정

        // 데이터 생성 및 설정
        val data = CombinedData()
        data.setData(generateCandleData())
        data.setData(generateBarData())
        xAxis.axisMaximum = data.xMax + 0.25f

        // 차트에 데이터 설정 및 갱신
        combinedChart.data = data
        combinedChart.invalidate()
    }

    // x 축 라벨 표시
    private fun getXAxisLabelData(): ArrayList<String> {
        val xAxis = ArrayList<String>()
        for (i in 0 until nCount) {
            xAxis.add("라벨 ${i + 1}")
        }
        return xAxis
    }

    // 캔들 차트 데이터 생성
    private fun generateCandleData(): CandleData {
        val entries = ArrayList<CandleEntry>()
        entries.add(CandleEntry(0f, 2200f, 3900f, 2000f, 3500f))
        entries.add(CandleEntry(1f, 3500f, 4600f, 2900f, 4200f))
        entries.add(CandleEntry(2f, 3900f, 5400f, 3800f, 5100f))
        entries.add(CandleEntry(3f, 4800f, 6500f, 4600f, 6100f))
        entries.add(CandleEntry(4f, 6000f, 7200f, 5700f, 7000f))

        val set = CandleDataSet(entries, "캔들차트")
        set.setDrawIcons(false)
        set.axisDependency = YAxis.AxisDependency.LEFT
        set.color = Color.rgb(0, 255, 0)
        set.shadowColor = Color.DKGRAY
        set.shadowWidth = 0.7f
        set.decreasingColor = Color.RED
        set.decreasingPaintStyle = Paint.Style.FILL
        set.increasingColor = Color.GREEN
        set.increasingPaintStyle = Paint.Style.FILL
        set.neutralColor = Color.BLUE
        set.setDrawValues(false)

        return CandleData(set)
    }

    // 막대 차트 데이터 생성
    private fun generateBarData(): BarData {
        val entries = ArrayList<BarEntry>()
        entries.add(BarEntry(1f, 41f))
        entries.add(BarEntry(2f, 82f))
        entries.add(BarEntry(3f, 63f))
        entries.add(BarEntry(4f, 44f))
        entries.add(BarEntry(5f, 54f))

        val set = BarDataSet(entries, "막대차트")
        set.color = Color.BLUE
        set.valueTextColor = Color.RED
        set.valueTextSize = 10f
        set.axisDependency = YAxis.AxisDependency.RIGHT

        return BarData(set)
    }
}
