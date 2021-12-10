package com.daeseong.swiperefreshlayout_test

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class ConstraintLayoutEx : ConstraintLayout {
    private var iv1: ImageView? = null
    private var loading: Animation? = null

    constructor(context: Context) : super(context) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    private fun init(context: Context) {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.wait, this)
        iv1 = view.findViewById<View>(R.id.iv1) as ImageView
        loading = AnimationUtils.loadAnimation(context, R.anim.loading)
        //iv1!!.startAnimation(loading)
    }

    fun show() {
        visibility = VISIBLE
        iv1!!.startAnimation(loading)
    }

    fun hide() {
        visibility = GONE
        clearAnimation()
        iv1!!.clearAnimation()
    }
}
