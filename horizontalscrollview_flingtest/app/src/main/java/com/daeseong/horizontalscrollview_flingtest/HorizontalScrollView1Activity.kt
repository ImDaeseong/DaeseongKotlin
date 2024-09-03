package com.daeseong.horizontalscrollview_flingtest

import android.content.Context
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

class HorizontalScrollView1Activity : AppCompatActivity() {

    private val tag = HorizontalScrollView1Activity::class.java.simpleName

    private lateinit var btnRefresh: Button
    private lateinit var hsc: HorizontalScrollView
    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView

    private val imgList = ArrayList<ImageView>()
    private lateinit var gestureDetector: GestureDetector
    private var layoutParams: LinearLayout.LayoutParams? = null

    private var nWidth = 0
    private var nImgHeight = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll_view1)

        init()
    }

    override fun onDestroy() {
        super.onDestroy()

        hsc.removeAllViews()
        imgList.clear()
    }

    private fun init() {

        // 제스처 감지를 위한 GestureDetector 초기화
        gestureDetector = GestureDetector(this, MyGestureDetector())

        hsc = findViewById(R.id.hsc)
        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)
        btnRefresh = findViewById(R.id.btnrefresh)

        btnRefresh.setOnClickListener {
            refreshInit()
        }

        initImageList()

        hsc.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun initImageList() {

        // 이미지 속성 초기화 및 이미지 목록에 추가
        updateImageAttributes()
        imgList.apply {
            clear()
            addAll(listOf(image1, image2, image3))
        }
    }

    private fun updateImageAttributes() {

        // 이미지 크기 계산
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(this, 29F)
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()
        layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)

        // 이미지 속성 설정
        listOf(image1 to R.drawable.a, image2 to R.drawable.b, image3 to R.drawable.a).forEach { (imageView, resourceId) ->
            imageView.setImageResource(resourceId)
            imageView.adjustViewBounds = true
            imageView.scaleType = ImageView.ScaleType.FIT_XY
            imageView.layoutParams = layoutParams
            imageView.clipToOutline = true
        }
    }

    private fun refreshInit() {

        // 이미지 목록 및 속성 새로 고침
        updateImageAttributes()
        imgList.apply {
            clear()
            addAll(listOf(image1, image2, image3))
        }
    }

    private fun getVisibleViews(direction: String): Int {
        val hitRect = Rect()
        var position = -1

        imgList.forEachIndexed { index, imageView ->

            if (imageView.getLocalVisibleRect(hitRect)) {

                if (direction == "left" && position == -1) {

                    // 왼쪽으로 스크롤할 때 첫 번째 보이는 이미지를 찾음
                    position = index
                } else if (direction == "right") {

                    // 오른쪽으로 스크롤할 때 마지막 보이는 이미지를 찾음
                    position = index
                }

            }
        }

        return position
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    inner class MyGestureDetector : GestureDetector.SimpleOnGestureListener() {

        override fun onFling(e1: MotionEvent?, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            // e1이 null일 수 있으므로 null 체크 후 x 좌표 비교
            val direction = if (e1?.x ?: 0f < e2.x) "left" else "right"
            val position = getVisibleViews(direction)

            if (position in imgList.indices) {
                hsc.smoothScrollTo(imgList[position].left, 0)
            }
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {
            Log.d(tag, "현재 보이는 이미지 인덱스: ${getVisibleViews("right")}")
            return super.onSingleTapUp(e)
        }
    }

}
