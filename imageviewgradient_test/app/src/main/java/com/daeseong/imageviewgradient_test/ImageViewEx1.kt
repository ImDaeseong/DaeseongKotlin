package com.daeseong.imageviewgradient_test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ImageViewEx1 : AppCompatImageView {

    private lateinit var paint: Paint
    private var gradient: LinearGradient? = null
    private var nHeight = 0
    private var nWidth = 0
    private val nColor = Color.argb(204, 0, 0, 0) // 255 * 0.8 = 204 80%

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
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        if (gradient == null) {

            //왼쪽
            //gradient = LinearGradient(0f, (nHeight).toFloat(), (nWidth / 2).toFloat(), (nHeight).toFloat(), nColor, Color.TRANSPARENT, Shader.TileMode.CLAMP)

            //오른쪽
            //gradient = LinearGradient((nWidth /2).toFloat(), (nHeight).toFloat(), (nWidth).toFloat(), (nHeight).toFloat(), Color.TRANSPARENT, nColor, Shader.TileMode.CLAMP)

            //위쪽
            //gradient = LinearGradient((nWidth).toFloat(), 0f, (nWidth).toFloat(), (nHeight /2).toFloat(), nColor, Color.TRANSPARENT, Shader.TileMode.CLAMP)

            //아래쪽
            gradient = LinearGradient((nWidth).toFloat(), (nHeight / 2).toFloat(), (nWidth).toFloat(), (nHeight).toFloat(), Color.TRANSPARENT, nColor, Shader.TileMode.CLAMP)

            paint.shader = gradient
        }
        canvas.drawPaint(paint)
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