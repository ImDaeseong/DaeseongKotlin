package com.daeseong.animation_test

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity


class Animate7Activity : AppCompatActivity() {

    private val tag: String = Animate7Activity::class.java.simpleName

    private var button1 : Button? = null

    private var image1: ImageView? = null

    private var llayout: LinearLayout? = null

    private var nWidth:Int = 0
    private var nHeight:Int = 0
    private var nImgHeight:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animate7)

        //해상도 사이즈
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        nWidth = displayMetrics.widthPixels
        nHeight = displayMetrics.heightPixels

        llayout = findViewById<LinearLayout>(R.id.llayout)

        image1 = findViewById<ImageView>(R.id.image1)

        button1 = findViewById<Button>(R.id.button1)
        button1!!.setOnClickListener {

            runAnimation1(image1!!)
        }
    }

    private fun runAnimation1(imageView: ImageView) {
        try {

            //ImageView - > Bitmap
            val drawable = imageView.drawable
            val bitmap = (drawable as BitmapDrawable).bitmap
            val rows = 3
            val columns = 5
            var xPosition = 0
            var yPosition = 0
            val nWidth = bitmap.width / columns
            val nHeight = bitmap.height / rows
            for (i in 0..13) {
                xPosition = i % columns * nWidth
                yPosition = i / columns * nHeight

                //이미지 자르기
                val croppedBmp = Bitmap.createBitmap(bitmap, xPosition, yPosition, nWidth, nHeight)
                setImageView(i, croppedBmp)
            }
        } catch (ex: Exception) {
        }
    }

    private fun setImageView(nIndex: Int, bitmap: Bitmap) {
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
        imageView.setImageBitmap(bitmap)
        llayout!!.addView(imageView)
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale: Float = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}
