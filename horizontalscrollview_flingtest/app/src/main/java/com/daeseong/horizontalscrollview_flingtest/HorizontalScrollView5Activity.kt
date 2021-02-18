package com.daeseong.horizontalscrollview_flingtest


import android.content.Context
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class HorizontalScrollView5Activity : AppCompatActivity() {

    private val tag = HorizontalScrollView5Activity::class.java.simpleName

    private var btnrefresh: Button? = null

    private var hsc: HorizontalScrollView? = null
    private var llayout: LinearLayout? = null

    private var nWidth:Int = 0
    private var nHeight:Int = 0
    private var nImgWidth:Int = 0
    private var nImgHeight:Int = 0

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
        nImgWidth = nWidth - dip2px(this, 29f)
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()

        hsc = findViewById<HorizontalScrollView>(R.id.hsc)
        llayout = findViewById<LinearLayout>(R.id.llayout)

        //데이터 로드
        addRoundedImageView()

        val viewTreeObserver = llayout!!.viewTreeObserver
        viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {

            override fun onGlobalLayout() {

                llayout!!.viewTreeObserver.removeGlobalOnLayoutListener(this)

                //로드 완료시 HorizontalScrollView 제일 마지막 이미지 선택하기
                selectPos(2)
            }
        })

        btnrefresh = findViewById(R.id.btnrefresh)
        btnrefresh!!.setOnClickListener {

            refreshInit()
        }
    }

    private fun refreshInit() {
        llayout!!.removeAllViews()
        addRoundedImageView()
    }

    private fun addRoundedImageView() {

        for (i in 0..2) {

            val roundedImageView = RoundedImageView(this)
            roundedImageView.tag = i

            if (i == 0) {

                roundedImageView.setImageResource(R.drawable.a)
                roundedImageView.adjustViewBounds = true
                roundedImageView.scaleType = ImageView.ScaleType.FIT_XY
                roundedImageView.clipToOutline = true
            } else if (i == 1) {

                roundedImageView.setImageResource(R.drawable.b)
                roundedImageView.adjustViewBounds = true
                roundedImageView.scaleType = ImageView.ScaleType.FIT_XY
                roundedImageView.clipToOutline = true
            } else if (i == 2) {

                roundedImageView.setImageResource(R.drawable.a)
                roundedImageView.adjustViewBounds = true
                roundedImageView.scaleType = ImageView.ScaleType.FIT_XY
                roundedImageView.clipToOutline = true
            }

            roundedImageView.setOnClickListener {

                Log.e(tag, "roundedImageView Click")
            }

            val params = LinearLayout.LayoutParams(nImgWidth, nImgHeight)
            roundedImageView.layoutParams = params

            //마진 설정
            if (i == 0) {

                params.setMargins(0, 0, 0, 0)
            } else {

                params.setMargins(10, 0, 0, 0)
            }
            llayout!!.addView(roundedImageView)
        }
    }

    private fun selectPos(nSelectIndex: Int) {
        val nSelectWidth = llayout!!.getChildAt(nSelectIndex).width
        val nSelectLeft = llayout!!.getChildAt(nSelectIndex).left
        val nIndex = nWidth / nSelectWidth
        val nPosScroll = nSelectLeft - nIndex * nSelectWidth / 2 + nSelectWidth / 2
        hsc!!.smoothScrollTo(nPosScroll, 0)
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun px2dip(context: Context, pxValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

}
