package com.daeseong.imageviewgradient_test

import android.content.Context
import android.graphics.*
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class ImageViewEx2 : AppCompatImageView {

    private var paint: Paint? = null
    private var shader: Shader? = null
    private var nHeight = 0
    private var nWidth = 0
    private val nColors = intArrayOf(Color.RED, Color.TRANSPARENT)

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(
        context!!, attrs
    ) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context!!, attrs, defStyle
    ) {
        init()
    }

    private fun init() {
        paint = Paint()
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        //가운데 좌표
        val nX = nWidth / 2
        val nY = nHeight / 2

        //표시할 영역
        val nRadius = nWidth / 4
        if (shader == null) {
            shader = RadialGradient((nX).toFloat(), (nY).toFloat(), (nRadius).toFloat(), nColors, null, Shader.TileMode.CLAMP)
            paint!!.shader = shader
            canvas.drawCircle(nX.toFloat(), nY.toFloat(), nRadius.toFloat(), paint!!)
        }
        canvas.drawCircle(nX.toFloat(), nY.toFloat(), nRadius.toFloat(), paint!!)
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