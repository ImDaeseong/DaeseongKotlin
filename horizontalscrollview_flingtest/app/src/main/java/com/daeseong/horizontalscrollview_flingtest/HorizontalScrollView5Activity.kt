package com.daeseong.horizontalscrollview_flingtest

import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HorizontalScrollView5Activity : AppCompatActivity() {

    private val tag = HorizontalScrollView5Activity::class.java.simpleName

    private lateinit var btnRefresh: Button
    private lateinit var hsc: HorizontalScrollView
    private lateinit var llayout: LinearLayout

    private var nWidth: Int = 0
    private var nHeight: Int = 0
    private var nImgWidth: Int = 0
    private var nImgHeight: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll_view5)

        init()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    private fun init() {

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels
        nHeight = displayMetrics.heightPixels
        nImgWidth = nWidth - dip2px(29f)
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()

        hsc = findViewById(R.id.hsc)
        llayout = findViewById(R.id.llayout)

        //데이터 로드
        addRoundedImageView()

        val viewTreeObserver = llayout.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {

            override fun onGlobalLayout() {
                llayout.viewTreeObserver.removeOnGlobalLayoutListener(this)

                //로드 완료시 HorizontalScrollView 제일 마지막 이미지 선택하기
                selectPos(2)
            }
        })

        btnRefresh = findViewById(R.id.btnrefresh)
        btnRefresh.setOnClickListener {
            refreshInit()
        }
    }

    private fun refreshInit() {
        llayout.removeAllViews()
        addRoundedImageView()
    }

    private fun addRoundedImageView() {

        for (i in 0..2) {

            val roundedImageView = RoundedImageView(this)
            roundedImageView.tag = i

            when (i) {
                0, 1, 2 -> {
                    val resourceId = if (i % 2 == 0) R.drawable.a else R.drawable.b
                    setupRoundedImageView(roundedImageView, resourceId)
                }
            }

            roundedImageView.setOnClickListener {
                Log.e(tag, "roundedImageView Click")
            }

            val params = LinearLayout.LayoutParams(nImgWidth, nImgHeight).apply {
                //마진 설정
                setMargins(if (i == 0) 0 else 10, 0, 0, 0)
            }

            roundedImageView.layoutParams = params
            llayout.addView(roundedImageView)
        }
    }

    private fun setupRoundedImageView(roundedImageView: RoundedImageView, resourceId: Int) {
        roundedImageView.apply {
            setImageResource(resourceId)
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_XY
            clipToOutline = true
        }
    }

    private fun selectPos(selectIndex: Int) {
        val selectWidth = llayout.getChildAt(selectIndex).width
        val selectLeft = llayout.getChildAt(selectIndex).left
        val index = nWidth / selectWidth
        val posScroll = selectLeft - index * selectWidth / 2 + selectWidth / 2
        hsc.smoothScrollTo(posScroll, 0)
    }

    private fun dip2px(dpValue: Float): Int {
        val scale: Float = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun px2dip(pxValue: Float): Int {
        val scale: Float = resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }
}
