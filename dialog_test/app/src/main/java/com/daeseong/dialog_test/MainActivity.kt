package com.daeseong.dialog_test

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val tag: String = MainActivity::class.java.simpleName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //startActivity(Intent(this, Style1Activity_Dialog::class.java))
        //startActivity(Intent(this, Style2Activity_Dialog::class.java))
        //startActivity(Intent(this, Style3Activity_Dialog::class.java))
        //startActivity(Intent(this, Style4Activity_Dialog::class.java))
    }

    override fun onBackPressed() {

        Custom1Dialog(this) {
            finish()
        }.show()
    }
}
