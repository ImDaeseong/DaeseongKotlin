package com.daeseong.drawerchild_test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Child1Activity : BaseActivity() {

    private val tag: String = Child1Activity::class.java.simpleName

    private lateinit var tv1: TextView
    private lateinit var btn1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child1)

        tv1 = findViewById(R.id.tv1)
        tv1.text = "Child1Activity TextView"

        btn1 = findViewById(R.id.btn1)
        btn1.setOnClickListener {
            Toast.makeText(applicationContext, "Click Child1Activity", Toast.LENGTH_SHORT).show()
        }
    }
}
