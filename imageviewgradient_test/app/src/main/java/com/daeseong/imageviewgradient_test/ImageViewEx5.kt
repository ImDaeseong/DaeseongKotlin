package com.daeseong.imageviewgradient_test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Typeface
import android.text.TextPaint
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class ImageViewEx5 : AppCompatImageView {

    private var paint: Paint? = null
    private var nHeight = 0
    private var nWidth = 0
    private var bfitXY = false
    private var sText: String? = null

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
        paint!!.color = Color.parseColor("#ffffff")
        paint!!.typeface = Typeface.DEFAULT_BOLD
        paint!!.textAlign = Paint.Align.LEFT
        paint!!.flags = TextPaint.ANTI_ALIAS_FLAG
        paint!!.textSize = resources.displayMetrics.density * 10
        sText = "이미지에 텍스트 추가"
        bfitXY = true

        if (bfitXY) {
            scaleType = ScaleType.FIT_XY
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        nWidth = measuredWidth
        nHeight = measuredHeight
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        /*
        if (bfitXY) {
            canvas.drawText(sText!!, 17f, height - 15.toFloat(), paint!!)
        } else {
            canvas.drawText(sText!!, 17f, nHeight - 15.toFloat(), paint!!)
        }
        */

        if (bfitXY) {
            sText?.let { paint?.let { it1 -> canvas.drawText(it, 17f, height - 15f, it1) } }
        } else {
            sText?.let { paint?.let { it1 -> canvas.drawText(it, 17f, nHeight - 15f, it1) } }
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        nWidth = w
        nHeight = h
    }

    override fun invalidate() {
        super.invalidate()
    }

    fun setText(sText: String?) {
        this.sText = sText
        invalidate()
    }
}