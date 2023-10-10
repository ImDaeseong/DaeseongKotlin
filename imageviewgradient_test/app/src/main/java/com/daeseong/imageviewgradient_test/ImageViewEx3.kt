package com.daeseong.imageviewgradient_test

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ImageViewEx3 : AppCompatImageView {

    private var nHeight = 0
    private var nWidth = 0
    private var fRotation = 90f

    constructor(context: Context?) : super(context!!) {}
    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {}
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {}

    override fun onDraw(canvas: Canvas) {
        //90 rotate
        canvas.rotate(fRotation, nWidth / 2f, nHeight / 2f)
        super.onDraw(canvas)
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        nWidth = w
        nHeight = h
    }

    override fun invalidate() {
        super.invalidate()
    }

    override fun setRotation(fRotation: Float) {
        this.fRotation = fRotation
        invalidate()
        //invalidate()//좀 더 빠르게 화면을 redraw
        //postInvalidate()//좀 덜 빠르게 화면을 refresh
    }
}
