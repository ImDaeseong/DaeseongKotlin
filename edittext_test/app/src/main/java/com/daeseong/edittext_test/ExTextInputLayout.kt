package com.daeseong.edittext_test

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class ExTextInputLayout : BackBorderLayout {

    private var editText: EditText? = null
    private var params: LayoutParams? = null

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

        params = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        editText = EditText(context)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            editText!!.setTextCursorDrawable(R.drawable.edittext_cursor)
        }

        editText!!.isSingleLine = true
        editText!!.setTextColor(Color.parseColor("#000000"))
        editText!!.setHintTextColor(Color.parseColor("#AFAFAF"))
        editText!!.gravity = Gravity.CENTER_VERTICAL
        editText!!.textSize = 14f
        editText!!.setPadding(42, 0, 42, 0)
        editText!!.inputType = EditorInfo.TYPE_CLASS_TEXT
        editText!!.hint = "E-mail 주소 입력5"
        editText!!.setText("")
        editText!!.setBackgroundResource(R.drawable.edittext_select_normal)
        editText!!.isCursorVisible = true
        params!!.setMargins(0, 0, 0, 0)
        editText!!.layoutParams = params
        addView(editText)

        editText!!.onFocusChangeListener = OnFocusChangeListener { view, b ->

            if (b) {

                editText!!.setBackgroundResource(R.drawable.edittext_select)

            } else {

                editText!!.setBackgroundResource(R.drawable.edittext_select_normal)

            }
        }
    }

    fun getEditInstance(): EditText? {
        return editText
    }
}