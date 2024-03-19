package com.daeseong.spinner_test.Controls

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatSpinner

class ComboBox : AppCompatSpinner {

    private var adapter: ComboBoxAdapter

    constructor(context: Context) : super(context) {
        adapter = ComboBoxAdapter(context)
        setAdapter(adapter)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        adapter = ComboBoxAdapter(context)
        setAdapter(adapter)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        adapter = ComboBoxAdapter(context)
        setAdapter(adapter)
    }

    fun addItem(item: String) {
        adapter.addItem(item)
    }

    fun removeItem(item: String) {
        adapter.removeItem(item)
    }

    fun getList(): List<String> {
        return adapter.getList()
    }
}