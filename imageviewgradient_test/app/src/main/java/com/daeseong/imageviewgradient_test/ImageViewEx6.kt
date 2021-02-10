package com.daeseong.imageviewgradient_test

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class ImageViewEx6 : AppCompatImageView {
    private var paint: Paint? = null
    private var path: Path? = null
    private var rect: RectF? = null
    private var nHeight = 0
    private var nWidth = 0
    private val fCornerRadius = 20f

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
        scaleType = ScaleType.FIT_XY
        paint = Paint()
        path = Path()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(path!!)
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        nWidth = w
        nHeight = h

        //round border
        rect = RectF(0F, 0F, nWidth.toFloat(), nHeight.toFloat())
        path!!.addRoundRect(rect, fCornerRadius, fCornerRadius, Path.Direction.CCW)
    }

    override fun invalidate() {
        super.invalidate()
    }
}