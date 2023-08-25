package com.daeseong.permission33sdk_test

import android.Manifest.permission.*
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

class Permission4Activity : AppCompatActivity() {

    private val TAG = Permission4Activity::class.java.simpleName
    private lateinit var button1: Button
    private lateinit var permissResultLauncher: ActivityResultLauncher<Array<String>>

    private val PERMISSIONS = arrayOf(CAMERA, READ_EXTERNAL_STORAGE)
    private val PERMISSIONS33 = arrayOf(CAMERA, READ_MEDIA_IMAGES)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission4)

        initResultPermission()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                if (ActivityCompat.checkSelfPermission(this, CAMERA) != PackageManager.PERMISSION_GRANTED || checkSelfPermission(READ_MEDIA_IMAGES) != PackageManager.PERMISSION_GRANTED) {
                    permissResultLauncher.launch(PERMISSIONS33)
                } else {
                    Log.e(TAG, "이미 권한 소유")
                }

            } else {

                if (ActivityCompat.checkSelfPermission(this, CAMERA) != PackageManager.PERMISSION_GRANTED) {
                    permissResultLauncher.launch(PERMISSIONS)
                } else {
                    Log.e(TAG, "이미 권한 소유")
                }
            }
        }
    }

    private fun initResultPermission() {
        permissResultLauncher = registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->
            try {
                for ((permission, isGranted) in result) {
                    if (isGranted) {
                        Log.e(TAG, permission)
                    } else {
                        Log.e(TAG, "$permission 권한 미소유")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}