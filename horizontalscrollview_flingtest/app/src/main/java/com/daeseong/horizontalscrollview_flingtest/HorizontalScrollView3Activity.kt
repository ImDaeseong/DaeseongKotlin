package com.daeseong.horizontalscrollview_flingtest


import android.content.Context
import android.graphics.Rect
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.GestureDetector
import android.view.GestureDetector.SimpleOnGestureListener
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.HorizontalScrollView
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView


class HorizontalScrollView3Activity : AppCompatActivity() {

    private val tag = HorizontalScrollView3Activity::class.java.simpleName

    private var btnrefresh: Button? = null

    private var hsc: HorizontalScrollView? = null
    private var llayout: LinearLayout? = null
    private var cardView1: CardView? = null
    private var cardView2:CardView? = null
    private var cardView3:CardView? = null
    private var image1: ImageView? = null
    private var image2:ImageView? = null
    private var image3:ImageView? = null

    private var imgList: ArrayList<CardView>? = null
    private var gestureDetector: GestureDetector? = null

    private var nWidth:Int = 0
    private var nHeight:Int = 0
    private var nImgHeight:Int = 0

    private var selectIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horizontal_scroll_view3)

        init()
    }

    private fun init() {

        //제스처 이벤트
        gestureDetector = GestureDetector(applicationContext, MyGestureDetector())
        hsc = findViewById<HorizontalScrollView>(R.id.hsc)
        llayout = findViewById<LinearLayout>(R.id.llayout)
        cardView1 = findViewById<CardView>(R.id.cardView1)
        cardView2 = findViewById<CardView>(R.id.cardView2)
        cardView3 = findViewById<CardView>(R.id.cardView3)
        image1 = findViewById<ImageView>(R.id.image1)
        image2 = findViewById<ImageView>(R.id.image2)
        image3 = findViewById<ImageView>(R.id.image3)

        //이미지 개수
        imgList = ArrayList()

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(this, 29F)
        nHeight = displayMetrics.heightPixels
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()
        val layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)
        cardView1!!.layoutParams = layoutParams
        cardView2!!.layoutParams = layoutParams
        cardView3!!.layoutParams = layoutParams
        image1!!.setImageResource(R.drawable.a)
        image2!!.setImageResource(R.drawable.a)
        image3!!.setImageResource(R.drawable.a)

        //이미지 리스트
        imgList!!.add(cardView1!!)
        imgList!!.add(cardView2!!)
        imgList!!.add(cardView3!!)


        hsc!!.setOnTouchListener(View.OnTouchListener { _, event ->

            if (gestureDetector!!.onTouchEvent(event)) {
                return@OnTouchListener true
            }
            return@OnTouchListener false
        })

        btnrefresh = findViewById(R.id.btnrefresh)
        btnrefresh!!.setOnClickListener {
            refreshInit()
        }
    }

    private fun prevImage() {
        hsc!!.smoothScrollTo(hsc!!.scrollX - nWidth, 0)
    }

    private fun nextImage() {
        hsc!!.smoothScrollTo(hsc!!.scrollX + nWidth, 0)
    }

    private fun refreshInit() {

        //이미지 개수
        imgList!!.clear()

        //llayout!!.removeAllViews()

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels - dip2px(this, 29F)
        nHeight = displayMetrics.heightPixels
        nImgHeight = (320 * (nWidth.toFloat() / 960)).toInt()
        val layoutParams = LinearLayout.LayoutParams(nWidth, nImgHeight)
        cardView1!!.layoutParams = layoutParams
        cardView2!!.layoutParams = layoutParams
        cardView3!!.layoutParams = layoutParams
        image1!!.setImageResource(R.drawable.b)
        image2!!.setImageResource(R.drawable.b)
        image3!!.setImageResource(R.drawable.b)

        //이미지 리스트
        imgList!!.add(cardView1!!)
        imgList!!.add(cardView2!!)
        imgList!!.add(cardView3!!)
    }

    private fun getVisibleViews(direction: String): Int {

        val hitRect = Rect()
        var position = 0
        var rightCounter = 0
        for (i in imgList!!.indices) {
            if (imgList!![i].getLocalVisibleRect(hitRect)) {
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
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    private fun px2dip(context: Context, pxValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }


    inner class MyGestureDetector : SimpleOnGestureListener() {

        override fun onFling(e1: MotionEvent, e2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {

            if (e1.x < e2.x) {

                selectIndex = getVisibleViews("left")
            } else {

                selectIndex = getVisibleViews("right")
            }

            hsc!!.smoothScrollTo(imgList!![selectIndex].left, 0)
            return true
        }

        override fun onSingleTapUp(e: MotionEvent): Boolean {

            Log.e(tag, "selectIndex:$selectIndex")
            return super.onSingleTapUp(e)
        }
    }

}
