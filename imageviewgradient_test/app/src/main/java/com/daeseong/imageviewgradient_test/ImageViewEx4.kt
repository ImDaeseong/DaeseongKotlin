package com.daeseong.imageviewgradient_test

import android.content.Context
import android.graphics.Canvas
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.graphics.drawable.BitmapDrawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView


class ImageViewEx4 : AppCompatImageView {

    private var fCornerRadius = 15f

    constructor(context: Context?) : super(context!!) {

    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {

    }

    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(context!!, attrs, defStyle) {

    }

    override fun onDraw(canvas: Canvas) {

        //round border
        val myDrawable = drawable
        if (myDrawable != null && myDrawable is BitmapDrawable && fCornerRadius > 0) {
            val paint = myDrawable.paint
            val color = -0x1000000
            val bitmapBounds = myDrawable.getBounds()
            val rectF = RectF(bitmapBounds)
            val saveCount = canvas.saveLayer(rectF, null, Canvas.ALL_SAVE_FLAG)
            imageMatrix.mapRect(rectF)
            paint.isAntiAlias = true
            canvas.drawARGB(0, 0, 0, 0)
            paint.color = color
            canvas.drawRoundRect(rectF, fCornerRadius, fCornerRadius, paint)
            val oldMode = paint.xfermode
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
            super.onDraw(canvas)
            paint.xfermode = oldMode
            canvas.restoreToCount(saveCount)
        } else {
            super.onDraw(canvas)
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
    }

    override fun invalidate() {
        super.invalidate()
    }

    fun setCornerRadius(fCornerRadius: Float) {
        this.fCornerRadius = fCornerRadius
        invalidate()
        //invalidate()//좀 더 빠르게 화면을 redraw
        //postInvalidate()//좀 덜 빠르게 화면을 refresh
    }
}