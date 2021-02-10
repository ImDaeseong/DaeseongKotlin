package com.daeseong.imageviewgradient_test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ImageViewEx8 : AppCompatImageView {

    private var paint: Paint? = null
    private var nHeight = 0
    private var nWidth = 0

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {
        init()
    }

    private fun init() {
        paint = Paint()
        paint!!.color = Color.parseColor("#000000")
        paint!!.isAntiAlias = true
        //paint.setAlpha(204)//255 * 0.8 =204 80%
        paint!!.alpha = 51 //255 * 0.2 =51 20%
        paint!!.style = Paint.Style.FILL
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        nWidth = measuredWidth
        nHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //절반만 투명처리
        canvas.drawRect(0f, (nHeight / 2).toFloat(), nWidth.toFloat(), nHeight.toFloat(), paint!!)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        nWidth = w
        nHeight = h
    }

    override fun invalidate() {
        super.invalidate()
    }
}