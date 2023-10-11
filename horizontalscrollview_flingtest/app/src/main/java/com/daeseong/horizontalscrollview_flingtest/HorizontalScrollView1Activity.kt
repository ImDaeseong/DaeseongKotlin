package com.daeseong.horizontalscrollview_flingtest

import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
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

    private var imgList: ArrayList<ImageView> = ArrayList()
    private var gestureDetector: GestureDetector? = null
    private var layoutParams: LinearLayout.LayoutParams? = null

    private var nWidth: Int = 0
    private var nHeight: Int = 0
    private var nImgHeight: Int = 0
    private var selectIndex = 0

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

        initImageList()

        hsc.setOnTouchListener { _, event ->
            gestureDetector?.onTouchEvent(event) ?: false
        }
    }

    private fun initImageList() {

        //이미지 개수
        imgList.clear()

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(this, 29F)
        nHeight = displayMetrics.heightPixels
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()
        layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)

        //이미지 설정
        setImageAttributes(image1, R.drawable.a)
        setImageAttributes(image2, R.drawable.b)
        setImageAttributes(image3, R.drawable.a)

        //이미지 리스트
        imgList.addAll(listOf(image1, image2, image3))
    }

    private fun setImageAttributes(imageView: ImageView, resourceId: Int) {
        imageView.setImageResource(resourceId)
        imageView.adjustViewBounds = true
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.layoutParams = layoutParams
        imageView.clipToOutline = true
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
        nWidth = displayMetrics.widthPixels - dip2px(this, 29F)
        nHeight = displayMetrics.heightPixels
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()
        layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)

        //이미지 설정
        image1!!.setImageResource(R.drawable.b)
        image1!!.adjustViewBounds = true
        image1!!.scaleType = ImageView.ScaleType.FIT_XY
        image1!!.layoutParams = layoutParams
        image2!!.setImageResource(R.drawable.a)
        image2!!.adjustViewBounds = true
        image2!!.scaleType = ImageView.ScaleType.FIT_XY
        image2!!.layoutParams = layoutParams
        image3!!.setImageResource(R.drawable.b)
        image3!!.adjustViewBounds = true
        image3!!.scaleType = ImageView.ScaleType.FIT_XY
        image3!!.layoutParams = layoutParams

        //이미지 리스트
        imgList!!.add(image1!!)
        imgList!!.add(image2!!)
        imgList!!.add(image3!!)
    }

    private fun getVisibleViews(direction: String): Int {
        val hitRect = Rect()
        var position = 0
        var rightCounter = 0

        for (i in imgList.indices) {
            if (imgList[i].getLocalVisibleRect(hitRect)) {
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

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun px2dip(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


    inner class MyGestureDetector : SimpleOnGestureListener() {

        override fun onFling(
            e1: MotionEvent, e2: MotionEvent,
            velocityX: Float, velocityY: Float
        ): Boolean {
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
