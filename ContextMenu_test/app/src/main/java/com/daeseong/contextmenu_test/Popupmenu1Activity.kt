package com.daeseong.contextmenu_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity

class Popupmenu1Activity : AppCompatActivity() {

    private val tag = Popupmenu1Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popupmenu1)

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener { v -> showPopupMenu(v) }
    }

    private fun showPopupMenu(view: View) {

        val popupMenu = PopupMenu(this, view)
        val menuInflater = popupMenu.menuInflater
        menuInflater.inflate(R.menu.menu1, popupMenu.menu)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.Item1 -> {
                    Log.e(tag, "Item1")
                    true
                }
                R.id.Item2 -> {
                    Log.e(tag, "Item2")
                    true
                }
                else -> false
            }
        }

        popupMenu.show()
    }

}