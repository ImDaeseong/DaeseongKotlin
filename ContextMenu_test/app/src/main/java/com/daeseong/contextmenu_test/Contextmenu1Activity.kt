package com.daeseong.contextmenu_test

import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import android.view.ContextMenu.ContextMenuInfo
import android.view.MenuItem
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class Contextmenu1Activity : AppCompatActivity() {

    companion object {
        private val tag = Contextmenu1Activity::class.java.simpleName
    }

    private var button1: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contextmenu1)

        //ContextMenu 메뉴는 길게 클릭해야 호출됨
        button1 = findViewById<View>(R.id.button1) as Button
        registerForContextMenu(button1)
    }

    override fun onCreateContextMenu(menu: ContextMenu?, v: View, menuInfo: ContextMenuInfo?) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val menuInflater = menuInflater
        if (v === button1) {
            menuInflater.inflate(R.menu.menu1, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.Item1 -> {
                Log.e(tag, "Item1")
                return true
            }
            R.id.Item2 -> {
                Log.e(tag, "Item2")
                return true
            }
        }
        return super.onContextItemSelected(item)
    }
}