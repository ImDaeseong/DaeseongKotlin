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

class Permission2Activity : AppCompatActivity() {

    private val TAG = Permission2Activity::class.java.simpleName

    private lateinit var button1: Button
    private lateinit var requestPermissions: ActivityResultLauncher<Array<String>>

    private val PERMISSIONS = arrayOf(CAMERA, READ_EXTERNAL_STORAGE)
    private val PERMISSIONS33 = arrayOf(CAMERA, READ_MEDIA_IMAGES)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_permission2)

        initPermissionsLauncher()

        button1 = findViewById(R.id.button1)
        button1.setOnClickListener {
            var bPermissResult = false

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {

                for (permission in PERMISSIONS33) {
                    bPermissResult = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
                    if (!bPermissResult) {
                        break
                    }
                }

                if (!bPermissResult) {
                    requestPermissions.launch(PERMISSIONS33)
                } else {
                    Log.e(TAG, "PERMISSIONS33 권한 소요")
                }

            } else {

                for (permission in PERMISSIONS) {
                    bPermissResult = checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED
                    if (!bPermissResult) {
                        break
                    }
                }

                if (!bPermissResult) {
                    requestPermissions.launch(PERMISSIONS)
                } else {
                    Log.e(TAG, "PERMISSIONS 권한 소요")
                }
            }
        }
    }

    private fun initPermissionsLauncher() {
        requestPermissions =
            registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { result ->

                var bCamera = false
                var bImage = false

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    bCamera = result[CAMERA] ?: false
                    bImage = result[READ_MEDIA_IMAGES] ?: false
                } else {
                    bCamera = result[CAMERA] ?: false
                    bImage = result[READ_EXTERNAL_STORAGE] ?: false
                }

                if (bCamera && bImage) {
                    Log.e(TAG, "PERMISSIONS 권한 소유")
                } else {
                    Log.e(TAG, "PERMISSIONS 권한 미소유")
                }
            }
    }
}