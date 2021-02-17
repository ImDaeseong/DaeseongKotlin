package com.daeseong.drawerchild_test

import android.os.Bundle
import android.widget.Button
import android.widget.Toast


class MainActivity : BaseActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    private var btnchild: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnchild = findViewById<Button>(R.id.btnchild)
        btnchild!!.setOnClickListener {
            Toast.makeText(applicationContext, "Click MainActivity", Toast.LENGTH_SHORT).show()
        }
    }
}
