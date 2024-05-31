package com.daeseong.loading_test.Controls

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import com.daeseong.loading_test.R

class LoadingDialog1(context : Context) {

    private var dialog: Dialog = Dialog(context)

    init {

        val view = LayoutInflater.from(context).inflate(R.layout.loading_layout, null)
        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))


        val imageView: ImageView = view.findViewById(R.id.iv1)
        val animation: Animation = ScaleAnimation(
            0.5f, 1.0f,
            0.5f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.duration = 1000
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.REVERSE
        imageView.startAnimation(animation)

        /*
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
        */
    }

    fun show() {
        if(!dialog.isShowing) {
            dialog.show()
        }
    }

    fun dismiss() {
        if(dialog.isShowing) {
            dialog.dismiss()
        }
    }

    fun isShowing(): Boolean {
        return dialog.isShowing
    }
}