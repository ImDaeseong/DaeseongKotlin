package com.daeseong.horizontalscrollview_flingtest

import android.content.Context
import android.graphics.Canvas
import android.graphics.Path
import android.graphics.RectF
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class RoundedImageView : AppCompatImageView {

    private var rect: RectF? = null
    private val path = Path()
    private val DEFAULT_RADIUS = 20f
    private val radius = DEFAULT_RADIUS

    constructor(context: Context) : super(context) {
        scaleType = ScaleType.FIT_XY
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        scaleType = ScaleType.FIT_XY
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        scaleType = ScaleType.FIT_XY
        init()
    }

    override fun onDraw(canvas: Canvas) {
        canvas.clipPath(path)
        super.onDraw(canvas)
    }

    private fun init() {

        rect = RectF(0F, 0F, this.width.toFloat(), this.height.toFloat())
        path.addRoundRect(rect, radius, radius, Path.Direction.CCW)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        init()
    }
}