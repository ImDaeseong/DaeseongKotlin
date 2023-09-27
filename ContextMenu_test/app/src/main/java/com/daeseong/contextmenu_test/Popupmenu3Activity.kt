package com.daeseong.contextmenu_test

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity

class Popupmenu3Activity : AppCompatActivity() {

    companion object {
        private val tag = Popupmenu3Activity::class.java.simpleName
    }

    private lateinit var button1: Button
    private var popupWindow: PopupWindow? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popupmenu3)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            showPopupWindow()
        }
    }

    private fun showPopupWindow() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_layout1, null)

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

        val nTop: Int = dip2px(this, 4f)
        popupWindow!!.showAsDropDown(button1, 0, nTop)
    }

    private fun dip2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }
}