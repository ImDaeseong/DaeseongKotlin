package com.daeseong.newbanner_test.Banner3_style

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.DrawableRes

class ViewPagerIndicatorView @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {
    private val mContext: Context = context
    private lateinit var dotImages: Array<ImageView?>
    private var mDefaultDot = 0
    private var mSelectedDot = 0

    fun init(count: Int, @DrawableRes defaultDot: Int, @DrawableRes selectedDot: Int, margin: Int) {

        this.removeAllViews()

        dotImages = arrayOfNulls(count)
        mDefaultDot = defaultDot
        mSelectedDot = selectedDot

        for (i in 0 until count) {

            dotImages[i] = ImageView(mContext)

            val params = LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
            params.topMargin = 0
            params.bottomMargin = 0
            params.leftMargin = 0
            params.rightMargin = if (i == count - 1) 0 else margin
            params.gravity = Gravity.CENTER

            dotImages[i]?.layoutParams = params
            dotImages[i]?.setImageResource(defaultDot)
            dotImages[i]?.tag = dotImages[i]?.id to false
            this.addView(dotImages[i])
        }
        setSelection(0)
    }

    fun setSelection(position: Int) {
        for (i in dotImages.indices) {
            dotImages[i]?.setImageResource(if (i == position) mSelectedDot else mDefaultDot)
        }
    }
}