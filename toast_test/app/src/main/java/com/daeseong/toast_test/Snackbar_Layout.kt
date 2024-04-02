package com.daeseong.toast_test

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import com.google.android.material.snackbar.Snackbar

class CustomSnackbar(private val context: Context, private val view: View, private val message: String) {

    init {
        val snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)

        // Snackbar에 사용자 정의 레이아웃을 추가합니다.
        val inflater = LayoutInflater.from(context)
        val snackbarView = inflater.inflate(R.layout.snackbar_layout, null)
        snackbarView.findViewById<TextView>(R.id.tv1).text = message

        // Snackbar를 표시합니다.
        val snackbarLayout = snackbar.view as Snackbar.SnackbarLayout
        snackbarLayout.addView(snackbarView, 0)
        snackbar.show()
    }
}