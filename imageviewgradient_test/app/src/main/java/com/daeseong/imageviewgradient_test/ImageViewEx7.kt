package com.daeseong.imageviewgradient_test
import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Rect
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class ImageViewEx7 : AppCompatImageView {
    private var paint: Paint? = null
    private var rect: Rect? = null

    //빨간색 border
    private val nBorderColor: Int = android.graphics.Color.argb(255, 255, 0, 0)

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

        rect = canvas.clipBounds
        rect!!.bottom--
        rect!!.right--
        paint!!.color = nBorderColor
        paint!!.style = Paint.Style.STROKE
        canvas.drawRect(rect!!, paint!!)
    }
}