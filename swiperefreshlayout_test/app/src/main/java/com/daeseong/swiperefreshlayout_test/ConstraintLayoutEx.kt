package com.daeseong.swiperefreshlayout_test

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout

class ConstraintLayoutEx @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val iv1: ImageView
    private val loading: Animation

    init {
        val view = LayoutInflater.from(context).inflate(R.layout.wait, this)
        iv1 = view.findViewById(R.id.iv1)
        loading = AnimationUtils.loadAnimation(context, R.anim.loading)
    }

    fun show() {
        visibility = View.VISIBLE
        iv1.startAnimation(loading)
    }

    fun hide() {
        visibility = View.GONE
        clearAnimation()
        iv1.clearAnimation()
    }
}
