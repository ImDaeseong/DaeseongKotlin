package com.daeseong.edittext_test

import android.content.Context
import android.graphics.Color
import android.os.Build
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText

class ExTextInputLayout @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0) : BackBorderLayout(context, attrs, defStyleAttr) {

    private var editText: EditText = EditText(context)
    private var params: LayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)

    init {
        setupEditText(context)
    }

    private fun setupEditText(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            editText.setTextCursorDrawable(R.drawable.edittext_cursor)
        }

        editText.isSingleLine = true
        editText.setTextColor(Color.parseColor("#000000"))
        editText.setHintTextColor(Color.parseColor("#AFAFAF"))
        editText.gravity = Gravity.CENTER_VERTICAL
        editText.textSize = 14f
        editText.setPadding(42, 0, 42, 0)
        editText.inputType = EditorInfo.TYPE_CLASS_TEXT
        editText.hint = "E-mail 주소 입력5"
        editText.setText("")
        editText.setBackgroundResource(R.drawable.edittext_select_normal)
        editText.isCursorVisible = true
        params.setMargins(0, 0, 0, 0)
        editText.layoutParams = params
        addView(editText)

        editText.onFocusChangeListener = OnFocusChangeListener { view, b ->
            val backgroundResource = if (b) {
                R.drawable.edittext_select
            } else {
                R.drawable.edittext_select_normal
            }
            editText.setBackgroundResource(backgroundResource)
        }
    }

    fun getEditInstance(): EditText {
        return editText
    }
}
