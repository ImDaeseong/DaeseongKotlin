package com.daeseong.loading_test

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageView

class LoadingDialog2(context : Context) {

    private var dialog: Dialog = Dialog(context)

    init {

        val view = LayoutInflater.from(context).inflate(R.layout.loading_layout, null)
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val imageView: ImageView = view.findViewById(R.id.iv1)
        val animation: Animation = RotateAnimation(
            0f,
            360f,
            Animation.RELATIVE_TO_SELF,
            0.5f,
            Animation.RELATIVE_TO_SELF,
            0.5f
        )

        animation.duration = 2000
        animation.repeatCount = 10
        animation.fillAfter = true
        imageView.startAnimation(animation)
    }

    fun show() {

        dialog.show()
    }

    fun close() {
        dialog.dismiss()
    }
}