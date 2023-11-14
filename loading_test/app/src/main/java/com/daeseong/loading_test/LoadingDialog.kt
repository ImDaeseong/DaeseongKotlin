package com.daeseong.loading_test

import android.app.Dialog
import android.content.Context
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView

class LoadingDialog(context: Context) : Dialog(context) {

    private var animation: Animation? = null
    private var iv1: ImageView

    init {
        setCancelable(false)
        setCanceledOnTouchOutside(false)
        window?.setBackgroundDrawableResource(android.R.color.transparent)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.loadingdialog)

        iv1 = findViewById(R.id.iv1)
    }

    override fun show() {
        super.show()

        animation = AnimationUtils.loadAnimation(context, R.anim.loading)
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
