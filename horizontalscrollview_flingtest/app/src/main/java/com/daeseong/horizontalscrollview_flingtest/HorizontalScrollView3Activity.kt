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

    private var imgList: MutableList<CardView> = mutableListOf()
    private var gestureDetector: GestureDetector? = null

    private var nWidth: Int = 0
    private var nImgHeight: Int = 0

    private var selectIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll_view3)

        init()
    }

    private fun init() {

        //제스처 이벤트
        gestureDetector = GestureDetector(applicationContext, MyGestureDetector())

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

        //이미지 개수
        imgList = ArrayList()

        //해상도 사이즈
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

        //이미지 리스트
        imgList.addAll(listOf(cardView1, cardView2, cardView3))


        hsc.setOnTouchListener { _, event ->
            gestureDetector?.onTouchEvent(event) ?: false
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
        imgList.clear()

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(29F)
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()
        val layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)

        cardView1.layoutParams = layoutParams
        cardView2.layoutParams = layoutParams
        cardView3.layoutParams = layoutParams
        image1.setImageResource(R.drawable.b)
        image2.setImageResource(R.drawable.b)
        image3.setImageResource(R.drawable.b)

        //이미지 리스트
        imgList.addAll(listOf(cardView1, cardView2, cardView3))
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
            selectIndex = when {
                e1.x < e2.x -> getVisibleViews("left")
                else -> getVisibleViews("right")
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
