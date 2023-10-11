package com.daeseong.horizontalscrollview_flingtest

import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HorizontalScrollView2Activity : AppCompatActivity() {

    private val tag = HorizontalScrollView2Activity::class.java.simpleName

    private lateinit var llayout: LinearLayout

    private var nWidth: Int = 0
    private var nHeight: Int = 0
    private var nImgHeight: Int = 0

    private val nImgCount = 8

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll_view2)

        init()
    }

    private fun init() {

        llayout = findViewById(R.id.llayout)

        //해상도 사이즈
        val displayMetrics = DisplayMetrics().apply {
            windowManager.defaultDisplay.getMetrics(this)
        }
        nWidth = displayMetrics.widthPixels - dip2px(29F)
        nHeight = displayMetrics.heightPixels
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()

        for (i in 0 until nImgCount) {
            setImageView(i)
        }
    }

    private fun setImageView(nIndex: Int) {

        val imageView = ImageView(this)
        val params = LinearLayout.LayoutParams(nImgHeight * 2, nImgHeight)
        imageView.adjustViewBounds = true
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.layoutParams = params

        //마진 설정
        if (nIndex == 0) {
            params.setMargins(0, 0, 0, 0)
        } else {
            params.setMargins(10, 0, 0, 0)
        }

        imageView.setImageResource(if (nIndex % 2 == 1) R.drawable.a else R.drawable.b)

        llayout.addView(imageView)
    }

    private fun dip2px(dpValue: Float): Int {
        val scale: Float = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
