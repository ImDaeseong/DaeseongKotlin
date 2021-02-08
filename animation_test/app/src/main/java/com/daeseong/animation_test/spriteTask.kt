package com.daeseong.animation_test


import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.widget.ImageView
import java.util.*


class spriteTask(context: Context, imageView: ImageView) : TimerTask() {

    private val tag: String = spriteTask::class.java.simpleName

    private var context: Context? = null
    private var handler: Handler? = null
    private var imageView: ImageView? = null
    private var list: ArrayList<Bitmap>? = null
    private var nIndex = 0

    init {

        this.context = context
        this.imageView = imageView
        handler = Handler()
        list = ArrayList()

        initBitmap()
    }

    override fun run() {

        handler!!.post {
            changeImage()
        }
    }

    private fun changeImage() {
        if (nIndex >= 14) {
            nIndex = 0
        }

        //Log.e(TAG, "nIndex:" + nIndex);
        imageView!!.setImageBitmap(list!![nIndex])
        nIndex++
    }

    private fun initBitmap() {

        //14개의 이미지
        val bitmap = BitmapFactory.decodeResource(context!!.resources, R.drawable.sprite)
        val rows = 3
        val columns = 5
        var xPosition = 0
        var yPosition = 0
        val nWidth = bitmap.width / columns
        val nHeight = bitmap.height / rows
        for (i in 0..13) {
            xPosition = i % columns * nWidth
            yPosition = i / columns * nHeight

            //이미지 쪼개서 리스트에 저장

            //Bitmap -> imageView
            val croppedBmp = Bitmap.createBitmap(bitmap, xPosition, yPosition, nWidth, nHeight)
            list!!.add(croppedBmp)
        }
    }
}