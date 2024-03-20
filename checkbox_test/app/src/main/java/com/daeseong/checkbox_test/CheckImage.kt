package com.daeseong.checkbox_test

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class CheckImage : AppCompatImageView {

    private var isChecked = false
    private val selectImgID = R.drawable.check_selected
    private val unselectImgID = R.drawable.check_unselected

    constructor(context: Context) : super(context) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context, attrs: AttributeSet?, defStyle: Int) : super(context, attrs, defStyle) {
        init()
    }

    private fun init() {
        setOnClickListener {
            toggleChecked()
        }
    }

    private fun toggleChecked() {
        isChecked = !isChecked
        setImageResource(if (isChecked) selectImgID else unselectImgID)
    }

    fun setCheck(isChecked: Boolean) {
        this.isChecked = isChecked
        setImageResource(if (isChecked) selectImgID else unselectImgID)
    }

    fun isChecked(): Boolean {
        return isChecked
    }
}