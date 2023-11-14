package com.daeseong.loading_test

import android.app.Dialog
import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class LoadingDialog1(context: Context) : Dialog(context, R.style.TransparentLoadingLayout) {

    private var animation: Animation? = null
    private var iv1: ImageView

    init {
        setCancelable(false)
        setOnCancelListener(null)
        setContentView(R.layout.loadingdialog1)
        iv1 = findViewById(R.id.iv1)
    }

    override fun show() {
        super.show()

        animation = AnimationUtils.loadAnimation(context, R.anim.rotation)
        iv1.animation = animation
        iv1.startAnimation(animation)
    }

    override fun dismiss() {
        super.dismiss()

        iv1.clearAnimation()
        animation = null
    }

    override fun onBackPressed() {

    }
}
