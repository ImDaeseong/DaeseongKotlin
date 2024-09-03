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
import androidx.cardview.widget.CardView

class HorizontalScrollView3Activity : AppCompatActivity() {

    private val tag = HorizontalScrollView3Activity::class.java.simpleName

    private lateinit var btnRefresh: Button
    private lateinit var hsc: HorizontalScrollView
    private lateinit var llayout: LinearLayout
    private lateinit var cardView1: CardView
    private lateinit var cardView2: CardView
    private lateinit var cardView3: CardView
    private lateinit var image1: ImageView
    private lateinit var image2: ImageView
    private lateinit var image3: ImageView

    private val imgList = mutableListOf<CardView>()
    private lateinit var gestureDetector: GestureDetector

    private var nWidth = 0
    private var nImgHeight = 0

    private var selectIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll_view3)

        init()
    }

    private fun init() {

        // 제스처 이벤트를 위한 GestureDetector 초기화
        gestureDetector = GestureDetector(this, MyGestureDetector())

        hsc = findViewById(R.id.hsc)
        llayout = findViewById(R.id.llayout)
        cardView1 = findViewById(R.id.cardView1)
        cardView2 = findViewById(R.id.cardView2)
        cardView3 = findViewById(R.id.cardView3)
        image1 = findViewById(R.id.image1)
        image2 = findViewById(R.id.image2)
        image3 = findViewById(R.id.image3)

        btnRefresh = findViewById(R.id.btnrefresh)
        btnRefresh.setOnClickListener {
            refreshInit()
        }

        // 해상도 사이즈 및 이미지 리스트 초기화
        updateDimensionsAndImageViews()
        imgList.addAll(listOf(cardView1, cardView2, cardView3))

        hsc.setOnTouchListener { _, event ->
            gestureDetector.onTouchEvent(event)
        }
    }

    private fun updateDimensionsAndImageViews() {

        // 이미지 크기 계산
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(29F)
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()

        val layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)
        cardView1.layoutParams = layoutParams
        cardView2.layoutParams = layoutParams
        cardView3.layoutParams = layoutParams
        image1.setImageResource(R.drawable.a)
        image2.setImageResource(R.drawable.a)
        image3.setImageResource(R.drawable.a)
    }

    private fun refreshInit() {

        // 이미지와 레이아웃 속성 새로 고침
        updateDimensionsAndImageViews()
        image1.setImageResource(R.drawable.b)
        image2.setImageResource(R.drawable.b)
        image3.setImageResource(R.drawable.b)

        imgList.clear()
        imgList.addAll(listOf(cardView1, cardView2, cardView3))
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
        override fun onFling(e1: MotionEvent?, e2: MotionEvent,velocityX: Float, velocityY: Float): Boolean {

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
