package com.daeseong.progresscircular_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private lateinit var progressCircular: ProgressBar
    private lateinit var tv1: TextView

    private var dTotalHour: Double = 0.0
    private var dTotalMinute: Double = 0.0
    private var dMyHour: Double = 0.0
    private var dMyMinute: Double = 0.0
    private var dResult: Double = 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()
    }

    private fun init() {

        tv1 = findViewById(R.id.tv1)

        // 프로그래스 초기화
        initProgressCircular()

        // 프로그래스 표시
        //calcuPercent1()
        calcuPercent2()
    }

    private fun initProgressCircular() {
        progressCircular = findViewById(R.id.progress_circular)
        progressCircular.progress = 0 // Main Progress
        progressCircular.secondaryProgress = 100 // Secondary Progress
        progressCircular.max = 100 // Maximum Progress
        progressCircular.progressDrawable = ResourcesCompat.getDrawable(resources, R.drawable.circular, null)
     }

    //프로그래스 표시
    private fun calcuPercent1() {

        // 총 5시간 30분
        dTotalHour = (5 * 60).toDouble() // 5시간 * 60 분
        dTotalMinute = 30.0 // 30분

        // 현재 4시간 50분 소요
        dMyHour = (4 * 60).toDouble() // 4시간 * 60 분
        dMyMinute = 50.0 // 50분

        dResult = (dMyHour + dMyMinute) / (dTotalHour + dTotalMinute) * 100

        val nValue = dResult.toInt()
        tv1.text = "$nValue%"
        progressCircular.progress = nValue
    }

    private fun calcuPercent2() {

        //전체 시간중 현재 소요 시간에 대한 평균
        dTotalHour = (5 * 60).toDouble() // 5시간 * 60 분
        dTotalMinute = 30.0 // 30분
        dMyHour = (1 * 60).toDouble() // 1시간 * 60 분
        dMyMinute = 30.0 // 30분
        dResult = ((dMyHour + dMyMinute) / (dTotalHour + dTotalMinute)) * 100

        val nValue = dResult.toInt()
        tv1.text = "$nValue%"
        progressCircular.progress = nValue
    }
}
