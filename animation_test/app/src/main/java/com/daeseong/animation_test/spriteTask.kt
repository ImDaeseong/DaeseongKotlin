package com.daeseong.animation_test

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Handler
import android.widget.ImageView
import java.util.*

class SpriteTask(private val context: Context, private val imageView: ImageView) : TimerTask() {

    private val tag: String = SpriteTask::class.java.simpleName
    private val handler = Handler()
    private val list = ArrayList<Bitmap>()
    private var nIndex = 0

    init {
        initBitmap()
    }

    override fun run() {
        handler.post {
            changeImage()
        }
    }

    private fun changeImage() {
        if (nIndex >= 14) {
            nIndex = 0
        }
        imageView.setImageBitmap(list[nIndex])
        nIndex++
    }

    private fun initBitmap() {
        val bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.sprite)
        val rows = 3
        val columns = 5
        var xPosition: Int
        var yPosition: Int
        val nWidth = bitmap.width / columns
        val nHeight = bitmap.height / rows
        for (i in 0 until 14) {
            xPosition = i % columns * nWidth
            yPosition = i / columns * nHeight
            val croppedBmp = Bitmap.createBitmap(bitmap, xPosition, yPosition, nWidth, nHeight)
            list.add(croppedBmp)
        }
    }
}