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

    private val imgList: MutableList<RoundedImageView> = mutableListOf()
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

        // 제스처 이벤트를 위한 GestureDetector 초기화
        gestureDetector = GestureDetector(this, MyGestureDetector())

        hsc = findViewById(R.id.hsc)
        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)

        btnRefresh = findViewById(R.id.btnrefresh)
        btnRefresh.setOnClickListener {
            refreshInit()
        }

        // 해상도 사이즈 및 레이아웃 초기화
        updateDimensionsAndImages()

        // 이미지 리스트 초기화
        imgList.addAll(listOf(image1, image2, image3))

        hsc.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun updateDimensionsAndImages() {

        // 이미지 크기 계산
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(39F)
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()

        layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)

        // 이미지 설정
        setupImage(image1, 0, R.drawable.a)
        setupImage(image2, 10, R.drawable.b)
        setupImage(image3, 10, R.drawable.a)
    }

    private fun setupImage(imageView: RoundedImageView, margin: Int, resourceId: Int) {

        layoutParams.setMargins(margin, 0, 0, 0)
        imageView.apply {
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_XY
            layoutParams = this@HorizontalScrollView4Activity.layoutParams
            clipToOutline = true
            setImageResource(resourceId)
        }
    }

    private fun refreshInit() {

        // 이미지 개수 초기화
        imgList.clear()

        // 해상도 사이즈 및 레이아웃 초기화
        updateDimensionsAndImages()

        // 이미지 리스트 초기화
        imgList.addAll(listOf(image1, image2, image3))
    }

    private fun getVisibleViews(direction: String): Int {
        val hitRect = Rect()
        var position = 0
        var rightCounter = 0

        imgList.forEachIndexed { i, img ->
            if (img.getLocalVisibleRect(hitRect)) {
                if (direction == "left") {
                    position = i
                    return@forEachIndexed // 더 이상 필요 없으므로 루프 종료
                } else if (direction == "right") {
                    rightCounter++
                    position = i
                    if (rightCounter == 2) return@forEachIndexed // 두 번째 이미지까지 찾았으므로 루프 종료
                }
            }
        }
        return position
    }

    private fun dip2px(dpValue: Float): Int {
        val scale = resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    inner class MyGestureDetector : GestureDetector.SimpleOnGestureListener() {
        override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            val direction = if (e1?.x ?: 0f < e2.x) "left" else "right"
            selectIndex = getVisibleViews(direction)

            if (selectIndex in imgList.indices) {
                hsc.smoothScrollTo(imgList[selectIndex].left, 0)
            }

            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(tag, "현재 보이는 이미지 인덱스: $selectIndex")
            return super.onSingleTapUp(e)
        }
    }
}
