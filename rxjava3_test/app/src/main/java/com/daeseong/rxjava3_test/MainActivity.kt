package com.daeseong.rxjava3_test

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)

        setButtonClickListeners()

        checkPermissions()
    }

    private fun setButtonClickListeners() {
        button1.setOnClickListener {
            startActivity(Intent(this, Main1Activity::class.java))
        }

        button2.setOnClickListener {
            startActivity(Intent(this, Main2Activity::class.java))
        }

        button3.setOnClickListener {
            startActivity(Intent(this, Main3Activity::class.java))
        }

        button4.setOnClickListener {
            startActivity(Intent(this, Main4Activity::class.java))
        }

        button5.setOnClickListener {
            startActivity(Intent(this, Main5Activity::class.java))
        }

        button6.setOnClickListener {
            startActivity(Intent(this, Main6Activity::class.java))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                //Log.e(tag, "WRITE_EXTERNAL_STORAGE 권한 없음")
            } else {
                //Log.e(tag, "WRITE_EXTERNAL_STORAGE 권한 있음")
            }
        }
    }

    private fun checkPermissions() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE), 1)
        }
    }
}
