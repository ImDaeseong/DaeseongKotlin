package com.daeseong.contextmenu_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow

class Popupmenu3Activity : AppCompatActivity() {

    companion object {
        private val tag = Popupmenu3Activity::class.java.simpleName
    }

    private var button1: Button? = null
    private var popupWindow: PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popupmenu3)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener {

            showPopupWindow()
        }
    }

    private fun showPopupWindow() {

        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_layout1, null)

        //팝업 생성성
        popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        //외부 영역 클릭시 dismiss() 호출
        popupWindow!!.isOutsideTouchable = true
        popupWindow!!.isFocusable = true

        val btnMenu1 = popupView.findViewById<Button>(R.id.btnMenu1)
        btnMenu1.setOnClickListener {

            Log.e(tag, "btnMenu1")

            popupWindow!!.dismiss()
        }

        val btnMenu2 = popupView.findViewById<Button>(R.id.btnMenu2)
        btnMenu2.setOnClickListener {

            Log.e(tag, "btnMenu2")

            popupWindow!!.dismiss()
        }

        popupWindow!!.showAsDropDown(button1)
    }
}