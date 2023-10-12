package com.daeseong.permission33sdk_test

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.simpleName

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button

    private val PERMISSIONS = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
    private val PERMISSIONS33 = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_MEDIA_IMAGES,Manifest.permission.POST_NOTIFICATIONS)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            val intent = Intent(this@MainActivity, Permission1Activity::class.java)
            startActivity(intent)
        }

        button2 = findViewById(R.id.button2)
        button2.setOnClickListener {
            val intent = Intent(this@MainActivity, Permission2Activity::class.java)
            startActivity(intent)
        }

        button3 = findViewById(R.id.button3)
        button3.setOnClickListener {
            val intent = Intent(this@MainActivity, Permission3Activity::class.java)
            startActivity(intent)
        }

        button4 = findViewById(R.id.button4)
        button4.setOnClickListener {
            val intent = Intent(this@MainActivity, Permission4Activity::class.java)
            startActivity(intent)
        }

        button5 = findViewById(R.id.button5)
        button5.setOnClickListener {
            val intent = Intent(this@MainActivity, Permission5Activity::class.java)
            startActivity(intent)
        }

        button6 = findViewById(R.id.button6)
        button6.setOnClickListener {
            val intent = Intent(this@MainActivity, Permission6Activity::class.java)
            startActivity(intent)
        }
    }

    private fun init() {

        //sdk 33 이상
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

            for (permission in PERMISSIONS33) {
                if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                    Log.e(TAG, "$permission 미허용 상태")
                } else {
                    Log.e(TAG, "$permission 허용 상태")
                }
            }

        } else {

            for (permission in PERMISSIONS) {
                if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                    Log.e(TAG, "$permission 미허용 상태")
                } else {
                    Log.e(TAG, "$permission 허용 상태")
                }
            }
        }

    }
}