package com.daeseong.imageviewgradient_test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ImageViewEx2 : AppCompatImageView {

    private lateinit var paint: Paint
    private var shader: Shader? = null
    private var nHeight = 0
    private var nWidth = 0
    private val nColors = intArrayOf(Color.RED, Color.TRANSPARENT)

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

        //가운데 좌표
        val centerX = nWidth / 2f
        val centerY = nHeight / 2f

        //표시할 영역
        val radius = nWidth / 4f
        if (shader == null) {
            shader = RadialGradient(centerX, centerY, radius, nColors, null, Shader.TileMode.CLAMP)
            paint.shader = shader
        }

        canvas.drawCircle(centerX, centerY, radius, paint)
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
