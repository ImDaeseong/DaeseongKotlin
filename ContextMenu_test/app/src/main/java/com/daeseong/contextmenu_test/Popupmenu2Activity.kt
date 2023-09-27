package com.daeseong.contextmenu_test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity

class Popupmenu2Activity : AppCompatActivity() {

    private val tag = Popupmenu2Activity::class.java.simpleName

    private lateinit var button1: Button
    private var popupWindow: PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popupmenu2)

        button1 = findViewById<View>(R.id.button1) as Button
        button1.setOnClickListener { showPopupWindow() }
    }

    private fun showPopupWindow() {
        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_layout, null)

        popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true
        )

        popupWindow!!.isOutsideTouchable = true
        popupWindow!!.isFocusable = true

        val btnMenu1 = popupView.findViewById<Button>(R.id.btnMenu1)
        val btnMenu2 = popupView.findViewById<Button>(R.id.btnMenu2)

        btnMenu1.setOnClickListener {
            Log.e(tag, "btnMenu1")
            popupWindow!!.dismiss()
        }

        btnMenu2.setOnClickListener {
            Log.e(tag, "btnMenu2")
            popupWindow!!.dismiss()
        }

        popupWindow!!.showAsDropDown(button1)
    }

}