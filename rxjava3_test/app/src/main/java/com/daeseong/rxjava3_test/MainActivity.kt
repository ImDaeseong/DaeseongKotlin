package com.daeseong.rxjava3_test

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val tag = MainActivity::class.java.simpleName

    private var button1: Button? = null
    private var button2: Button? = null
    private var button3: Button? = null
    private var button4: Button? = null
    private var button5: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button1 = findViewById(R.id.button1)
        button1!!.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, Main1Activity::class.java)
            startActivity(intent)
        })

        button2 = findViewById(R.id.button2)
        button2!!.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        })

        button3 = findViewById(R.id.button3)
        button3!!.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, Main3Activity::class.java)
            startActivity(intent)
        })

        button4 = findViewById(R.id.button4)
        button4!!.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, Main4Activity::class.java)
            startActivity(intent)
        })

        button5 = findViewById(R.id.button5)
        button5!!.setOnClickListener(View.OnClickListener {

            val intent = Intent(this, Main5Activity::class.java)
            startActivity(intent)
        })

        checkPermissions()
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String?>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if (requestCode == 1) {

            // 네트워크 권한
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {

                //Log.e(tag, "WRITE_EXTERNAL_STORAGE 권한 없음")
            } else {

                //Log.e(tag, "WRITE_EXTERNAL_STORAGE 권한 있음")
            }
        }
    }

    private fun checkPermissions() {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),1)
        }
    }
}