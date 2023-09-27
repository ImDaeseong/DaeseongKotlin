package com.daeseong.contextmenu_test

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Contextmenu1Activity : AppCompatActivity() {

    private val tag = Contextmenu1Activity::class.java.simpleName

    private lateinit var button1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contextmenu1)

        //ContextMenu 메뉴는 길게 클릭해야 호출됨
        button1 = findViewById(R.id.button1)
        registerForContextMenu(button1)
    }

    override fun onCreateContextMenu(menu: ContextMenu, v: View, menuInfo: ContextMenu.ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        if (v == button1) {
            menuInflater.inflate(R.menu.menu1, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.Item1 -> {
                Log.e(tag, "Item1")
                true
            }
            R.id.Item2 -> {
                Log.e(tag, "Item2")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }
}