package com.daeseong.newbanner_test.Banner4_style

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout

class ViewPagerIndicatorView : LinearLayout {

    private var mContext: Context
    private lateinit var dotImages: Array<ImageView?>
    private var mDefaultDot = 0
    private var mSelectedDot = 0

    constructor(context: Context) : super(context) {
        mContext = context
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        mContext = context
    }

    fun init(count: Int, defaultDot: Int, selectedDot: Int, margin: Int) {

        removeAllViews()

        dotImages = arrayOfNulls<ImageView>(count)
        mDefaultDot = defaultDot
        mSelectedDot = selectedDot

        for (i in 0 until count) {

            dotImages[i] = ImageView(mContext)
            val params = LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.topMargin = 0
            params.bottomMargin = 0
            params.leftMargin = 0

            if (i == count - 1) params.rightMargin = 0 else params.rightMargin = margin

            params.gravity = Gravity.CENTER
            dotImages[i]!!.layoutParams = params
            dotImages[i]!!.setImageResource(defaultDot)
            dotImages[i]!!.setTag(dotImages[i]!!.id, false)
            this.addView(dotImages[i])
        }

        setSelection(0)
    }

    fun setSelection(position: Int) {

        for (i in dotImages.indices) {
            if (i == position) {
                dotImages[i]!!.setImageResource(mSelectedDot)
            } else {
                dotImages[i]!!.setImageResource(mDefaultDot)
            }
        }
    }
}