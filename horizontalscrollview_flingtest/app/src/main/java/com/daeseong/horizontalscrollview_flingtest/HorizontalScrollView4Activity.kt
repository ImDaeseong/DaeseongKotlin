package com.daeseong.horizontalscrollview_flingtest

import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity

class HorizontalScrollView4Activity : AppCompatActivity() {

    private val tag = HorizontalScrollView4Activity::class.java.simpleName

    private lateinit var btnRefresh: Button
    private lateinit var hsc: HorizontalScrollView
    private lateinit var image1: RoundedImageView
    private lateinit var image2: RoundedImageView
    private lateinit var image3: RoundedImageView

    private var imgList: MutableList<RoundedImageView> = mutableListOf()
    private lateinit var gestureDetector: GestureDetector
    private lateinit var layoutParams: LinearLayout.LayoutParams

    private var nWidth: Int = 0
    private var nImgHeight: Int = 0
    private var selectIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll_view4)

        init()
    }

    private fun init() {

        //제스처 이벤트
        gestureDetector = GestureDetector(applicationContext, MyGestureDetector())
        hsc = findViewById(R.id.hsc)
        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)

        btnRefresh = findViewById(R.id.btnrefresh)
        btnRefresh.setOnClickListener {
            refreshInit()
        }

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(39F)
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()
        layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)

        //이미지 설정
        for (i in 0..2) {
            when (i) {
                0 -> {
                    setupImage(image1, 0, R.drawable.a)
                }
                1 -> {
                    setupImage(image2, 10, R.drawable.b)
                }
                2 -> {
                    setupImage(image3, 10, R.drawable.a)
                }
            }
        }

        //이미지 리스트
        imgList.addAll(listOf(image1, image2, image3))

        hsc.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun setupImage(imageView: RoundedImageView, margin: Int, resourceId: Int) {
        layoutParams.setMargins(margin, 0, 0, 0)
        imageView.apply {
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_XY
            this.layoutParams = layoutParams
            clipToOutline = true
            setImageResource(resourceId)
        }
    }

    private fun prevImage() {
        hsc.smoothScrollTo(hsc.scrollX - nWidth, 0)
    }

    private fun nextImage() {
        hsc.smoothScrollTo(hsc.scrollX + nWidth, 0)
    }

    private fun refreshInit() {

        //이미지 개수
        imgList!!.clear()

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(39F)
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()
        layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)

        //이미지 설정
        for (i in 0..2) {
            when (i) {
                0 -> {
                    setupImage(image1, 0, R.drawable.b)
                }
                1 -> {
                    setupImage(image2, 10, R.drawable.a)
                }
                2 -> {
                    setupImage(image3, 10, R.drawable.b)
                }
            }
        }

        //이미지 리스트
        imgList.addAll(listOf(image1, image2, image3))
    }

    private fun getVisibleViews(direction: String): Int {
        val hitRect = Rect()
        var position = 0
        var rightCounter = 0
        for ((i, img) in imgList.withIndex()) {
            if (img.getLocalVisibleRect(hitRect)) {
                if (direction == "left") {
                    position = i
                    break
                } else if (direction == "right") {
                    rightCounter++
                    position = i
                    if (rightCounter == 2) break
                }
            }
        }
        return position
    }

    private fun dip2px(dpValue: Float): Int {
        val scale: Float = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }


    inner class MyGestureDetector : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            selectIndex = if (e1.x < e2.x) {
                getVisibleViews("left")
            } else {
                getVisibleViews("right")
            }

            hsc.smoothScrollTo(imgList[selectIndex].left, 0)
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.e(tag, "selectIndex:$selectIndex")
            return super.onSingleTapUp(e)
        }
    }
}
