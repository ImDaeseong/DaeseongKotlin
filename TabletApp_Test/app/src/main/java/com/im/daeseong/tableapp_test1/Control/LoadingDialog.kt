package com.im.daeseong.tableapp_test1.Control

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import com.im.daeseong.tableapp_test1.R

class LoadingDialog(context : Context) {

    //화면 전체 영역
    private var dialog: Dialog = Dialog(context, android.R.style.Theme_Black_NoTitleBar_Fullscreen)

    //다이얼로그 영역
    //private var dialog: Dialog = Dialog(context)

    init {

        val view = LayoutInflater.from(context).inflate(R.layout.loading_layout, null)

        dialog.setContentView(view)
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        //클릭시 종료
        view.setOnClickListener {
            dismiss()
        }

        val iv1: ImageView = view.findViewById(R.id.iv1)
        val animation: Animation = ScaleAnimation(
            0.5f, 1.0f,
            0.5f, 1.0f,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        )
        animation.duration = 1000
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.REVERSE
        iv1.startAnimation(animation)
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