package com.daeseong.horizontalscrollview_test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

open class RoundImageView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : AppCompatImageView(context, attrs) {

    private val roundRect = RectF()
    private var rectRadius = 90f
    private val maskPaint: Paint = Paint()
    private val zonePaint: Paint = Paint()

    init {
        init()
    }

    private fun init() {
        maskPaint.isAntiAlias = true
        maskPaint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
        maskPaint.isFilterBitmap = true
        zonePaint.isAntiAlias = true
        zonePaint.isFilterBitmap = true
        val density: Float = resources.displayMetrics.density
        rectRadius *= density
    }

    fun setRectRadius(radius: Float) {
        rectRadius = radius
        invalidate()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        val w = width
        val h = height
        roundRect.set(0f, 0f, w.toFloat(), h.toFloat())
    }

    override fun draw(canvas: Canvas) {
        canvas.saveLayer(roundRect, zonePaint)
        canvas.drawRoundRect(roundRect, rectRadius, rectRadius, zonePaint)
        canvas.saveLayer(roundRect, maskPaint)
        super.draw(canvas)
        canvas.restore()
    }
}
