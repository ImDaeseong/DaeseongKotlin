package com.daeseong.drawerchild_test

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class Child2Activity : BaseActivity() {

    private val tag: String = Child2Activity::class.java.simpleName

    private lateinit var tv2: TextView
    private lateinit var btn2: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_child2)

        tv2 = findViewById(R.id.tv2)
        tv2.text = "Child2Activity TextView"

        btn2 = findViewById(R.id.btn2)
        btn2.setOnClickListener {
            Toast.makeText(applicationContext, "Click Child2Activity", Toast.LENGTH_SHORT).show()
        }
    }
}
