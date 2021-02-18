package com.daeseong.horizontalscrollview_test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


open class RoundImageView : AppCompatImageView {

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context) : super(context) {
        init()
    }

    private val roundRect = RectF()
    private var rect_radius = 90f
    private val maskPaint: Paint = Paint()
    private val zonePaint: Paint = Paint()

    private fun init() {
        maskPaint.isAntiAlias = true
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        maskPaint.isFilterBitmap = true
        zonePaint.isAntiAlias = true
        zonePaint.isFilterBitmap = true
        val density: Float = resources.displayMetrics.density
        rect_radius *= density
    }

    fun setRectRadius(radius: Float) {
        rect_radius = radius
        invalidate()
    }

    protected override fun onLayout( changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val w = width
        val h = height
        roundRect[0f, 0f, w.toFloat()] = h.toFloat()
    }

    override fun draw(canvas: Canvas) {

        canvas.saveLayer(roundRect, zonePaint)
        canvas.drawRoundRect(roundRect, rect_radius, rect_radius, zonePaint)
        canvas.saveLayer(roundRect, maskPaint)
        super.draw(canvas)
        canvas.restore()
    }
}