package com.daeseong.loading_test


import android.app.Dialog
import android.content.Context
import android.view.ViewGroup

class LoadingOverlay(context: Context) : Dialog(context, R.style.TransparentOverlay) {

    init {
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        setContentView(R.layout.overlay_loading)
        window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
}