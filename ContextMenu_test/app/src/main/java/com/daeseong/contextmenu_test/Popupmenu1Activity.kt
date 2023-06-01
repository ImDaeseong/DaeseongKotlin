package com.daeseong.contextmenu_test

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity

class Popupmenu1Activity : AppCompatActivity() {

    companion object {
        private val tag = Popupmenu1Activity::class.java.simpleName
    }

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popupmenu1)

        button1 = findViewById<View>(R.id.button1) as Button
        button1!!.setOnClickListener { v ->

            val popupMenu = PopupMenu(this@Popupmenu1Activity, v)
            val menuInflater = popupMenu.menuInflater
            menuInflater.inflate(R.menu.menu1, popupMenu.menu)

            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.Item1 -> {
                        Log.e(tag, "Item1")
                        return@OnMenuItemClickListener true
                    }
                    R.id.Item2 -> {
                        Log.e(tag, "Item2")
                        return@OnMenuItemClickListener true
                    }
                }
                false
            })
            popupMenu.show()
        }
    }

}